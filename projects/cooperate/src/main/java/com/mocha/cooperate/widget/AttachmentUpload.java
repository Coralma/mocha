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

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.FileUtils;
import com.coral.foundation.utils.Message;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.widget.AttachmentLayout.AttachmentPanel;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Upload;

/**
 * @author Coral.Ma
 *
 */
public class AttachmentUpload extends Upload implements Upload.StartedListener,Upload.ProgressListener,Upload.SucceededListener,Upload.FailedListener,Upload.FinishedListener{
	
    protected AttachmentLayout attachmentLayout;
    protected Attachment attachment;
    protected AttachmentPanel attachmentPanel;
    protected BasicUser basicUser;
    
	public AttachmentUpload(AttachmentLayout attachmentLayout, BasicUser basicUser) {
		super();
		this.basicUser = basicUser;
		this.attachmentLayout = attachmentLayout;
		this.addStyleName("attachment-upload");
		this.setReceiver(new AttachmentReceiver());
		this.setImmediate(true);
		this.setButtonCaption("Add File");

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
    		AttachmentUpload.this.interruptUpload();
    		return;
    	}
        attachment = new Attachment();
        attachment.setFileName(event.getFilename());
        
        attachment.setFilePath(getAttachmentFolder() + event.getFilename());
        attachment.setType(event.getMIMEType());
        attachment.setFileSize(length);
        attachmentPanel = attachmentLayout.new AttachmentPanel(attachment);
        attachmentLayout.addAttachmentPanel(attachmentPanel);
	}
	
	@Override
	public void updateProgress(long readBytes, long contentLength) {
		if(attachmentPanel != null) {
    		attachmentPanel.getPi().setValue(new Float(readBytes / (float) contentLength));
    	}
	}
	
	public String getAttachmentFolder() {
		String attachmentFolder = SystemProperty.getUserAttachmentFolder(basicUser);
        FileUtils.createDir(attachmentFolder);
        return attachmentFolder;
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
     	if(attachmentPanel != null) {
     		attachmentPanel.getPi().setVisible(false);
     	}
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
            	file = new File(getAttachmentFolder() + fileName);
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
    }
}
