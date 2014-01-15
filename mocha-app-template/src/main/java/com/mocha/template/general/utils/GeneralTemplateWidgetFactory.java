package com.mocha.template.general.utils;

import com.mocha.template.general.widget.GeneralEntityCardSectionWidget;
import com.mocha.template.general.widget.GeneralNavigationWidget;
import com.mocha.template.general.widget.GeneralPropertyWidget;
import com.mocha.template.general.widget.GeneralSectionWidget;
import com.mocha.template.general.widget.GeneralTableWidget;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralEntityCardSectionVO;
import com.mocha.template.general.widget.vo.GeneralHeadVO;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;

public class GeneralTemplateWidgetFactory {
	
	public static String total_width = GeneralTemplateCst.total_width;
	public static String app_name_width = GeneralTemplateCst.app_name_width;
	public static String menu_width = GeneralTemplateCst.menu_width;

	public static GeneralNavigationWidget buildGeneralNavigationWidget(GeneralHeadVO generalHead) {
		GeneralNavigationWidget navigationWidget = new GeneralNavigationWidget(generalHead);
		return navigationWidget; 
	}
	
	public static GeneralSectionWidget buildGeneralSectionWidget(GeneralSectionContentVO sectionContent) {
		GeneralSectionWidget sectionWidget = new GeneralSectionWidget(sectionContent);	
		return sectionWidget;
	}
	
	public static GeneralTableWidget buildGeneralTableWidget(GeneralTableVO generalTable, GeneralTableListener tableListener) {
		GeneralTableWidget tableWidget = new GeneralTableWidget(generalTable, tableListener);
		return tableWidget;
	}
	
	public static GeneralPropertyWidget buildGeneralPropertyWidget(GeneralPropertyVO propertyVO, GeneralPropertyListener propertyListener) {
		GeneralPropertyWidget propertyWidget = new GeneralPropertyWidget(propertyVO, propertyListener);
		return propertyWidget;
	}
	
	public static GeneralEntityCardSectionWidget buildGeneralEntityCardSectionWidget(GeneralEntityCardSectionVO entityCardSectionVO) {
		GeneralEntityCardSectionWidget entityCardSectionWidget = new GeneralEntityCardSectionWidget(entityCardSectionVO);
		return entityCardSectionWidget;
	}
	
	public static Button buildCreateButton(String caption) {
		Button createButton = new NativeButton(caption);
		createButton.addStyleName("create-button");
		return createButton;
	}
}
