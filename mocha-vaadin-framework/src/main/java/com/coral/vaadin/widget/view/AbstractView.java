package com.coral.vaadin.widget.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.controller.PageFactory;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.util.CodeTableFactory;
import com.coral.vaadin.util.VaadinDataBinding;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.field.BigDecimalField;
import com.coral.vaadin.widget.field.CodeTableField;
import com.coral.vaadin.widget.field.DateField;
import com.coral.vaadin.widget.field.StringAreaField;
import com.coral.vaadin.widget.field.StringField;
import com.coral.vaadin.widget.layout.PageLayout;
import com.coral.vaadin.widget.layout.SectionLayout;
import com.coral.vaadin.widget.table.AbstractTable;
import com.coral.vaadin.widget.table.EntityTable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TabSheet;

/**
 * @author Coral
 * @deprecated
 */
@SuppressWarnings({"rawtypes","unchecked"})
public abstract class AbstractView extends PageLayout {

	private static final long serialVersionUID = -813840575998136491L;
	List<Widget> widgets;
	List<SectionLayout> sections;
	List<AbstractTable> tables;
	List<ActionButton> buttons;
	Map<String,ComponentContainer> container = new HashMap<String, ComponentContainer>();
	PageFactory pageFactory = SpringContextUtils.getBean("pageFactory", PageFactory.class);
	
	public AbstractView() {
		this(null);
	}
	public AbstractView(String title) {
		super(title);
		widgets = new ArrayList<Widget>();
		sections = new ArrayList<SectionLayout>();
		buttons = new ArrayList<ActionButton>();
		tables = new ArrayList<AbstractTable>();
	}
	
	public SectionLayout createSection(String sectionLabel, int columns) {
		return createSection(sectionLabel, null, columns);
	}
	
	public SectionLayout createSection(String sectionLabel, String type, int columns) {
		SectionLayout section = new SectionLayout(sectionLabel, columns, 1, type);
		sections.add(section);
        addComponent(section);
        return section;
	}
	
	public SectionLayout createStandSection(String sectionLabel, int columns) {
		return createStandSection(sectionLabel, columns, null);
	}
	
	public SectionLayout createStandSection(String sectionLabel, int columns, String type) {
		SectionLayout section = new SectionLayout(sectionLabel, columns, 1, type);
		sections.add(section);
        return section;
	}
	
	public SectionLayout createSection(String sectionLabel, int columns, SectionLayout parentLayout) {
		return createSection(sectionLabel, columns, parentLayout, null);
	}
	
	public SectionLayout createSection(String sectionLabel, int columns, SectionLayout parentLayout, String type) {
		SectionLayout section = new SectionLayout(sectionLabel, columns, 1, type);
		if(parentLayout == null) {
			addComponent(section);
		} else {
			parentLayout.addComponent(section);
		}
		sections.add(section);
        return section;
	}
	
	public SectionLayout createTabSection(String group, String sectionLabel, int columns, SectionLayout parentLayout) {
		TabSheet tab = (TabSheet) container.get(group);
		if(tab == null) {
			tab = new TabSheet();
	        tab.setWidth("100%");
			if(parentLayout == null) {
				addComponent(tab);
			} else {
				parentLayout.addComponent(tab);
			}
			container.put(group, tab);
		}
		SectionLayout section = new SectionLayout(columns, 1);
		section.setMargin(true);
		sections.add(section);
		tab.addTab(section,sectionLabel);
        return section;
	}
	
	public ActionButton addButton(String label, String data, ClickListener listener) {
		return addButton(label, data, listener, null);
	}
	
	public ActionButton addButton(String label, String data, ClickListener listener, ThemeResource icon) {
		ActionButton actionButton = new ActionButton(label,data);
		if(listener != null) {
			actionButton.addListener(listener);
		}
		if(icon != null) {
			actionButton.setIcon(icon);
		}
		buttons.add(actionButton);
		return actionButton;
	}
	
	public void addViewer(SectionLayout section, String viewerName, Object data) {
		Presenter presenter = pageFactory.getPresenter(viewerName, null);
		Widget widget = (Widget)presenter.go();
		if(widget != null) {
			widget.setData(data);
			section.addField((Component)widget);
			widgets.add(widget);
		} else {
			System.out.println("[Error] Create Sub-Viewer "+ viewerName +" error!" );
		}
	}

