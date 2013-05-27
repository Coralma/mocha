package com.coral.vaadin.widget.view;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.util.CodeTableFactory;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.BigDecimalField;
import com.coral.vaadin.widget.field.CodeTableField;
import com.coral.vaadin.widget.field.DateField;
import com.coral.vaadin.widget.field.StringAreaField;
import com.coral.vaadin.widget.field.StringField;
import com.coral.vaadin.widget.layout.SectionLayout;
import com.coral.vaadin.widget.listener.PresenterListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;

public class AbstractHomePageView extends AbstractView implements Widget,Viewer{
	
	private PresenterListener listener;
	
	
	
	public AbstractHomePageView(){
		super();
		
	}
	
	public AbstractHomePageView(String title){
		super(title);
	}
	
	public void build(){
		 	setSpacing(true);

	        // Create the content for the popup
	        Label content = new Label(
	                "This popup will close as soon as you move the mouse cursor outside of the popup area.");
	        // The PopupView popup will be as large as needed by the content
	        content.setWidth("300px");

	        // Construct the PopupView with simple HTML text representing the
	        // minimized view
	        PopupView popup = new PopupView("Default popup", content);
	        popup.setHideOnMouseOut(true);
//	        popup.addListener(this);
	        addComponent(popup);

	        content = new Label(
	                "This popup will only close if you click the mouse outside the popup area.");
	        // The PopupView popup will be as large as needed by the content
	        content.setWidth("300px");

	        popup = new PopupView("Popup that won't auto-close", content);
	        popup.setHideOnMouseOut(false);
//	        popup.addListener(this);
	        addComponent(popup);
		
			
	}
	
	  public void popupVisibilityChange(PopupVisibilityEvent event) {
	        if (!event.isPopupVisible()) {
	            getWindow().showNotification("Popup closed");
	        }
	    }

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void setListener(PresenterListener listener) {
//		this.listener = listener;
//	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Result validate(String type) {
		// TODO Auto-generated method stub
		return new Result();
	}
	
	
	@Override
	public SectionLayout createSection(String sectionLabel, int columns, SectionLayout parentLayout) {
		return createSection(sectionLabel, 1, parentLayout, null);
	}
	
	@Override
	public Widget addWidget(SectionLayout section, WidgetParameter parameter, Alignment aligment) {
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
			section.addField((Component)widget,aligment);
			widgets.add(widget);
		} else {
			System.out.println("[Error] Create Widget '" + label + "', type is '"+ type +"' error!" );
		}
		return widget;
	}
	
}
