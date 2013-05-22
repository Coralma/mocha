package com.coral.vaadin.widget.table;

import java.util.Collection;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.coral.foundation.md.model.Entity;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.resource.ModelCenter;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings({"serial","rawtypes","unchecked"})
public class PopupEditorTable extends AbstractEntityTable implements EntityTable {
	
	private Viewer entityView;
	private Window popupWindow;
	private Item selectedItem;
	private ActionKind currentActionKind;
	
	public PopupEditorTable() {
		
	}
	public PopupEditorTable(String view) {
		super(view);
	}

	public void build(String label) {
		super.build(label);
		buildControlButton();
	}
	
	public void buildControlButton() {
//		HorizontalLayout group = new HorizontalLayout();
//		group.setSpacing(true);
		ButtonGroup group = new ButtonGroup();
		
		Button addAction = new Button("Add");
        addAction.setIcon(new ThemeResource("icons/add.png"));
        addAction.setData(ActionKind.ADD);
        addAction.addListener((ClickListener) this);
        group.addButton(addAction);
        
        Button editAction = new Button("Edit");
        editAction.setIcon(new ThemeResource("icons/edit.png"));
        editAction.setData(ActionKind.EDIT);
        editAction.addListener((ClickListener) this);
        group.addButton(editAction);
        
        Button viewAction = new Button("View");
        viewAction.setIcon(new ThemeResource("icons/view.png"));
        viewAction.setData(ActionKind.VIEW);
        viewAction.addListener((ClickListener) this);
        group.addButton(viewAction);
      
        Button delAction = new Button("Delete");
        delAction.setIcon(new ThemeResource("icons/del.png"));
        delAction.setData(ActionKind.DELETE);
        delAction.addListener((ClickListener) this);
        group.addButton(delAction);
        addComponent(group);
        setComponentAlignment(group, Alignment.MIDDLE_RIGHT);
	}
	
	public Component buildEntitySection(Object selectObj) {
		selectedItem = table.getItem(selectObj);
		Presenter presenter = pageFactory.getPresenter(viewClass, null);
		entityView = presenter.go(); 
		ViewContext viewContext = presenter.getViewContext();
		String[] dialogSize = viewContext.getDialogSize();
		
		if(selectObj != null)
			entityView.setValue(selectObj);
        popupWindow = new Window(entityView.getViewerTitle());
        popupWindow.setModal(true);
        
        if(dialogSize != null && dialogSize.length > 0) {
        	popupWindow.setWidth(dialogSize[0] + "px");
        } else if(dialogSize != null && dialogSize.length > 1) {
        	popupWindow.setHeight(dialogSize[1] + "px");
        } else {
        	popupWindow.setWidth("700px");
        }
        VerticalLayout dialog = new VerticalLayout();
        dialog.setWidth("100%");
        dialog.setSpacing(true);
        dialog.addComponent(entityView);
        ButtonGroup group = getPopupWindowButton();
        dialog.addComponent(group);
        dialog.setComponentAlignment(group, Alignment.MIDDLE_CENTER);
        popupWindow.addComponent(dialog);
//	        popupWindow.addComponent(entityView);
//	        popupWindow.addComponent(getPopupWindowButton());
        getWindow().addWindow(popupWindow);
		return null;
    }
	
	public void onCreateItem(final Collection<Object> selection) {
		currentActionKind = ActionKind.ADD;
		buildEntitySection(null);
	}
	
	public void onUpdateItem(final Collection<Object> selection) {
		currentActionKind = ActionKind.EDIT;
		buildEntitySection(selection.iterator().next());
	}

	public void onDisplayItem(final Collection<Object> selection) {
		currentActionKind = ActionKind.VIEW;
		buildEntitySection(selection.iterator().next());
	}

	public void selectionChange(com.vaadin.data.Property.ValueChangeEvent event) {
		
	}
	
	public ButtonGroup getPopupWindowButton() {
		ButtonGroup btnLayout = new ButtonGroup();
		if(currentActionKind != ActionKind.VIEW) {
			Button saveAction = new Button("Save");
	        saveAction.setIcon(new ThemeResource("icons/add.png"));
	        saveAction.setData(ActionKind.SAVE);
	        btnLayout.addButton(saveAction);
	        saveAction.addListener(new PopupEditorListener());
		}
        
        Button cancelAction = new Button("Cancel");
        cancelAction.setIcon(new ThemeResource("icons/del.png"));
        cancelAction.setData(ActionKind.CANCEL);
        btnLayout.addButton(cancelAction);
        cancelAction.addListener(new PopupEditorListener());
        return btnLayout;
	}

	public class PopupEditorListener implements ClickListener {
		@Override
		public void buttonClick(ClickEvent event) {
			ActionKind kind = (ActionKind)event.getButton().getData();
			switch (kind) {
			case SAVE: 
	            onSave();
	            break;
	        case CANCEL:
	        	onCancel();
	            break;
	        default:
	            break;
	        }
		}
		
		private void onSave() {
			switch (currentActionKind) {
			case ADD: 
				table.addItem(entityView.getValue());
	            break;
	        case EDIT:
	        	BeanItem newItem = new BeanItem(entityView.getValue());
	        	for (Object propId : selectedItem.getItemPropertyIds()) {
	                Property newProperty = newItem
	                        .getItemProperty(propId);
	                if (newProperty != null)
	                	selectedItem.getItemProperty(propId).setValue(
	                            newProperty.getValue());
	            }
	            table.refreshRowCache();
	            break;
	        default:
	            break;
	        }
			getWindow().removeWindow(popupWindow);
		}
		
		private void onCancel() {
			getWindow().removeWindow(popupWindow);
		}
	}
}
