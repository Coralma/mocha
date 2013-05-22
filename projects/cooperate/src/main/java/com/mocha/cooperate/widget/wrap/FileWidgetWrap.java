/**
 * 
 */
package com.mocha.cooperate.widget.wrap;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.foundation.utils.StrUtils;
import com.coral.foundation.utils.UUIDGenerater;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.service.CompanyFileService;
import com.mocha.cooperate.service.IFileService;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.service.UserFileService;
import com.mocha.cooperate.widget.FileUpload.FileListener;
import com.mocha.cooperate.widget.FileWidget;
import com.mocha.cooperate.widget.NotifyTokenField;
import com.mocha.cooperate.widget.listener.BreadcrumbListener;
import com.mocha.cooperate.widget.listener.FileWidgetListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

public class FileWidgetWrap {
	
	private FileWidget fileWidget;
	private IFileService fileService;
	private boolean isCompany = false;
	private BasicUser user;
	private String accountName;
	private String currentFolder;
	
	public FileWidgetWrap(BasicUser user) {
		this.user = user;
		this.build();
	}
	
	public FileWidgetWrap(String accountName) {
		this.accountName = accountName; 
		this.isCompany = true;
		this.build();
	}
	
	public void build() {
		if(isCompany) {
			fileService = new CompanyFileService(accountName);
		} else {
			fileService = new UserFileService(user);
		}
		String defaultFolder = fileService.createDefaultPhysicalFolder();
		List<File> files = fileService.loadFiles();
		
		fileWidget = new FileWidget(files, user);
		fileWidget.setCurrentFolder(defaultFolder);
		fileWidget.getFileUpload().setFileListener(new FileListener() {
			@Override
			public void upload(File uploadFile) {
				uploadFile.setParentID(fileWidget.getCurrentFolderId());
				uploadFile.setCreator((BasicUser) fileWidget.getApplication().getUser());
				fileService.createFile(uploadFile);
				reloadFileList(fileWidget.getCurrentFolderId());
			}
		});
		fileWidget.getNewFolderButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final CreateFolderWindow createFolderWindow = new CreateFolderWindow();
				fileWidget.getWindow().addWindow(createFolderWindow);
				createFolderWindow.addListener(new Window.CloseListener() {
					@Override
					public void windowClose(CloseEvent e) {
						String folderName = createFolderWindow.getFolderName();
						if(folderName != null) {
							File file = new File();
							file.setParentID(fileWidget.getCurrentFolderId());
							file.setName(folderName);
							file.setCreator((BasicUser) fileWidget.getApplication().getUser());
							file.setPath(fileWidget.getCurrentFolder() + "/" + folderName);
							fileService.createFolder(file);
							reloadFileList(fileWidget.getCurrentFolderId());
						}
					}
				});
			}
		});
		fileWidget.getSearchField().addShortcutListener(new ShortcutListener("EnterEdit", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
            	TextField field = (TextField)target;
                String value = (String)field.getValue();
                if(!StrUtils.isEmpty(value)) {
                	List<File> files = fileService.fuzzySearchFile(value);
                	fileWidget.getFileListWidget().build(files);
                	fileWidget.getBreadCrumb().addStep("Search Result", SystemProperty.SEARCH_FOLDER);
                }
            }
        });
		fileWidget.getBreadCrumb().setBreadcrumbListener(new BreadcrumbListener() {
			@Override
			public void stepClick(BreadcrumbEvent event) {
				Long folderID = event.getStepID();
				String folderPath;
				if(folderID != null) {
					File folder = fileService.loadFileById(folderID);
					folderPath = folder.getPath();
				} else {
					folderPath = fileService.createDefaultPhysicalFolder();
				}
				reloadFileList(folderID);
				fileWidget.setCurrentFolder(folderPath);
				fileWidget.setCurrentFolderId(folderID);
			}
		});
		fileWidget.setFileWidgetListener(new FileWidgetListener() {
			@Override
			public void folderClick(File folder) {
				Long folderID = folder.getID();
				reloadFileList(folderID);
				fileWidget.setCurrentFolder(folder.getPath());
				fileWidget.setCurrentFolderId(folderID);
				fileWidget.getBreadCrumb().addStep(folder.getName(),folderID);
			}

			@Override
			public void shareFile(File file) {
				if(file.getShareKey() == null) {
					file.setShareKey(UUIDGenerater.generate());
					file.setShareDate(new Date());
					fileService.updateFile(file);
				}
				String url = fileWidget.getApplication().getURL().toString() + "share?key=" + file.getShareKey();
				ShareFileWindow shareFileWindow = new ShareFileWindow(file.getName(),url);
				fileWidget.getWindow().addWindow(shareFileWindow);
			}

			@Override
			public void deleteFile(File file) {
				fileService.delete(file);
				reloadFileList(fileWidget.getCurrentFolderId());
			}

			@Override
			public void folderRowClick(File file) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void reloadFileList(Long folderID) {
		List<File> files = fileService.loadFiles(folderID);
		fileWidget.getFileListWidget().build(files);
	}
	
	/**
	 * @param currentFolder the currentFolder to set
	 */
	public void setCurrentFolder(String currentFolder) {
		this.currentFolder = currentFolder;
		fileWidget.getFileUpload().setCurrentFolder(currentFolder);
	}
	
	public FileWidget getFileWidget() {
		return fileWidget;
	}
	

	public static class CreateFolderWindow extends ConfirmDialog {
		private TextField folderNameField;
		private String buttonWidth ="80px";
		private String folderName;
		private Message message;
		
		public CreateFolderWindow() {
			setWidth("360px");
			center();
		    setModal(true);
		    setResizable(false);
		    setResizeLazy(false);
		    addStyleName(Reindeer.WINDOW_LIGHT);
		    addStyleName("mocha-app");
		}
		
		public void attach() {
			message = new Message(getApplication().getLocale());
			setCaption(message.getString("cooperate.file.createNewFolder"));
		    VerticalLayout layout = new VerticalLayout();
		    layout.setSizeFull();
		    layout.setSpacing(true);
		    addComponent(layout);
		    // create form
		    FormLayout form = new FormLayout();
		    folderNameField = new TextField(message.getString("cooperate.file.newFolderName"));
		    folderNameField.setWidth("200px");
		    folderNameField.setRequired(true);
		    form.addComponent(folderNameField);
		    layout.addComponent(form);
		    
		    final NativeButton btnConfirm = new NativeButton(message.getString("cooperate.create"), new Button.ClickListener() {
		        @Override
		        public void buttonClick(ClickEvent event) {
		        	folderNameField.validate();
		        	folderName = (String) folderNameField.getValue();
		        	CreateFolderWindow.this.close();
		        }
		      });
		    btnConfirm.addStyleName("mocha-button");
		    btnConfirm.setWidth(buttonWidth);
		    NativeButton btnCancel = new NativeButton(message.getString("cooperate.cancel"), new Button.ClickListener() {
		        @Override
		        public void buttonClick(ClickEvent event) {
		        	folderName = null;
		        	CreateFolderWindow.this.close();
		        }
		      });
		    btnCancel.addStyleName("mocha-button");
		    btnCancel.setWidth(buttonWidth);
		    HorizontalLayout buttons = new HorizontalLayout();
		    buttons.setSpacing(true);
		    buttons.addComponent(btnConfirm);
		    buttons.addComponent(btnCancel);
		    layout.addComponent(buttons);
		    layout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
		}

		/**
		 * @return the folderName
		 */
		public String getFolderName() {
			return folderName;
		}

		/**
		 * @param folderName the folderName to set
		 */
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}
	}
	
	public static class ShareFileWindow extends ConfirmDialog {
		private TimeLineService service = new TimeLineService();
		private TextArea shareInputArea = new TextArea();
		private NotifyTokenField tokenField = new NotifyTokenField();
		private String buttonWidth ="80px";
		private Message message;
		private String fileName;
		private String url;
		
		public ShareFileWindow(String fileName, String url) {
			this.fileName = fileName;
			this.url = url;
			setCaption("Share the " + fileName);
			setWidth("530px");
			center();
		    setModal(true);
		    setResizable(true);
		    addStyleName(Reindeer.WINDOW_LIGHT);
		}
		
		public void attach() {
			message = new Message(getApplication().getLocale());
		    VerticalLayout layout = new VerticalLayout();
		    layout.setWidth("100%");
		    layout.setSpacing(true);
		    addComponent(layout);
		    
		    shareInputArea.setWidth("490px");
		    shareInputArea.setRows(4);
		    shareInputArea.setValue(getShareMessage(fileName, url));
		    layout.addComponent(shareInputArea);
		    
		    HorizontalLayout controlLayout = new HorizontalLayout();
		    controlLayout.setWidth("100%");
			tokenField.setInputPrompt(message.getString("cooperate.publisher.NotificateOthers"));
			tokenField.setWidth("300px");
			controlLayout.addComponent(tokenField);
			controlLayout.setComponentAlignment(tokenField, Alignment.BOTTOM_LEFT);
			
		    Button submitCancelBtn = new Button("Submit", new Button.ClickListener() {
		    	@Override
		        public void buttonClick(ClickEvent event) {
		    		Status status = new Status();
		    		status.setContent((String) shareInputArea.getValue());
		    		service.saveStatus(status,(BasicUser) getApplication().getUser(),(Set<BasicUser>)tokenField.getValue(),null);
		        	ShareFileWindow.this.close();
		    	}
		    });
		    Button cancelBtn = new Button("Close", new Button.ClickListener() {
		        @Override
		        public void buttonClick(ClickEvent event) {
		        	ShareFileWindow.this.close();
		        }
		    });
		    cancelBtn.setWidth(buttonWidth);
		    HorizontalLayout buttons = new HorizontalLayout();
		    buttons.setSpacing(true);
		    buttons.addComponent(submitCancelBtn);
		    buttons.addComponent(cancelBtn);
		    controlLayout.addComponent(buttons);
		    controlLayout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		    layout.addComponent(controlLayout);
		}
		
		public String getShareMessage(String fileName, String url) {
			return "Share the file " + fileName + " by the link " + url + " ";
		}
	}

}

