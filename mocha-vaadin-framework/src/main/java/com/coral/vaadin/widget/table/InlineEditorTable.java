/**
 * 
 */
package com.coral.vaadin.widget.table;

import java.util.Collection;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class InlineEditorTable extends AbstractEntityTable implements EntityTable {

	private Viewer entityView;
	private Item selectedItem;
	private VerticalLayout entityForm;
	private Button addAction;
	private Button delAction;

	public void build(String label) {
		setSpacing(true);
		
		super.build(label);
		
		entityForm = new VerticalLayout();
		entityForm.setWidth("100%");
		entityForm.addComponent(buildEntitySection(null));
		addComponent(entityForm);
		ButtonGroup controlBtn = buildControlButton();
		addComponent(controlBtn);
		setComponentAlignment(controlBtn, Alignment.MIDDLE_RIGHT);
	}

	public Component buildEntitySection(Object selectObj) {
		Presenter presenter = pageFactory.getPresenter(viewClass, null);
		entityView = presenter.go();
//		entityView.build();
		if(selectObj != null) {
			selectedItem = table.getItem(selectObj);
			entityView.setValue(selectObj);
		}
		return entityView;
	}
	
	public void rebuildEntitySection() {
		entityForm.removeAllComponents();
		entityForm.addComponent(buildEntitySection(null));
		entityForm.requestRepaintAll();
	}

	public ButtonGroup buildControlButton() {
//		HorizontalLayout group = new HorizontalLayout();
//		group.setSpacing(true);
		ButtonGroup group = new ButtonGroup();
		
		addAction = new Button("New");
        addAction.setIcon(new ThemeResource("icons/add.png"));
        addAction.setData(ActionKind.ADD);
        addAction.addListener((ClickListener) this);
        addAction.setEnabled(false);
        group.addButton(addAction);
        
        Button editAction = new Button("Save");
        editAction.setIcon(new ThemeResource("icons/edit.png"));
        editAction.setData(ActionKind.EDIT);
        editAction.addListener((ClickListener) this);
        group.addButton(editAction);

        delAction = new Button("Delete");
        delAction.setIcon(new ThemeResource("icons/del.png"));
        delAction.setData(ActionKind.DELETE);
        delAction.addListener((ClickListener) this);
        delAction.setEnabled(false);
        group.addButton(delAction);
		return group;
	}

	@Override
	public void onCreateItem(Collection<Object> selection) {
		rebuildEntitySection();
		addAction.setEnabled(false);
		delAction.setEnabled(false);
		selectedItem = null;
	}

	@Override
	protected void onUpdateItem(Collection<Object> selection) {
		if(selectedItem == null) {
			Object value = entityView.getValue();
			if(value == null) return;
			table.addItem(value);
		} else {
        	BeanItem newItem = new BeanItem(entityView.getValue());
        	for (Object propId : selectedItem.getItemPropertyIds()) {
                Property newProperty = newItem
                        .getItemProperty(propId);
                if (newProperty != null)
                	selectedItem.getItemProperty(propId).setValue(
                            newProperty.getValue());
            }
            table.refreshRowCache();
		}
		rebuildEntitySection();
		addAction.setEnabled(false);
	}

	@Override
	protected void onDisplayItem(Collection<Object> selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void selectionChange(ValueChangeEvent event) {
		entityForm.removeAllComponents();
		entityForm.addComponent(buildEntitySection(getSelection().iterator().next()));
		entityForm.requestRepaintAll();
		addAction.setEnabled(true);
		delAction.setEnabled(true);
	}

}
