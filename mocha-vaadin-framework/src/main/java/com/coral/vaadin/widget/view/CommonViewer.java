/**
 * 
 */
package com.coral.vaadin.widget.view;

import java.util.List;

import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.view.builder.FieldBuilder;
import com.coral.vaadin.widget.view.builder.IFieldBuilder;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public abstract class CommonViewer extends AbstractViewer implements Viewer {

	private static final long serialVersionUID = -1526031310306428234L;
	protected Object value;
	protected Class entityClass;
	protected IFieldBuilder fieldBuilder = new FieldBuilder();
	protected VerticalLayout pageTitle;
	protected Message message;
	public CommonViewer() {
		this.setMargin(false);
		this.setSpacing(true);
		this.setWidth("100%");
//		initViewer();
	}
	
	public void attach() {
		super.attach();
		initMessage();
		initViewer();
	}
	
	public void initMessage() {
		message = new Message(getApplication().getLocale());
	}
	
	public void initViewer() {
		String title = getViewerTitle();
		if(title != null) {
			String i18NTitle = message.getString(title);
			pageTitle = PageBuildHelper.buildPageTitle(i18NTitle);
			addComponent(pageTitle);
		}
	}
	
	public void changeTitle(String newTitle) {
		if(pageTitle != null) {
			Label label = (Label)pageTitle.getComponent(0);
			label.setValue(newTitle);
		}
	}
	
	public void registerWidget(String fieldName, Widget widget) {
		widgets.put(fieldName, widget);
	}

	public Widget buildWidget(String fieldName, String label) {
		return buildWidget(fieldName,label,"String");
	}
	
	public Widget buildWidget(String fieldName, String label, boolean required) {
		return buildWidget(fieldName,label,"String", required);
	}
	
	public Widget buildWidget(String fieldName, String label, String type) {
		return buildWidget(fieldName,label,type,false);
	}
	
	public Widget buildWidget(String fieldName, String label, String type, boolean required) {
		return buildWidget(fieldName,label,type,required, null);
	}
	
	public Widget buildWidget(String fieldName, String label, String type,boolean required, String style) {
		Property property = new Property();
		property.setPropertyName(fieldName);
		property.setType(type);
		property.setStyle(style);
		property.setLabel(label);
		property.setRequired(required);
		ViewField viewField = new ViewField();
		viewField.setFieldName(fieldName);
		viewField.setProperty(property);

		Widget widget = fieldBuilder.build(viewField);
		widgets.put(fieldName, widget);
		return widget;
	}
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return getEntityValue(value,entityClass);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
		setEntityValue(value);
	}

	public Result validate(String type) {
		return new Result();
	}

	/**
	 * @return the entityClass
	 */
	public Class getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
}
