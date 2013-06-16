/**
 * 
 */
package com.mocha.cooperate.widget;

import java.io.File;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.service.UserFileService;
import com.mocha.cooperate.widget.listener.FileDownloadResource;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral.Ma
 *
 */
public class AttachmentLayout extends VerticalLayout {

	private static final long serialVersionUID = -1969759545672129384L;
	private List<Attachment> attachments = Lists.newArrayList();
	private UserFileService fileService;
	private TimeLineService timeLineService;
	private Message message;
	private BasicUser basicUser;
	private String attachPanelWidth = SystemProperty.content_widget_width;
	
	public AttachmentLayout(BasicUser basicUser) {
		this.basicUser = basicUser;
		fileService = new UserFileService(basicUser);
		timeLineService = new TimeLineService();
		this.setSizeFull();
	}
	
	public void addDisplayAttachment(Attachment attachment) {
		attachments.add(attachment);
		AttachmentDisplayPanel displayPanel = new AttachmentDisplayPanel(attachment);
		addComponent(displayPanel);
	}
	
	public void addAttachmentPanel(AttachmentPanel attachmentPanel) {
		attachments.add(attachmentPanel.getAttachment());
		addComponent(attachmentPanel);
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}
	
	public void clean() {
		removeAllComponents();
		attachments = Lists.newArrayList();
	}
	
	public class AttachmentPanel extends HorizontalLayout {
		
		protected ProgressIndicator pi = new ProgressIndicator();
		protected Label fileName = new Label();
		protected NativeButton delete = new NativeButton("X");
		protected Attachment attachment;
		
		public AttachmentPanel(final Attachment attachment) {
			this.setWidth(attachPanelWidth);
			this.attachment = attachment;
			this.addStyleName("attachment-panel");
		}