	@Deprecated
	public Widget addWidget(SectionLayout section, String label, String data, String type, boolean isRequired) {
		return addWidget(section, label, data, type, null, isRequired, null);
	}
	@Deprecated
	public Widget addWidget(SectionLayout section, String label, String data, String type, String style, boolean isRequired) {
		return addWidget(section, label, data, type, style, isRequired, null);
	}
	@Deprecated
	public Widget addWidget(SectionLayout section, String label, String data, String type, String style, boolean isRequired, String fieldWidth) {
		Widget widget = null;
		if("String".equals(type)) {
			if(!StrUtils.isEmpty(style) && style.startsWith("large")) {
				widget = new StringAreaField(label);
				if(style.length() > 6) {
					String largeWidth = style.substring(6);
					((Field)widget).setFieldWidth(largeWidth);
				}
			} else if(!StrUtils.isEmpty(style) && style.startsWith("code")) {
				String codeTableName = style.substring(5);
				String[] datas = CodeTableFactory.getFactory().getCodeTable(codeTableName);
				widget = new CodeTableField(label, datas);
			} else {
				widget = new StringField(label);
			}
		} else if("Date".equals(type)) {
			widget = new DateField(label);
		} else if("BigDecimal".equals(type)) {
			widget = new BigDecimalField(label);
		}
		if(widget != null) {
			((Field)widget).setRequired(isRequired);
			widget.setData(data);
			((Field)widget).setFieldWidth(section.getFieldWidth());
			if(fieldWidth != null) {
				((Field)widget).setFieldWidth(fieldWidth);	
			}
			section.addField((Component)widget);
			widgets.add(widget);
		} else {
			System.out.println("[Error] Create Widget '" + label + "', type is '"+ type +"' error!" );
		}
		return widget;
	}

	public Widget addWidget(SectionLayout section, WidgetParameter parameter) {
		String label = parameter.getLabel();
		String data = parameter.getData();
		String type = parameter.getType();
		String style = parameter.getStyle();
		boolean isRequired = parameter.isRequired();
		String fieldWidth = parameter.getFieldWidth();
		
		Widget widget = null;
		if("String".equals(type)) {
			if(!StrUtils.isEmpty(style) && style.startsWith("large")) {
				widget = new StringAreaField(label);
				if(style.length() > 6) {
					String largeWidth = style.substring(6);
					((Field)widget).setFieldWidth(largeWidth);
				}
			} else if(!StrUtils.isEmpty(style) && style.startsWith("code")) {
				String codeTableName = style.substring(5);
				String[] datas = CodeTableFactory.getFactory().getCodeTable(codeTableName);
				widget = new CodeTableField(label, datas);
			} else {
				widget = new StringField(label);
			}
		} else if("Date".equals(type)) {
			widget = new DateField(label);
		} else if("BigDecimal".equals(type)) {
			widget = new BigDecimalField(label);
		}
		if(widget != null) {
			((Field)widget).setRequired(isRequired);
			widget.setData(data);
			((Field)widget).setFieldWidth(section.getFieldWidth());
			if(!StrUtils.isEmpty(fieldWidth)) {
				((Field)widget).setFieldWidth(fieldWidth);	
			}
			section.addField((Component)widget);
			widgets.add(widget);
		} else {
			System.out.println("[Error] Create Widget '" + label + "', type is '"+ type +"' error!" );
		}
		return widget;
	}
	
	public Widget addWidget(SectionLayout section, WidgetParameter parameter, Alignment alignment) {
		return null;
	}
	
	@Deprecated
	public Widget addTableWidget(SectionLayout section, String label, String data, Class viewClass, Class tableClass) {
		return addTableWidget(section, label, data, viewClass,  tableClass, EntityTable.POPUP);
	}
	@Deprecated
	public Widget addTableWidget(SectionLayout section, String label, String data, Class viewClass, Class tableClass, String style) {
		AbstractTable table = null;
		try {
			table = (AbstractTable)tableClass.getDeclaredConstructor(String.class, Class.class).newInstance(style, viewClass);
			if(table != null) {
				table.setData(data);
				table.initTable(null);
				section.addField((Component)table.getTable());
				widgets.add(table);
			} else {
				System.out.println("[Error] Create Widget '" + label + "', type is '"+ viewClass +"' error!" );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(table != null) {
			tables.add(table);
		}
		return table;
	}
	
	public Widget addTableWidget(SectionLayout section, WidgetParameter parameter) {
		String label = parameter.getLabel();
		String data =  parameter.getData();
		Class viewClass = parameter.getViewClass();
		Class tableClass = parameter.getTableClass();
		String style = parameter.getStyle();
		
		AbstractTable table = null;
		try {
			table = (AbstractTable)tableClass.getDeclaredConstructor(String.class, Class.class).newInstance(style, viewClass);
			if(table != null) {
				table.setData(data);
				table.initTable(null);
				section.addField((Component)table.getTable());
				widgets.add(table);
			} else {
				System.out.println("[Error] Create Widget '" + label + "', type is '"+ viewClass +"' error!" );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(table != null) {
			tables.add(table);
		}
		return table;
	}
	
	public List<Widget> getSubWidgets() {
	    return widgets;
	}

    public <T> T getEntityValue(Class<T> t) {
		T pojo = null;
		try {
			pojo = getEntityValue(t.newInstance(),t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}

    public <T> T getEntityValue(T obj, Class<T> t) {
		for(Widget Widget : widgets) {
			Result result = Widget.validate("COMMIT");
			if(!result.isPass()) {
				return null;
			}
		}
		if(obj == null) {
			try {
				obj = t.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (T) VaadinDataBinding.bindingData(widgets, obj,t);
	}
	
	public void setEntityValue(Object value) {
		if(value == null) return;
		VaadinDataBinding.bindingToFields(value, widgets);
	}
	
	public List<AbstractTable> getTables() {
		return tables;
	}

	public List<ActionButton> getButtons() {
		return buttons;
	}
	

}
