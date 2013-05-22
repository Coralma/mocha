/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.Date;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.foundation.utils.StrUtils;
import com.coral.foundation.utils.UUIDGenerater;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.common.collect.Lists;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.CompanyFileService;
import com.mocha.cooperate.service.UserFileService;
import com.mocha.cooperate.widget.FileUpload.FileListener;
import com.mocha.cooperate.widget.listener.BreadcrumbListener;
import com.mocha.cooperate.widget.listener.FileWidgetListener;
import com.mocha.cooperate.widget.wrap.FileWidgetWrap.CreateFolderWindow;
import com.mocha.cooperate.widget.wrap.FileWidgetWrap.ShareFileWindow;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("serial")
public class FilePresenter extends CommonPresenter implements Presenter {

	private UserFileService fileService;
	private CompanyFileService companyFileService;
	private Message message;
	private BasicUser user;
	public FilePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.user = eventBus.getUser();
		fileService = new UserFileService(user);
		companyFileService = new CompanyFileService(user.getAccount().getName());
		String defaultFolder = fileService.createDefaultPhysicalFolder();
		List<File> files = fileService.loadFiles();
		// init folder to add the company folder.
		initRootFolder(files, user.getAccount().getName());
		this.viewer = new FileViewer(files);
		((FileViewer)viewer).setCurrentFolder(defaultFolder);
	}
	
	private String getAccountName() {
		return user.getAccount().getName();
	}

	@Override
	public void bind() {
		final FileViewer fileViewer = (FileViewer)viewer;
		message = new Message(fileViewer.getApplication().getLocale());
		
		// upload action
		fileViewer.getFileUpload().setFileListener(new FileListener() {
			@Override
			public void upload(File uploadFile) {
				uploadFile.setParentID(fileViewer.getCurrentFolderId());
				uploadFile.setCreator((BasicUser) fileViewer.getApplication().getUser());
				fileService.createFile(uploadFile);
				reloadFileList(fileViewer.getCurrentFolderId());
			}
		});
		fileViewer.getNewFolderButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final CreateFolderWindow createFolderWindow = new CreateFolderWindow();
				fileViewer.getWindow().addWindow(createFolderWindow);
				createFolderWindow.addListener(new Window.CloseListener() {
					@Override
					public void windowClose(CloseEvent e) {
						String folderName = createFolderWindow.getFolderName();
						if(folderName != null) {
							File file = new File();
							file.setParentID(fileViewer.getCurrentFolderId());
							file.setName(folderName);
							file.setCreator((BasicUser) fileViewer.getApplication().getUser());
							file.setPath(fileViewer.getCurrentFolder() + "/" + folderName);
							fileService.createFolder(file);
							reloadFileList(fileViewer.getCurrentFolderId());
						}
					}
				});
			}
		});
		fileViewer.getSearchField().addShortcutListener(new ShortcutListener("EnterEdit", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                TextField field = (TextField)target;
                String value = (String)field.getValue();
                if(!StrUtils.isEmpty(value)) {
                	List<File> files = fileService.fuzzySearchFile(value);
                	fileViewer.getFileWidget().build(files);
                	fileViewer.getBreadCrumb().addStep("Search Result", SystemProperty.SEARCH_FOLDER);
                }
            }
        });
		fileViewer.getBreadCrumb().setBreadcrumbListener(new BreadcrumbListener() {
			@Override
			public void stepClick(BreadcrumbEvent event) {
				Long folderID = event.getStepID();
				String folderPath;
				if(folderID != null) {
					if(folderID == SystemProperty.COMPANY_FOLDER) {
						folderPath = SystemProperty.getCompanyFolder(getAccountName());
					} else {
						File folder = fileService.loadFileById(folderID);
						folderPath = folder.getPath();
					}
				} else {
					folderPath = fileService.createDefaultPhysicalFolder();
				}
//				reloadFileList(folderID);
				reloadFileByFolderID(folderID);
				fileViewer.setCurrentFolder(folderPath);
				fileViewer.setCurrentFolderId(folderID);
			}
		});
		fileViewer.getFileWidget().setFileWidgetListener(new FileWidgetListener() {
			@Override
			public void folderClick(File folder) {
				Long folderID = folder.getID();
				if(folderID == SystemProperty.COMPANY_FOLDER && folder.getFileType() == 1) {
					reloadCompanyFileList(folderID);
				} else {
					reloadFileList(folderID);
				}
				fileViewer.setCurrentFolder(folder.getPath());
				fileViewer.setCurrentFolderId(folderID);
				fileViewer.getBreadCrumb().addStep(folder.getName(),folderID);
			}

			@Override
			public void shareFile(File file) {
				if(file.getShareKey() == null) {
					file.setShareKey(UUIDGenerater.generate());
					file.setShareDate(new Date());
					fileService.updateFile(file);
				}
				String url = fileViewer.getApplication().getURL().toString() + "share?key=" + file.getShareKey();
				ShareFileWindow shareFileWindow = new ShareFileWindow(file.getName(),url);
				fileViewer.getWindow().addWindow(shareFileWindow);
			}

			@Override
			public void deleteFile(File file) {
				fileService.delete(file);
				reloadFileByFolderID(fileViewer.getCurrentFolderId());
			}

			@Override
			public void folderRowClick(File file) {
				if(file.getID() == SystemProperty.COMPANY_FOLDER) {
					fileViewer.getFileWidget().getDeleteButton().setVisible(false);
				}
			}
		});
	}
	
	private void reloadFileByFolderID(Long folderID) {
		if(folderID == SystemProperty.COMPANY_FOLDER) {
			reloadCompanyFileList(folderID);
		} else {
			reloadFileList(folderID);
		}
	}
	
	public void reloadFileList(Long folderID) {
		List<File> files = fileService.loadFiles(folderID);
		if(folderID == null) {
			initRootFolder(files, user.getAccount().getName());
			((FileViewer)viewer).setFileEditDisplay(true);
		}
		((FileViewer)viewer).getFileWidget().build(files);
	}
	
	public void reloadCompanyFileList(Long folderID) {
		List<File> files = Lists.newArrayList();
		if(folderID == SystemProperty.COMPANY_FOLDER) {
			files = companyFileService.loadFiles(null);
			((FileViewer)viewer).setFileEditDisplay(false);
		} else {
			files = companyFileService.loadFiles(folderID);
		}
		((FileViewer)viewer).getFileWidget().build(files);
	}

	public void initRootFolder(List<File> files, String accountName) {
		File companyFolder = new File();
		companyFolder.setFileId(SystemProperty.COMPANY_FOLDER);
		companyFolder.setPhysicalType(new Long(1));
		companyFolder.setFileType(new Long(1));
		companyFolder.setName("Company Files");
		companyFolder.setType("publicFolder");
		companyFolder.setPath(SystemProperty.getCompanyFolder(accountName));
		files.add(0, companyFolder);
	}
	
	@Override
	public String getPresenterName() {
		return PresenterProperty.FILE;
	}

