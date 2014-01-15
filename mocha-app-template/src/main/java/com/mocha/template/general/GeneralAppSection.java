package com.mocha.template.general;

import com.coral.vaadin.view.template.sat.panel.impl.AbstractViewLayout;
import com.coral.vaadin.widget.Field;
import com.mocha.template.IAppSection;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.vaadin.ui.Label;

public class GeneralAppSection extends AbstractViewLayout implements IAppSection {

	private String left_content_width = GeneralTemplateCst.left_edit_content_width;
	
	public String label;
	private boolean readOnly;
	
	public GeneralAppSection() {
		this(null);
	}
	
	public GeneralAppSection(String label) {
		this.label = label;
		this.setWidth(left_content_width);
		this.setSpacing(true);
		this.addStyleName("app-section-panel");
		this.addStyleName("app-section-content");
	}
	
	public void attach() {
		initTitle();
		layout();
	}
	
	private void initTitle() {
		if(label != null) {
			Label sectionTitle = new Label(label);
			sectionTitle.setWidth("100%");
			sectionTitle.addStyleName("section-label");
			addComponent(sectionTitle);
		}
	}
	
	public void setReadOnly(boolean readOnly) {
		for(Field field : fields) {
			field.setReadOnly(readOnly);
		}
		this.readOnly = readOnly;
	}
	
	@Override
	public String getWholeRowWidth() {
		return "600px";
	}
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
}
