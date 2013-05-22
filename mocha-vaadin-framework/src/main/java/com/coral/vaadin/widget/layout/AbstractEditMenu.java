package com.coral.vaadin.widget.layout;

import java.util.List;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractEditMenu extends HorizontalLayout {
	VerticalLayout menuRoot = new VerticalLayout();
	HorizontalLayout root = new HorizontalLayout();
	private final PopupView popUpView = new PopupView(new PopUpViewContext());

	List<Component> popUpComponents = null;

	private Button editButton = new Button("Edit");
	private Button deleteButton = new Button("Delete");

	public AbstractEditMenu() {
		buildLayout();
	}

	public HorizontalLayout getMenu() {
		return this.root;
	}

	abstract void addPopupComponents(List<Component> popUpComponents);


	private void buildLayout() {
		getPopUpView().setHideOnMouseOut(true);
		getPopUpView().setStyleName("");
		Button link = new Button();
		link.setStyleName(Button.STYLE_LINK);
		link.setIcon(new ThemeResource("icons/todoPopup.png"));
		link.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getPopUpView().setPopupVisible(true);
				getPopUpView().setPopupVisibility(true);
			}
		});
		root.addComponent(link);
		root.addComponent(getPopUpView());

	}

	public void popupMenuDismiss() {
		getPopUpView().setPopupVisibility(false);
		getPopUpView().setPopupVisible(false);

	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getEditButton() {
		
		return editButton;
	}

	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

	public PopupView getPopUpView() {
		return popUpView;
	}

	public class PopUpViewContext implements PopupView.Content {

		@Override
		public String getMinimizedValueAsHTML() {
			// TODO Auto-generated method stub
			return "";
		}

		@Override
		public Component getPopupComponent() {
			menuRoot.setSizeUndefined();
			menuRoot.setSpacing(false);
			menuRoot.setMargin(false);
			
			
			menuRoot.addComponent(getEditButton());
			getEditButton().setStyleName(Button.STYLE_LINK);
			getDeleteButton().setStyleName(Button.STYLE_LINK);
			menuRoot.addComponent(getDeleteButton());
			
			if(popUpComponents!=null){
				for(Component component:popUpComponents){
					menuRoot.addComponent(component);
				}
			}
			
			return menuRoot;
		}

		public List<Component> getPopUpComponents() {
			return popUpComponents;
		}

	}

}