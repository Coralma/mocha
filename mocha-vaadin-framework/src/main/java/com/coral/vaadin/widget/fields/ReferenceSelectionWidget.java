/**
 * 
 */
package com.coral.vaadin.widget.fields;

import org.vaadin.addon.customfield.CustomField;

import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.CommonEntitySearchWindow;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

/**
 * @author Coral
 *
 */
public class ReferenceSelectionWidget extends FieldWidget {

	public Class refClass;
	public String[] searchFields;
	
	public ReferenceSelectionWidget(String label, String type, String exprssion) {
		super(label);
		try {
			this.refClass = Class.forName(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		searchFields = exprssion.split(",");
	}
	
	public void attach() {
		super.attach();
		setValue(property.getValue());
	}
	
	@Override
	public void initField() {
		field = new ReferenceSelectionField();
		field.setCaption(label);
	}
	
	public class ReferenceSelectionField extends CustomField  {
//		public Object value;
		TextField field = new TextField();
		Button selectionBtn = WidgetFactory.createIconButton("ref-search.png");
		
		public ReferenceSelectionField() {
			this.setImmediate(true);
			HorizontalLayout layout = new HorizontalLayout();
			field.setWidth("195px");
			layout.addComponent(field);
			selectionBtn.setWidth("25px");
			layout.addComponent(selectionBtn);
			setCompositionRoot(layout);
			bind();
		}
		
		public void bind() {
			selectionBtn.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					showSearchWindow();
				}
			});
			field.addListener(new FocusListener() {
				@Override
				public void focus(FocusEvent event) {
					showSearchWindow();					
				}
			});
		}

		public void showSearchWindow() {
			final CommonEntitySearchWindow window = new CommonEntitySearchWindow("Search " + label, refClass, searchFields);
			window.addListener(new CloseListener() {
				@Override
				public void windowClose(CloseEvent e) {
					Object entity = window.getEntity();
					if(entity != null) {
//						value = entity;
//						setFieldValue(value);
						setValue(entity);
					}
				}
			});
			ReferenceSelectionField.this.getWindow().addWindow(window);
		}
		
		@Override
		public Object getValue() {
			return super.getValue();
//			return value;
		}
		
		@Override
		public void setValue(Object value) {
			super.setValue(value);
//			this.value = value;
			setFieldValue(value);
//			property.setValue(value);
		}
		
		public void setFieldValue(Object entity) {
			if(entity != null) {
				Property property = new NestedMethodProperty(entity, searchFields[0]);
				field.setValue(property.getValue());
			}
		}
		
		@Override
		public Class<?> getType() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}