//	public class CreateFolderWindow extends ConfirmDialog {
//		
//		private TextField folderNameField;
//		private String buttonWidth ="80px";
//		private String folderName;
//		private Message message;
//		
//		public CreateFolderWindow() {
//			setWidth("360px");
//			center();
//		    setModal(true);
//		    setResizable(false);
//		    setResizeLazy(false);
//		    addStyleName(Reindeer.WINDOW_LIGHT);
//		    addStyleName("mocha-app");
//		}
//		
//		public void attach() {
//			message = new Message(getApplication().getLocale());
//			setCaption(message.getString("cooperate.file.createNewFolder"));
//		    VerticalLayout layout = new VerticalLayout();
//		    layout.setSizeFull();
//		    layout.setSpacing(true);
//		    addComponent(layout);
//		    // create form
//		    FormLayout form = new FormLayout();
//		    folderNameField = new TextField(message.getString("cooperate.file.newFolderName"));
//		    folderNameField.setWidth("200px");
//		    folderNameField.setRequired(true);
//		    form.addComponent(folderNameField);
//		    layout.addComponent(form);
//		    
//		    final NativeButton btnConfirm = new NativeButton(message.getString("cooperate.create"), new Button.ClickListener() {
//		        @Override
//		        public void buttonClick(ClickEvent event) {
//		        	folderNameField.validate();
//		        	folderName = (String) folderNameField.getValue();
//		        	CreateFolderWindow.this.close();
//		        }
//		      });
//		    btnConfirm.addStyleName("mocha-button");
//		    btnConfirm.setWidth(buttonWidth);
//		    NativeButton btnCancel = new NativeButton(message.getString("cooperate.cancel"), new Button.ClickListener() {
//		        @Override
//		        public void buttonClick(ClickEvent event) {
//		        	folderName = null;
//		        	CreateFolderWindow.this.close();
//		        }
//		      });
//		    btnCancel.addStyleName("mocha-button");
//		    btnCancel.setWidth(buttonWidth);
//		    HorizontalLayout buttons = new HorizontalLayout();
//		    buttons.setSpacing(true);
//		    buttons.addComponent(btnConfirm);
//		    buttons.addComponent(btnCancel);
//		    layout.addComponent(buttons);
//		    layout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
//		}
//
//		/**
//		 * @return the folderName
//		 */
//		public String getFolderName() {
//			return folderName;
//		}
//
//		/**
//		 * @param folderName the folderName to set
//		 */
//		public void setFolderName(String folderName) {
//			this.folderName = folderName;
//		}
//	}
//	
//	public class ShareFileWindow extends ConfirmDialog {
//		private TimeLineService service = new TimeLineService();
//		private TextArea shareInputArea = new TextArea();
//		private NotifyTokenField tokenField = new NotifyTokenField();
//		
//		private String buttonWidth ="80px";
//		
//		public ShareFileWindow(String fileName, String url) {
//			setCaption("Share the " + fileName);
//			setWidth("530px");
//			center();
//		    setModal(true);
//		    setResizable(true);
//		    addStyleName(Reindeer.WINDOW_LIGHT);
//		    
//		    VerticalLayout layout = new VerticalLayout();
//		    layout.setWidth("100%");
//		    layout.setSpacing(true);
//		    addComponent(layout);
//		    
//		    shareInputArea.setWidth("490px");
//		    shareInputArea.setRows(4);
//		    shareInputArea.setValue(getShareMessage(fileName, url));
//		    layout.addComponent(shareInputArea);
//		    
//		    HorizontalLayout controlLayout = new HorizontalLayout();
//		    controlLayout.setWidth("100%");
//			tokenField.setInputPrompt(message.getString("cooperate.publisher.NotificateOthers"));
//			tokenField.setWidth("300px");
//			controlLayout.addComponent(tokenField);
//			controlLayout.setComponentAlignment(tokenField, Alignment.BOTTOM_LEFT);
//			
//		    Button submitCancelBtn = new Button("Submit", new Button.ClickListener() {
//		    	@Override
//		        public void buttonClick(ClickEvent event) {
//		    		Status status = new Status();
//		    		status.setContent((String) shareInputArea.getValue());
//		    		service.saveStatus(status,(BasicUser) getApplication().getUser(),(Set<BasicUser>)tokenField.getValue(),null);
//		        	ShareFileWindow.this.close();
//		    	}
//		    });
//		    Button cancelBtn = new Button("Close", new Button.ClickListener() {
//		        @Override
//		        public void buttonClick(ClickEvent event) {
//		        	ShareFileWindow.this.close();
//		        }
//		    });
//		    cancelBtn.setWidth(buttonWidth);
//		    HorizontalLayout buttons = new HorizontalLayout();
//		    buttons.setSpacing(true);
//		    buttons.addComponent(submitCancelBtn);
//		    buttons.addComponent(cancelBtn);
//		    controlLayout.addComponent(buttons);
//		    controlLayout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
//		    layout.addComponent(controlLayout);
//		}
//		
//		public String getShareMessage(String fileName, String url) {
//			return "Share the file " + fileName + " by the link " + url + " ";
//		}
//	}
	
}
 