		@Override
		public void attach() {	
			fileName.setCaption(attachment.getFileName());
			fileName.setIcon(new ThemeResource("icons/file_icon.png"));
			addComponent(fileName);
			
			pi.setValue(0f);
			if(attachment.getID() != null) {
				pi.setVisible(false);
			} else {
				pi.setVisible(true);
			}
			pi.setPollingInterval(500);
			addComponent(pi);
			
			delete.setWidth("20px");
			delete.addStyleName("attachment-panel-remove");
			delete.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					fileService.removeAttachment(attachment, (BasicUser)getApplication().getUser());
					// if the attachment already store in database then we have to remove the relationship
					if(attachment.getID() != null) {
						timeLineService.removeAttachment(attachment);
					}
					attachments.remove(attachment);
					AttachmentLayout.this.removeComponent(AttachmentPanel.this);
				}
			});
			addComponent(delete);
			
			setExpandRatio(fileName, 100);
			setExpandRatio(pi, 100);
			setExpandRatio(delete, 1);
		}

		/**
		 * @return the attachment
		 */
		public Attachment getAttachment() {
			return attachment;
		}

		/**
		 * @param attachment the attachment to set
		 */
		public void setAttachment(Attachment attachment) {
			this.attachment = attachment;
		}

		/**
		 * @return the pi
		 */
		public ProgressIndicator getPi() {
			return pi;
		}
	}
	
	public class AttachmentDisplayPanel extends VerticalLayout implements LayoutClickListener,com.vaadin.event.MouseEvents.ClickListener,ClickListener {
		
		protected Label fileName = new Label();
		protected Attachment attachment;
		protected boolean isBigImage = false;
		protected Embedded image;
		protected Panel imagePanel;
		protected HorizontalLayout imageFunctionLayout;
		protected VerticalLayout imageLayout;
		protected String image_default_width = "120px";
		
		public AttachmentDisplayPanel(final Attachment attachment) {
			this.setWidth("100%");
			this.attachment = attachment;
		}

		@Override
		public void attach() {
			File downloadFile = fileService.getFile(attachment, basicUser);
			if(downloadFile == null) {
				return;
			}
			if(attachment.getType().startsWith("image")) {
				// function button.
				imageFunctionLayout = new HorizontalLayout();
				imageFunctionLayout.addStyleName("action-layout");
				Button hidePhoto = new Button(message.getString("cooperate.attachment.Hide"));
				imageFunctionLayout.setSpacing(true);
				hidePhoto.addStyleName(BaseTheme.BUTTON_LINK);
				hidePhoto.setIcon(new ThemeResource("icons/hide_icon.png"));
				hidePhoto.setData("hide");
				hidePhoto.addListener(this);
				imageFunctionLayout.addComponent(hidePhoto);
				Button fullPhoto = new Button(message.getString("cooperate.attachment.Download"));
				fullPhoto.addStyleName(BaseTheme.BUTTON_LINK);
				fullPhoto.setIcon(new ThemeResource("icons/download_icon.png"));
				fullPhoto.setData("download");
				fullPhoto.addListener(this);
				imageFunctionLayout.addComponent(fullPhoto);
				imageFunctionLayout.setVisible(false);
				
				imageLayout = new VerticalLayout();
				imageLayout.addComponent(imageFunctionLayout);
				image = PageBuildHelper.buildUserPhoto(downloadFile, getApplication());
				image.addListener((com.vaadin.event.MouseEvents.ClickListener)this);
				image.setWidth(image_default_width);
				imageLayout.addComponent(image);
				imagePanel = new Panel();
				imagePanel.addStyleName(Reindeer.PANEL_LIGHT);
				imagePanel.addStyleName("image-attachment-panel");
				imagePanel.setScrollable(true);
				imagePanel.setSizeFull();
				imagePanel.setContent(imageLayout);
				imagePanel.getContent().setSizeUndefined();
				((AbstractLayout)imagePanel.getContent()).setMargin(false);
				addComponent(imagePanel);
			} else {
				this.addStyleName("attachment-display_panel");
				this.addListener(this);
				fileName.setCaption(attachment.getFileName());
				fileName.setIcon(new ThemeResource("icons/file_icon.png"));
				addComponent(fileName);
			}
		}
		
		@Override
		public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
//			if(attachment.getType().startsWith("image")) {
//		        if(!isBigImage) {
//		        	imageFunctionLayout.setVisible(true);
//		        	image.setWidth(null);
//		        	isBigImage= true;
//		        } else {
//		        	imageFunctionLayout.setVisible(false);
//		        	image.setWidth(image_default_width);
//		        	isBigImage= false;
//		        }
//			}
			checkImageStatus();
		}
		
		@Override
		public void buttonClick(ClickEvent event) {
			if("hide".equals(event.getButton().getData())) {
				checkImageStatus();
			} else if("download".equals(event.getButton().getData())) {
				downloadResource(true);
			}
		}
		
		@Override
		public void layoutClick(LayoutClickEvent event) {
//			try {
//				// another way to download
//				FileDownloadResource fileResource = new FileDownloadResource(fileService.getFile(attachment),getApplication());
//				event.getComponent().getWindow().open(fileResource,"_top");
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
			downloadResource(false);
		}
		
		private void checkImageStatus() {
			if(!isBigImage) {
	        	imageFunctionLayout.setVisible(true);
	        	image.setWidth(null);
	        	isBigImage= true;
	        } else {
	        	imageFunctionLayout.setVisible(false);
	        	image.setWidth(image_default_width);
	        	isBigImage= false;
	        }
		}

		private void downloadResource(boolean isNewWindow) {
			try {
				// another way to download
				FileDownloadResource fileResource = new FileDownloadResource(fileService.getFile(attachment, basicUser),getApplication());
				if(isNewWindow) {
					getWindow().open(fileResource,"_blank");
				} else {
					getWindow().open(fileResource,"_top");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * @return the attachment
		 */
		public Attachment getAttachment() {
			return attachment;
		}

		/**
		 * @param attachment the attachment to set
		 */
		public void setAttachment(Attachment attachment) {
			this.attachment = attachment;
		}
	}
	
//	public class FileDownloadResource extends FileResource {
//
//		public FileDownloadResource(File sourceFile, Application application) {
//			super(sourceFile, application);
//		}
//		
//		public DownloadStream getStream() {
//	        try {
//	            final DownloadStream ds = new DownloadStream(new FileInputStream(
//	            		getSourceFile()),  "application/octet-stream", getFilename());
////	            ds.setParameter("Content-Disposition", "attachment; filename=" + URLEncoder.encode(getFilename(),"utf-8"));
//	            ds.setParameter("Content-Disposition", "attachment; filename=" + new String(getFilename().getBytes("gb2312"),"iso8859-1"));
//	            ds.setCacheTime(0);
//	            return ds;
//	        } catch (final Exception e) {
//	            return null;
//	        }
//	    }
//	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * @return the attachPanelWidth
	 */
	public String getAttachPanelWidth() {
		return attachPanelWidth;
	}

	/**
	 * @param attachPanelWidth the attachPanelWidth to set
	 */
	public void setAttachPanelWidth(String attachPanelWidth) {
		this.attachPanelWidth = attachPanelWidth;
	}
}
