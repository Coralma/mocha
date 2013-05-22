/**
 * 
 */
package com.mocha.cooperate.widget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.widget.AttachmentLayout.AttachmentPanel;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Upload;

/**
 * @author Coral.Ma
 *
 */
public class FileUpload extends Upload implements Upload.StartedListener,Upload.ProgressListener,Upload.SucceededListener,Upload.FailedListener,Upload.FinishedListener{
	
    protected com.mocha.cooperate.model.File uploadFile;
    protected FileListener fileListener;
    protected String currentFolder = SystemProperty.FILE_PATH;
    protected AttachmentReceiver attachmentReceiver = new AttachmentReceiver();
    
	public FileUpload() {
		super();
//		this.addStyleName("attachment-upload");
		this.setReceiver(attachmentReceiver);
		this.setImmediate(true);
		this.setButtonCaption("Upload");
		
		this.addListener((FinishedListener) this);
		this.addListener((FailedListener) this);
		this.addListener((SucceededListener) this);
		this.addListener((StartedListener) this);
		this.addListener((ProgressListener) this);
	}

	@Override
	public void uploadStarted(StartedEvent event) {
		long length = event.getContentLength();
		String fileName = event.getFilename();
		long fileNameLength = fileName.length();
    	if(length > SystemProperty.file_limited_Size || fileNameLength > SystemProperty.file_name_limited_Length) {
    		// TODO Add size too big message.
    		FileUpload.this.interruptUpload();
    		return;
    	}
    	uploadFile = new com.mocha.cooperate.model.File();
    	uploadFile.setName(event.getFilename());
    	uploadFile.setPath(currentFolder + event.getFilename());
    	uploadFile.setSize(length);
    	if(fileListener != null) {
    		fileListener.upload(uploadFile);
    	}
//        uploadFile = new Attachment();
//        uploadFile.setFileName(event.getFilename());
//        uploadFile.setFilePath(SystemProperty.USER_PHOTO_PATH + event.getFilename());
//        uploadFile.setType(event.getMIMEType());
//        uploadFile.setFileSize(length);
//        attachmentPanel = attachmentLayout.new AttachmentPanel(uploadFile);
//        attachmentLayout.addAttachmentPanel(attachmentPanel);
	}
	
	@Override
	public void updateProgress(long readBytes, long contentLength) {
//		if(attachmentPanel != null) {
//    		attachmentPanel.getPi().setValue(new Float(readBytes / (float) contentLength));
//    	}
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadFailed(FailedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void uploadFinished(FinishedEvent event) {
//     	if(attachmentPanel != null) {
//     		attachmentPanel.getPi().setVisible(false);
//     	}
	}
	
    public interface Buffer extends StreamResource.StreamSource,Upload.Receiver {
    	String getFileName();
	}
	
	public class AttachmentReceiver implements Buffer {
        private String fileName;
        private String mtype;
        private int counter;
        private int total;
        private boolean sleep;
        private File file;
        private String uploadFolder;
        
        public AttachmentReceiver() {
        }

        /**
         * return an OutputStream that simply counts lineends
         */
        public OutputStream receiveUpload(String filename, String MIMEType) {
            counter = 0;
            total = 0;
            fileName = filename;
            mtype = MIMEType;
            try {
            	file = new File(uploadFolder + fileName);
                return new FileOutputStream(file);
            } catch (final FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        
        @Override
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

        public String getFileName() {
            return fileName;
        }

        public String getMimeType() {
            return mtype;
        }

        public int getLineBreakCount() {
            return counter;
        }

        public void setSlow(boolean value) {
            sleep = value;
        }

		/**
		 * @return the uploadFolder
		 */
		public String getUploadFolder() {
			return uploadFolder;
		}

		/**
		 * @param uploadFolder the uploadFolder to set
		 */
		public void setUploadFolder(String uploadFolder) {
			this.uploadFolder = uploadFolder;
		}
    }
	
	public interface FileListener {
		public void upload(com.mocha.cooperate.model.File uploadFile);
	}

	/**
	 * @param fileListener the fileListener to set
	 */
	public void setFileListener(FileListener fileListener) {
		this.fileListener = fileListener;
	}

	/**
	 * @return the currentFolder
	 */
	public String getCurrentFolder() {
		return currentFolder;
	}

	/**
	 * @param currentFolder the currentFolder to set
	 */
	public void setCurrentFolder(String currentFolder) {
		this.currentFolder = currentFolder;
		attachmentReceiver.setUploadFolder(currentFolder);
	}
}
