package com.mocha.template.general;

import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.template.general.utils.GeneralTemplateCst;

public abstract class AbstractGeneralViewer extends CommonViewer {

	public String total_width = GeneralTemplateCst.total_width;
	public String app_name_width = GeneralTemplateCst.app_name_width;
	public String menu_width = GeneralTemplateCst.menu_width;
	
	public String left_layout_width = GeneralTemplateCst.left_layout_width;
	public String left_content_width = GeneralTemplateCst.left_content_width;
	public String right_layout_width = GeneralTemplateCst.right_layout_width;
	
}
