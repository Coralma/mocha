package com.coral.vaadin.widget.layout;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public class ToDoCardEditMenu extends AbstractEditMenu {

	private Button reOpenButton = new Button("Reopen");

	Button doneButton = new Button();

	private boolean reopenButtonFlag = false; 

	public ToDoCardEditMenu() {
		super();
	}

	public void setReopenButton(boolean reopenButtonFlag) {
		this.setReopenButtonFlag(reopenButtonFlag);
	}

	public void buildLayout() {
		List<Component> popUpComponents = new ArrayList<Component>();
		doneButton.setStyleName(Button.STYLE_LINK);
		popUpComponents.add(doneButton);
		if (isReopenButtonFlag() == true) {
			popUpComponents.add(getReOpenButton());
			getReOpenButton().setStyleName(Button.STYLE_LINK);
		}

		addPopupComponents(popUpComponents);
	}

	@Override
	public void addPopupComponents(List<Component> popUpComponents) {

		if (popUpComponents != null) {
			super.popUpComponents = popUpComponents;
		}

	}

	public boolean isReopenButtonFlag() {
		return reopenButtonFlag;
	}

	public void setReopenButtonFlag(boolean reopenButtonFlag) {
		this.reopenButtonFlag = reopenButtonFlag;
	}


	public Button getReOpenButton() {
		return reOpenButton;
	}

	public void setReOpenButton(Button reOpenButton) {
		this.reOpenButton = reOpenButton;
	}

}
