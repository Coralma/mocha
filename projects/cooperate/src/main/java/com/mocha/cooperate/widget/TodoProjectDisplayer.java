/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.Date;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.StrUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.InnerStyle;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.listener.TodoListener;
import com.mocha.cooperate.widget.listener.TodoProjectDisplayerListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;

public class TodoProjectDisplayer extends VerticalLayout {

	private ToDo toDo;
	private String contentWidth = SystemProperty.content_page_width;
	private String taskWidth = "740px";
	private String subTaskWidth = "710px";
	
	private Button expendIcon = new Button();
	private CheckBox titleBox;
	private Label titleContent;
	
	private VerticalLayout subTasksLayout = new VerticalLayout();
	private boolean owner = false;
	private boolean needExpend = true;
	private boolean specialRowStyle = false;
	private BasicUser currentUser;
	private BasicUser ownerUser;
	
	private List<TodoItemDisplay> todoItemDisplayList = Lists.newArrayList();
	private TodoProjectDisplayerListener listener;

	public TodoProjectDisplayer(ToDo toDo, BasicUser currentUser) {
		this.toDo = toDo;
		this.currentUser = currentUser;
		this.ownerUser = toDo.getAssginedUser();
		//FIXME it should be a assignedUser.
		if(ownerUser.getID().equals(currentUser.getID())) {
			owner = true;
		}
		this.setWidth(contentWidth);
	}
	
	public void setListDisplay(boolean listDisplay) {
		if(listDisplay) {
			this.addStyleName("todo-project-display-layout");
		}
	}
	
	public void setSpecialRowStyle(boolean specialRowStyle) {
		this.specialRowStyle = specialRowStyle;
	}
	
	public void attach() {
		HorizontalLayout titleLayout = new HorizontalLayout();
		if(needExpend) {
			expendIcon.setData(true);
			expendIcon.addStyleName(BaseTheme.BUTTON_LINK);
			expendIcon.setWidth("16px");
			expendIcon.setIcon(new ThemeResource("icons/arrow-expand.png"));
			titleLayout.addComponent(expendIcon);
			// set more margin for expend todo
			subTasksLayout.setWidth(taskWidth);
			subTasksLayout.addStyleName("todo-item-layout");
			if(specialRowStyle) {
				this.addStyleName("todo-project-title-special");
			} else {
				this.addStyleName("todo-project-title");
			}
		} else {
			subTasksLayout.addStyleName("todo-item-layout-card");
			this.addStyleName("todo-project-title");
		}
		titleBox = new CheckBox();
		titleBox.setWidth("20px");
		titleBox.setData(toDo);
		titleBox.setImmediate(true);
		titleLayout.addComponent(titleBox);
		// add logic for todo status.
		titleBox.setValue(getStatus());
		if(!owner) {
			titleBox.setReadOnly(true);
		}
//		String projectContent = generateContent(toDo.getName(), toDo.getAssginedUser().getRealName(), toDo.getExpiredDate(), toDo.getFinishDate());
		titleContent = new Label("",Label.CONTENT_XHTML);
		setToDoContent();
		titleContent.setWidth(taskWidth);
		titleLayout.addComponent(titleContent);

//			titleContent.setStyleName("todo-item-checked");
		this.addComponent(titleLayout);
		// check if there no children, dont need change the icon.
		if(toDo.getSubToDoItems().size() == 0) {
			expendIcon.setReadOnly(true);
		}
		
		for(SubToDoItem subItem : toDo.getSubToDoItems()) {
			TodoItemDisplay todoItemDisplay = new TodoItemDisplay(subItem);
			// if the todo is selected, all sub-item can not be selected.
			todoItemDisplay.setReadOnly(isAllowItem(getStatus(), subItem));
			todoItemDisplayList.add(todoItemDisplay);
			subTasksLayout.addComponent(todoItemDisplay);
		}
		this.addComponent(subTasksLayout);

		
		bind();
	}
	
