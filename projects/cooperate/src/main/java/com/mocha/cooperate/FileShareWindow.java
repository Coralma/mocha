/**
 * 
 */
package com.mocha.cooperate;

import java.util.Map;

import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.UserFileService;
import com.mocha.cooperate.widget.listener.FileDownloadResource;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("serial")
public class FileShareWindow extends Window {

	protected String pageWidth = "980px";
	protected VerticalLayout layout = new VerticalLayout();
	protected UserFileService fileService = new UserFileService(null);
	
	public FileShareWindow() {
		setName("share");
		setTheme("cooperate");
		addStyleName("mocha-share-window");
//		addStyleName(getRandomBackground());
		
		layout.addStyleName("mocha-share");
		layout.setSizeFull();
		setContent(layout);

		addParameterHandler(new ShareParameterHandler());
	}
	
//	public String getRandomBackground() {
//		Double d = Math.ceil(Math.random() * 17);
//		Integer random = d.intValue();
//		String bgCss = "login-background-" + random;
//		return bgCss;
//	}
	
	public void buildShareFilePanel(final File file) {
		layout.removeAllComponents();
		VerticalLayout mainScreen = new VerticalLayout();
		mainScreen.setHeight("500px");
		mainScreen.setWidth(pageWidth);
		Label logo = new Label();
		logo.setCaption("Mocha");
		logo.setWidth("200px");
		logo.setHeight("30px");
		logo.setIcon(new ThemeResource("images/login-logo.png"));
		logo.addStyleName("mocha-logo");
		mainScreen.addComponent(logo);
		layout.addComponent(mainScreen);
		layout.setComponentAlignment(mainScreen, Alignment.TOP_CENTER);
		
		Panel filePanel = new Panel();
		filePanel.setCaption(file.getCreator().getRealName() +" Shared File");
		filePanel.setWidth("550px");
		filePanel.setHeight("260px");
		
		VerticalLayout fileArea = new VerticalLayout();
		fileArea.setMargin(true);
//		fileArea.addComponent(new Label(file.getName()));
		filePanel.setContent(fileArea);
		
		Label fileImage = new Label();
		fileImage.setIcon(new ThemeResource("icons/sharedFile.png"));
		fileImage.setWidth("128px");
		HorizontalLayout fileHLayout = new HorizontalLayout();
		fileHLayout.setSpacing(true);
		fileHLayout.addComponent(fileImage);
		
		VerticalLayout fileDetailLayout = new VerticalLayout();
		fileDetailLayout.addStyleName("file-detail-layout");
		fileDetailLayout.setSpacing(true);
		Label filename = new Label("<span style=\"font-weight: bold; font-size:16px;\">"+file.getName()+"</span>",Label.CONTENT_XHTML);
		fileDetailLayout.addComponent(filename);
		Label filetype = new Label("<span style=\"font-weight: bold; font-size:12px;\">File type</span>: " + file.getType(),Label.CONTENT_XHTML);
		fileDetailLayout.addComponent(filetype);
		Label filesize = new Label("<span style=\"font-weight: bold; font-size:12px;\">File size</span>: " + file.getSize() + " B",Label.CONTENT_XHTML);
		fileDetailLayout.addComponent(filesize);
		fileHLayout.addComponent(fileDetailLayout);
		fileArea.addComponent(fileHLayout);
		
		NativeButton downloadButton = new NativeButton("Download");
		downloadButton.addStyleName("download-button");
		downloadButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				downloadResource(true, file.getPath());				
			}
		});
		fileArea.addComponent(downloadButton);
		fileArea.setComponentAlignment(downloadButton, Alignment.MIDDLE_RIGHT);
		
		mainScreen.addComponent(filePanel);
		mainScreen.setComponentAlignment(filePanel, Alignment.MIDDLE_CENTER);
		
		String copyRightString = "© Copyright Mocha Inc., All Rights Reserved.";
		Label copyRightLabel = new Label("<span style=\"font-weight: normal;font-family: Arial;font-size:12px; color: gray;\">" + copyRightString + "</span>",Label.CONTENT_XHTML);
		copyRightLabel.setWidth("300px");
		mainScreen.addComponent(copyRightLabel);
		mainScreen.setComponentAlignment(copyRightLabel, Alignment.MIDDLE_CENTER);
	}
	
	public void buildErrorPanel() {
		layout.removeAllComponents();
		VerticalLayout mainScreen = new VerticalLayout();
		mainScreen.setHeight("500px");
		mainScreen.setWidth(pageWidth);
		Label logo = new Label();
		logo.setCaption("Mocha");
		logo.setWidth("200px");
		logo.setHeight("30px");
		logo.setIcon(new ThemeResource("images/login-logo.png"));
		logo.addStyleName("mocha-logo");
		mainScreen.addComponent(logo);
		layout.addComponent(mainScreen);
		layout.setComponentAlignment(mainScreen, Alignment.TOP_CENTER);
		
		HorizontalLayout errorLayout = new HorizontalLayout();
		errorLayout.setSpacing(true);
		
		Label errorImage = new Label();
		errorImage.setIcon(new ThemeResource("icons/file_warning.png"));
		errorImage.setWidth("64px");
		errorLayout.addComponent(errorImage);
		
		String errorMessage = "Your share link is not correct. Please confirm with the file sharer."; //FIXME I18N
		Label errorMsg = new Label("<span style=\"padding-top:20px;font-weight: bold; font-size:25px; text-shadow: 0 1px 0 #ffffff;\">"+errorMessage+"</span>",Label.CONTENT_XHTML);
		errorLayout.addComponent(errorMsg);
		errorLayout.setComponentAlignment(errorMsg, Alignment.MIDDLE_LEFT);
		
		mainScreen.addComponent(errorLayout);
		mainScreen.setComponentAlignment(errorLayout, Alignment.TOP_CENTER);
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
	
	class ShareParameterHandler implements ParameterHandler {
		@Override
		public void handleParameters(Map<String, String[]> parameters) {
			String[] keyParams = parameters.get("key");
			if (keyParams != null && keyParams.length > 0) {
				String key = keyParams[0];
				File file = fileService.findFileByShareKey(key);
				if(file == null) {
					buildErrorPanel();
				} else {
					buildShareFilePanel(file);
				}
			} else {
				buildErrorPanel();
			}
		}
		
	}
}
