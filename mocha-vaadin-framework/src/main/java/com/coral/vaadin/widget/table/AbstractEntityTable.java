/**
 * 
 */
package com.coral.vaadin.widget.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.PageFactory;
import com.coral.vaadin.widget.Widget;
import com.google.common.collect.Lists;
import com.vaadin.data.Item;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Coral.Ma
 * 
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public abstract class AbstractEntityTable extends VerticalLayout implements Widget, ClickListener {
	
	EntityContainer dataContainer;
	PagedTableContainer pageTableContainer;
	PagedTable table;
	Object data;
	String type;
	String viewClass;
	PageFactory pageFactory = SpringContextUtils.getBean("pageFactory", PageFactory.class);
	
	public enum ActionKind {
        ADD, VIEW, EDIT, DELETE, SELECT, SAVE, CANCEL
    }
	
	public AbstractEntityTable() {
		this(null);
	}
	public AbstractEntityTable(String view) {
		this.viewClass = view;
//		this.setSpacing(true);
		this.setWidth("100%");
	}
	
	public void build(String label) {
		if(label != null) {
			Label tableTitle = new Label(label + ":");
			tableTitle.addStyleName("mocha-table-title");
			addComponent(tableTitle);
		}
		table = new PagedTable();
		pageTableContainer = new PagedTableContainer(dataContainer);
		table.setContainerDataSource(pageTableContainer);
		table.setVisibleColumns(dataContainer.getVisibleColumns());
		table.setColumnHeaders(dataContainer.getColumnHeaders());
		addComponent(table);
		addComponent(table.createControls());
		table.setPageLength(5);
		table.setWidth("100%");
        table.setSelectable(true);
        table.setImmediate(true);
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.addListener(new Table.ValueChangeListener() {
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
            	if(getSelection().size() > 0)
            		selectionChange(event);
//            	action.selectionChange(event);
            }
        });
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		ActionKind kind = (ActionKind)event.getButton().getData();
		final Collection<Object> selection = getSelection();
		switch (kind) {
		case ADD: 
            onCreateItem(selection);
            break;
        case EDIT:
        	onUpdateItem(selection);
            break;
        case VIEW:
            if (selection.size() == 1) 
            	onDisplayItem(selection);
            break;
        case DELETE:
            if (selection.size() > 0) 
            	onDeleteItem(selection);
            break;
        default:
            break;
        }
		requestRepaintAll();
	}
	
	protected abstract void onCreateItem(final Collection<Object> selection);
	
	protected abstract void onUpdateItem(final Collection<Object> selection);

	protected abstract void onDisplayItem(final Collection<Object> selection);

	public void onDeleteItem(final Collection<Object> selection) {
		ConfirmDialog.show(getWindow(), "请确认",
                "您是否真的要删除这条数据?", "是的", "取消",
                new ConfirmDialog.Listener() {
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                               table.removeItem(selection.iterator().next()); 
                        }
                    }
                });
	}

	protected abstract void selectionChange(com.vaadin.data.Property.ValueChangeEvent event);
	
	public void addItem(Object obj) {
		pageTableContainer.addItem(obj);
	}
	
	public Item getItem(Object obj) {
		return pageTableContainer.getItem(obj);
	}
	
	public void removeItem(Object obj) {
		pageTableContainer.removeItem(obj);
	}
	
	public void refreshRowCache() {
		table.refreshRowCache();
	}
	
	public void setPageLength(int lenth) {
		table.setPageLength(lenth);
	}

	@Override
	public Window getWindow() {
		return getRootWindow(getParent());
	}
	
	private Window getRootWindow(Component component) {
		Component rootWindow = component;
		while(rootWindow.getParent() != null) {
			rootWindow = rootWindow.getParent();
		}
		return (Window)rootWindow;
	}
	
	public Collection<Object> getSelection() {
        Object value = table.getValue();
        if (value instanceof Collection)
            return (Collection<Object>) value;
        else {
            List<Object> list = Lists.newArrayList();
            if (value != null)
                list.add(value);
            return list;
        }
    }

	@Override
	public List getValue() {
		List resultList = new ArrayList();
		resultList.addAll(table.getItemIds());
		return resultList;
	}

	@Override
	public void setValue(Object value) {
		List dataList = (List)value;
		for(Object data : dataList) {
			dataContainer.addItem(data);
      }
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public boolean validate(String type) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return the dataContainer
	 */
	public EntityContainer getDataContainer() {
		return dataContainer;
	}

	/**
	 * @param dataContainer the dataContainer to set
	 */
	public void setDataContainer(EntityContainer dataContainer) {
		this.dataContainer = dataContainer;
	}
	/**
	 * @return the viewClass
	 */
	public String getViewClass() {
		return viewClass;
	}
	/**
	 * @param viewClass the viewClass to set
	 */
	public void setViewClass(String viewClass) {
		this.viewClass = viewClass;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	

}
