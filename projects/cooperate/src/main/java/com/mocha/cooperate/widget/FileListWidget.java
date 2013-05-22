/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.FileUtils;
import com.coral.foundation.utils.Message;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.UserFileService;
import com.mocha.cooperate.widget.listener.FileDownloadResource;
import com.mocha.cooperate.widget.listener.FileWidgetListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("serial")
public class FileListWidget extends VerticalLayout implements LayoutClickListener{

	private String widgetWidth=SystemProperty.content_page_width;
	private List<File> files;
	private String[] heads = {"file","type", "modified"};
	private String fileNameWidth = "300px";
	private String headHeight = "30px";
	private HorizontalLayout headLayout = new HorizontalLayout();
	private List<HorizontalLayout> fileLayouts = Lists.newArrayList();
	private HorizontalLayout selectedFileLayout;
	private Button shareLinkButton;
	private Button downloadButton;
	private Button deleteButton;
	private BasicUser currentUser;
	private UserFileService fileService;
	private FileWidgetListener fileWidgetListener;
	private boolean allowDelete = true;
	private Message message;
	
	public FileListWidget(List<File> files, BasicUser user) {
		this.files = files;
		this.currentUser = user;
		fileService = new UserFileService(user);
		addStyleName("file-widget");
		setSizeFull();
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		build(files);		
	}
	
	public void build(List<File> files) {
		removeAllComponents();
		addComponent(buildHead());
		for(File file : files) {
			addComponent(buildFileList(file));
		}
	}
	
	private HorizontalLayout buildHead() {
		headLayout.removeAllComponents();
		headLayout.addStyleName("file-head");
		headLayout.setWidth(widgetWidth);
		headLayout.setHeight(headHeight);
		for(int i=0; i < heads.length ; i++) {
			String head = "cooperate.file." + heads[i];
			Label headLabel = new Label(message.getString(head));
			headLayout.addComponent(headLabel);
			if(i==0) {
				headLabel.setWidth(fileNameWidth);
				headLayout.setExpandRatio(headLabel, 3);
			} else {
				headLayout.setExpandRatio(headLabel, 2);
			}
		}
		return headLayout;
	}
	
	private void buildHeadFunction(final File selectedFile) {
		headLayout.removeAllComponents();
		headLayout.addStyleName("file-head");
		headLayout.setWidth(widgetWidth);
		headLayout.setHeight(headHeight);
		
		HorizontalLayout controlLayout = new HorizontalLayout();
		controlLayout.setSpacing(true);
		controlLayout.addStyleName("file-control-bar");
		Label fileNameLabel = new Label(selectedFile.getName());
		fileNameLabel.addStyleName("selected-file-name");
		controlLayout.addComponent(fileNameLabel);
		
		if(!FileUtils.isFolder(selectedFile.getPhysicalType())) {
			shareLinkButton = new Button(message.getString("cooperate.share"));
			shareLinkButton.addStyleName(BaseTheme.BUTTON_LINK);
			shareLinkButton.setIcon(new ThemeResource("icons/link.png"));
			shareLinkButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					fileWidgetListener.shareFile(selectedFile);
				}
			});
			controlLayout.addComponent(shareLinkButton);
				
			downloadButton = new Button(message.getString("cooperate.download"));
			downloadButton.addStyleName(BaseTheme.BUTTON_LINK);
			downloadButton.setIcon(new ThemeResource("icons/download.png"));
			downloadButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					File file = getCurrentFile();
					downloadResource(true, file.getPath());
				}
			});
			controlLayout.addComponent(downloadButton);
		}
		deleteButton = new Button(message.getString("cooperate.delete"));
		deleteButton.addStyleName(BaseTheme.BUTTON_LINK);
		deleteButton.setIcon(new ThemeResource("icons/delete.png"));
		deleteButton.setVisible(allowDelete);
		deleteButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				fileWidgetListener.deleteFile(getCurrentFile());
