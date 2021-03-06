/**
 * 
 */
package org.vaadin.easyuploads.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.OrderedLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class TestForUpload extends CustomComponent implements
        Upload.FinishedListener, FailedListener, SucceededListener,
        Upload.ProgressListener, StartedListener {

    Layout main = new OrderedLayout();

    Buffer buffer = new MemoryBuffer();

    Panel status = new Panel("Uploaded file:");

    private final Upload up;

    private final Label l;

    private final ProgressIndicator pi = new ProgressIndicator();
    private final ProgressIndicator pi2 = new ProgressIndicator();

    private final Label memoryStatus;

    private final Select uploadBufferSelector;

    private TextField textField;

    private Label textFieldValue;

    public TestForUpload() {
        setCompositionRoot(main);
        main.addComponent(new Label(
                "This is a simple test for upload application. "
                        + "Upload should work with big files and concurrent "
                        + "requests should not be blocked. Button 'b' reads "
                        + "current state into label below it. Memory receiver "
                        + "streams upload contents into memory. You may track"
                        + "consumption."
                        + "tempfile receiver writes upload to file and "
                        + "should have low memory consumption."));

        main
                .addComponent(new Label(
                        "Clicking on button b updates information about upload components status or same with garbage collector."));

        textField = new TextField("Test field");
        textFieldValue = new Label();
        main.addComponent(textField);
        main.addComponent(textFieldValue);

        up = new Upload("Upload", buffer);
        up.setImmediate(true);
        up.addListener((FinishedListener) this);
        up.addListener((FailedListener) this);
        up.addListener((SucceededListener) this);
        up.addListener((StartedListener) this);

        up.setProgressListener(this);
        up.addListener(new Upload.ProgressListener() {

            public void updateProgress(long readBytes, long contentLenght) {
                pi2.setValue(new Float(readBytes / (float) contentLenght));

                refreshMemUsage();
            }

        });

        final Button b = new Button("b", this, "readState");

        final Button c = new Button("b with gc", this, "gc");

        main.addComponent(b);
        main.addComponent(c);

        uploadBufferSelector = new Select("Receiver type");
        uploadBufferSelector.setImmediate(true);
        uploadBufferSelector.addItem("memory");
        uploadBufferSelector.setValue("memory");
        uploadBufferSelector.addItem("tempfile");
        uploadBufferSelector
                .addListener(new AbstractField.ValueChangeListener() {
                    public void valueChange(ValueChangeEvent event) {
                        setBuffer();
                    }
                });
        main.addComponent(uploadBufferSelector);

        main.addComponent(up);
        l = new Label("Idle");
        main.addComponent(l);

        pi.setVisible(false);
        pi.setPollingInterval(1000);
        main.addComponent(pi);

        pi2.setVisible(false);
        pi2.setPollingInterval(1000);
        main.addComponent(pi2);

        memoryStatus = new Label();
        main.addComponent(memoryStatus);

        status.setVisible(false);
        main.addComponent(status);

        final Button restart = new Button("R");
        restart.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                getApplication().close();
            }
        });
        main.addComponent(restart);

    }

    private void setBuffer() {
        final String id = (String) uploadBufferSelector.getValue();
        if ("memory".equals(id)) {
            buffer = new MemoryBuffer();
        } else if ("tempfile".equals(id)) {
            buffer = new TmpFileBuffer();
        }
        up.setReceiver(buffer);
    }

    public void gc() {
        Runtime.getRuntime().gc();
        readState();
    }

    public void readState() {
        final StringBuffer sb = new StringBuffer();

        if (up.isUploading()) {
            sb.append("Uploading...");
            sb.append(up.getBytesRead());
            sb.append("/");
            sb.append(up.getUploadSize());
            sb.append(" ");
            sb.append(Math.round(100 * up.getBytesRead()
                    / (double) up.getUploadSize()));
            sb.append("%");
        } else {
            sb.append("Idle");
        }
        l.setValue(sb.toString());
        refreshMemUsage();
    }

    public void uploadFinished(FinishedEvent event) {
        status.removeAllComponents();
        final InputStream stream = buffer.getStream();
        if (stream == null) {
            status.addComponent(new Label(
                    "Upload finished, but output buffer is null!!"));
        } else {
            status
                    .addComponent(new Label("<b>Name:</b> "
                            + event.getFilename(), Label.CONTENT_XHTML));
            status.addComponent(new Label("<b>Mimetype:</b> "
                    + event.getMIMEType(), Label.CONTENT_XHTML));
            status.addComponent(new Label("<b>Size:</b> " + event.getLength()
                    + " bytes.", Label.CONTENT_XHTML));

            status.addComponent(new Link("Download " + buffer.getFileName(),
                    new StreamResource(buffer, buffer.getFileName(),
                            getApplication())));

            status.setVisible(true);
        }
    }

    public interface Buffer extends StreamResource.StreamSource,
            Upload.Receiver {

        String getFileName();
    }

    public class MemoryBuffer implements Buffer {
        ByteArrayOutputStream outputBuffer = null;

        String mimeType;

        String fileName;

        public MemoryBuffer() {

        }

        public InputStream getStream() {
            if (outputBuffer == null) {
                return null;
            }
            return new ByteArrayInputStream(outputBuffer.toByteArray());
        }

        /**
         * @see com.vaadin.ui.Upload.Receiver#receiveUpload(String,
         *      String)
         */
        public OutputStream receiveUpload(String filename, String MIMEType) {
            fileName = filename;
            mimeType = MIMEType;
            outputBuffer = new ByteArrayOutputStream();
            return outputBuffer;
        }

        /**
         * Returns the fileName.
         * 
         * @return String
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Returns the mimeType.
         * 
         * @return String
         */
        public String getMimeType() {
            return mimeType;
        }

    }

    public class TmpFileBuffer implements Buffer {
        String mimeType;

        String fileName;

        private File file;

        public TmpFileBuffer() {
            final String tempFileName = "upload_tmpfile_"
                    + System.currentTimeMillis();
            try {
                file = File.createTempFile(tempFileName, null);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public InputStream getStream() {
            if (file == null) {
                return null;
            }
            try {
                return new FileInputStream(file);
            } catch (final FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @see com.vaadin.ui.Upload.Receiver#receiveUpload(String,
         *      String)
         */
        public OutputStream receiveUpload(String filename, String MIMEType) {
            fileName = filename;
            mimeType = MIMEType;
            try {
                return new FileOutputStream(file);
            } catch (final FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Returns the fileName.
         * 
         * @return String
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Returns the mimeType.
         * 
         * @return String
         */
        public String getMimeType() {
            return mimeType;
        }

    }

    public void uploadFailed(FailedEvent event) {
        System.out.println(event);

        System.out.println(event.getSource());

    }

    public void uploadSucceeded(SucceededEvent event) {
        pi.setVisible(false);
        pi2.setVisible(false);
        l.setValue("Finished upload, idle");
        System.out.println(event);
        setBuffer();
    }

    public void updateProgress(long readBytes, long contentLenght) {
        pi.setValue(new Float(readBytes / (float) contentLenght));

        refreshMemUsage();
    }

    private void refreshMemUsage() {
        memoryStatus.setValue("Not available in Java 1.4");
        /*
         * StringBuffer mem = new StringBuffer(); MemoryMXBean mmBean =
         * ManagementFactory.getMemoryMXBean(); mem.append("Heap (M):");
         * mem.append(mmBean.getHeapMemoryUsage().getUsed() / 1048576);
         * mem.append(" |锟絅on-Heap (M):");
         * mem.append(mmBean.getNonHeapMemoryUsage().getUsed() / 1048576);
         * memoryStatus.setValue(mem.toString());
         */
    }

    public void uploadStarted(StartedEvent event) {
        pi.setVisible(true);
        pi2.setVisible(true);
        l.setValue("Started uploading file " + event.getFilename());
        textFieldValue.setValue(" TestFields value at the upload start is:"
                + textField.getValue());
    }

}