	private boolean getStatus() {
		if(toDo.getStatus() != null && toDo.getStatus() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setToDoContent() {
		String projectContent = generateContent(getStatus(), toDo.getName(), toDo.getAssginedUser().getRealName(), toDo.getExpiredDate(), toDo.getFinishDate());
		titleContent.setValue(projectContent);
	}
	
	
	public String generateContent(boolean status, String content, String assigner, Date exprieDate, Date finishDate) {
		String projectContent = "";
		if(status) {
			projectContent = "<span style=\""+ InnerStyle.todo_checked +"\">" + StrUtils.asciiToXhtml(content) +"</span>&nbsp;";
		} else {
			projectContent = "<span style=\""+ InnerStyle.todo_unCheck +"\">" + StrUtils.asciiToXhtml(content) +"</span>&nbsp;";
		}
		projectContent += "&nbsp;<span style=\""+ InnerStyle.todo_assigner +"\">" + assigner + "</span>&nbsp;";
		if(exprieDate != null) {
			String expireDateString = DateUtils.date2String((Date) exprieDate, "MM-dd");
			projectContent += "<span style=\""+ InnerStyle.todo_dueto +"\">" + expireDateString + "</span>&nbsp;";
		}
		if(finishDate != null) {
			String finishDateString = DateUtils.date2String((Date) finishDate, "MM-dd");
			projectContent += "<span style=\""+ InnerStyle.todo_finish +"\">" + finishDateString + "</span>";
		}
		return projectContent;
	}
	
	public void bind() {
		expendIcon.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(expendIcon.getData().equals(true)) {
					expendIcon.setIcon(new ThemeResource("icons/arrow-collapse.png"));
					subTasksLayout.setVisible(false);
					expendIcon.setData(false);
				} else {
					expendIcon.setIcon(new ThemeResource("icons/arrow-expand.png"));					
					subTasksLayout.setVisible(true);
					expendIcon.setData(true);
				}
			}
		});
		titleBox.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Boolean todoStatus = (Boolean)titleBox.getValue();
				if(todoStatus) {
					boolean hasNotFinishedItem = false;
					for(TodoItemDisplay item : todoItemDisplayList) {
						Boolean subChecked = (Boolean)item.getSubTaskCheck().getValue();
						if(!subChecked) {
							hasNotFinishedItem = true;
							break;
						}
					}
					if(hasNotFinishedItem) {
						ConfirmDialog confirmDialog = new ConfirmDialog("Some sub-task didn't finish. Do you want to close all of them?") {
							@Override
							public void confirm() {
								for(TodoItemDisplay item : todoItemDisplayList) {
									item.getSubTaskCheck().setValue(true);
									item.getSubTaskContent().setStyleName("todo-item-checked");
									item.setReadOnly(isAllowProject(true));
									listener.todoSubItemClick(item);
								}
								listener.todoProjectClick(TodoProjectDisplayer.this);
							}
							@Override
							public void cancel() {
								titleBox.setValue(false);
							}
						};
						TodoProjectDisplayer.this.getWindow().addWindow(confirmDialog);
					} else {
						listener.todoProjectClick(TodoProjectDisplayer.this);
						for(TodoItemDisplay item : todoItemDisplayList) {
							item.setReadOnly(isAllowProject(true));
						}
					}
				} else {
					ConfirmDialog confirmDialog = new ConfirmDialog("Do you want to reopen this TODO project? The finish date will be recalculated.") {
						@Override
						public void confirm() {
							// enable all sub-item make it modified.
							listener.todoProjectClick(TodoProjectDisplayer.this);
							for(TodoItemDisplay item : todoItemDisplayList) {
								item.setReadOnly(isAllowProject(false));
							}
						}
						@Override
						public void cancel() {
							titleBox.setValue(true);
						}
					};
					TodoProjectDisplayer.this.getWindow().addWindow(confirmDialog);
				}
			}
		});
	}
	
	public boolean isAllowProject(boolean valid) {
		return valid && owner;
	}
	
	public boolean isAllowItem(boolean valid, SubToDoItem subItem) {
		if(valid) {
			return true;
		} else {
			if(owner) {
				return false;
			} else {
				return !subItem.getAssginedUser().getID().equals(currentUser.getID());
			}
		}
	}
	
	public class TodoItemDisplay extends VerticalLayout {
		
		CheckBox subTaskCheck;
		Label subTaskContent;
		SubToDoItem subItem;
		
		public TodoItemDisplay(SubToDoItem subItem) {
			this.subItem = subItem;
			this.addStyleName("todo-item");
			HorizontalLayout taskLayout = new HorizontalLayout();
			subTaskCheck = new CheckBox();
			subTaskCheck.setImmediate(true);
			subTaskCheck.setData(subItem);
			taskLayout.addComponent(subTaskCheck);
//			if(!owner && !subItem.getAssginedUser().getID().equals(currentUser.getID())) {
//				setReadOnly(true);
//			}
//			String taskContent = generateContent(subItem.getContent(), subItem.getAssginedUser().getRealName(),  subItem.getExpiredDate(), subItem.getFinishDate());
			subTaskContent = new Label("",Label.CONTENT_XHTML);
			setSubTaskContent();
			subTaskContent.setWidth(subTaskWidth);
//			if(subItem.getStatus() != null && subItem.getStatus() == 1) {
			subTaskCheck.setValue(getStatus());
//				subTaskContent.setStyleName("todo-item-checked");
//			}
			taskLayout.addComponent(subTaskContent);
			this.addComponent(taskLayout);
			
			bind();
		}
		
		public void bind() {
			subTaskCheck.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					listener.todoSubItemClick(TodoItemDisplay.this);
				}
			});
		}
		
		private boolean getStatus() {
			if(subItem.getStatus() != null && subItem.getStatus() == 1) {
				return true;
			}
			return false;
		}
		
		public void setSubTaskContent() {
			String taskContent = generateContent(getStatus(), subItem.getContent(), subItem.getAssginedUser().getRealName(),  subItem.getExpiredDate(), subItem.getFinishDate());
			subTaskContent.setValue(taskContent);
		}
		
		public void setReadOnly(boolean readOnly) {
			subTaskCheck.setReadOnly(readOnly);
		}

		/**
		 * @return the subTaskCheck
		 */
		public CheckBox getSubTaskCheck() {
			return subTaskCheck;
		}

		/**
		 * @return the subTaskContent
		 */
		public Label getSubTaskContent() {
			return subTaskContent;
		}

		/**
		 * @return the subItem
		 */
		public SubToDoItem getSubItem() {
			return subItem;
		}
	}
	
	public void setProjectWidth(String contentWidth, String taskWidth, String subTaskWidth) {
		this.contentWidth = contentWidth;
		this.taskWidth =taskWidth;
		this.subTaskWidth = subTaskWidth; 
	}

	/**
	 * @return the needExpend
	 */
	public boolean isNeedExpend() {
		return needExpend;
	}

	/**
	 * @param needExpend the needExpend to set
	 */
	public void setNeedExpend(boolean needExpend) {
		this.needExpend = needExpend;
	}

	/**
	 * @return the titleBox
	 */
	public CheckBox getTitleBox() {
		return titleBox;
	}

	/**
	 * @return the titleContent
	 */
	public Label getTitleContent() {
		return titleContent;
	}

	/**
	 * @return the listener
	 */
	public TodoProjectDisplayerListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(TodoProjectDisplayerListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the toDo
	 */
	public ToDo getToDo() {
		return toDo;
	}
}
