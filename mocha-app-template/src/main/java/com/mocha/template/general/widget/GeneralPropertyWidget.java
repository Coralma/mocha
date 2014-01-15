package com.mocha.template.general.widget;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.coral.foundation.utils.ReflectionUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GeneralPropertyWidget extends VerticalLayout {
	
	protected String right_section_width= GeneralTemplateCst.right_section_width;
	protected GeneralPropertyVO propertyVO;
	protected GeneralPropertyListener propertyListener;
	
	public GeneralPropertyWidget(GeneralPropertyVO propertyVO, GeneralPropertyListener propertyListener) {
		this.propertyVO = propertyVO;
		this.propertyListener = propertyListener;
		this.setWidth(right_section_width);
		this.addStyleName("property-widget");
	}
	
	public void attach() {
		VerticalLayout captionLayout = new VerticalLayout();
		captionLayout.setMargin(true);
		captionLayout.setWidth(right_section_width);
		captionLayout.addStyleName("property-caption-layout");
		Label captionLabel = WidgetFactory.createLabel(propertyVO.getCaption());
		captionLabel.addStyleName("property-caption");
		captionLayout.addComponent(captionLabel);
		
		this.addComponent(captionLayout);
		
		int type = propertyVO.getType();
		if(type == 1) {
			LinkedHashMap<String, String> propertyMap = propertyVO.getPropertyMap();
			for (Iterator<String> it = propertyMap.keySet().iterator();it.hasNext();) {
				String key = it.next();
				String value = propertyMap.get(key);
				HorizontalLayout layout = new HorizontalLayout();
				layout.addStyleName("property-row-layout");
				layout.setWidth("100%");
				Label keyLabel = WidgetFactory.createLabel(key);
				keyLabel.addStyleName("key-label");
				Label valueLabel = WidgetFactory.createLabel(value);
				valueLabel.addStyleName("value-label");
				layout.addComponent(keyLabel);
				layout.addComponent(valueLabel);
				this.addComponent(layout);
			}
		} else if(type == 2) {
			List<String> labels = propertyVO.getPropertyLabels();
			List<String> variables = propertyVO.getPropertyVariables();
			for(int i=0; i < labels.size(); i++) {
				String key = labels.get(i);
				String variable = variables.get(i);
				HorizontalLayout layout = new HorizontalLayout();
				layout.addStyleName("property-row-layout");
				layout.setWidth("100%");
				Object variableValue = ReflectionUtils.getVariableValue(propertyVO.getData(), variable);
				String value = "";
				if(variableValue != null) {
					value = variableValue.toString();
				}
				Label keyLabel = WidgetFactory.createLabel(key);
				keyLabel.addStyleName("key-label");
				Label valueLabel = WidgetFactory.createLabel(value);
				valueLabel.addStyleName("value-label");
				layout.addComponent(keyLabel);
				layout.addComponent(valueLabel);
				this.addComponent(layout);
			}
		}
	}
}