//				fileService.delete(file);
//				build(fileService.loadFiles());
			}
		});
		controlLayout.addComponent(deleteButton);
		
		
		headLayout.addComponent(controlLayout);
	}

	private HorizontalLayout buildFileList(final File file) {
		HorizontalLayout fileListLayout = new HorizontalLayout();
		fileListLayout.addListener(this);
		fileListLayout.addStyleName("file-list");
		fileListLayout.setWidth(widgetWidth);
		fileListLayout.setData(file);

		Button fileLink = new Button(file.getName());
		if(SystemProperty.COMPANY_FOLDER.equals(file.getFileId())) {
			String companyFilesName = message.getString("cooperate.file.CompanyFiles");
			fileLink.setCaption(companyFilesName);
			file.setName(companyFilesName);
		}
		fileLink.addStyleName(BaseTheme.BUTTON_LINK);
		fileLink.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(FileUtils.isFolder(file.getPhysicalType())) {
					fileWidgetListener.folderClick(file);
				} else {
					downloadResource(true, file.getPath());
				}
			}
		});
		if(FileUtils.isFolder(file.getPhysicalType())) {
			if(file.getFileType() == 0) {
				fileLink.setIcon(new ThemeResource("icons/folder.png"));
			} else {
				fileLink.setIcon(new ThemeResource("icons/admin_folder.png"));
			}
		} else {
			fileLink.setIcon(new ThemeResource("icons/file.png"));
		}
		
		HorizontalLayout linkLayout = new HorizontalLayout();
		linkLayout.setWidth(fileNameWidth);
		linkLayout.addComponent(fileLink);
		fileListLayout.addComponent(linkLayout);
		fileListLayout.setExpandRatio(linkLayout, 3);
		
		String fileType = file.getType();
		if(fileType.equals("FOLDER") || fileType.equals("publicFolder")) {
			fileType = message.getString("cooperate.file."+ fileType);
		}
		Label typeLabel = new Label(fileType);
		fileListLayout.addComponent(typeLabel);
		fileListLayout.setComponentAlignment(typeLabel, Alignment.MIDDLE_LEFT);
		fileListLayout.setExpandRatio(typeLabel, 2);
		
		Label modifiedLabel = new Label();
		if(file.getLastModifiedTime() != null) {
			modifiedLabel.setValue(DateUtils.date2String(file.getLastModifiedTime()));
		}
//		Label modifiedLabel = new Label(DateUtils.date2String(file.getLastModifiedTime()));
		fileListLayout.addComponent(modifiedLabel);
		fileListLayout.setComponentAlignment(modifiedLabel, Alignment.MIDDLE_LEFT);
		fileListLayout.setExpandRatio(modifiedLabel, 2);
		fileLayouts.add(fileListLayout);
		return fileListLayout;
	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		if(event.getButton() == com.vaadin.event.MouseEvents.ClickEvent.BUTTON_LEFT){
			Object source = event.getSource();
			if(source instanceof HorizontalLayout) {
				HorizontalLayout newSelectedFileLayout = (HorizontalLayout)source;
				if(newSelectedFileLayout.equals(selectedFileLayout)) {
					buildHead();
					// change style
					selectedFileLayout.addStyleName("file-list");
					selectedFileLayout.removeStyleName("file-list-selected");
					selectedFileLayout = null;
				} else {
					selectedFileLayout = newSelectedFileLayout;
					File selectedFile = (File)selectedFileLayout.getData();
					buildHeadFunction(selectedFile);
					// change style
					selectedFileLayout.removeStyleName("file-list");
					selectedFileLayout.addStyleName("file-list-selected");
					for(HorizontalLayout otherLayout : fileLayouts) {
						if(!selectedFileLayout.equals(otherLayout)) {
							otherLayout.removeStyleName("file-list-selected");
							otherLayout.addStyleName("file-list");
						}
					}
					fileWidgetListener.folderRowClick(selectedFile);
				}
			}
		}
	}

	public File getCurrentFile() {
		if(selectedFileLayout != null) {
			return (File)selectedFileLayout.getData();
		}
		return null;
	}
	
	private void downloadResource(boolean isNewWindow, String filePath) {
		try {
			java.io.File file = new java.io.File(filePath);
			if(!file.isFile()) {
				return;
			}
			// another way to download
			FileDownloadResource fileResource = new FileDownloadResource(file, getApplication());
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
	 * @return the shareLinkButton
	 */
	public Button getShareLinkButton() {
		return shareLinkButton;
	}

	/**
	 * @return the downloadButton
	 */
	public Button getDownloadButton() {
		return downloadButton;
	}

	/**
	 * @return the deleteButton
	 */
	public Button getDeleteButton() {
		return deleteButton;
	}

	/**
	 * @return the fileWidgetListener
	 */
	public FileWidgetListener getFileWidgetListener() {
		return fileWidgetListener;
	}

	/**
	 * @param fileWidgetListener the fileWidgetListener to set
	 */
	public void setFileWidgetListener(FileWidgetListener fileWidgetListener) {
		this.fileWidgetListener = fileWidgetListener;
	}

	/**
	 * @return the allowDelete
	 */
	public boolean isAllowDelete() {
		return allowDelete;
	}

	/**
	 * @param allowDelete the allowDelete to set
	 */
	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
}
