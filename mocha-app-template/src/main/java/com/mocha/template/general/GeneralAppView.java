package com.mocha.template.general;

import java.util.List;

import com.coral.vaadin.widget.Result;
import com.google.common.collect.Lists;
import com.mocha.template.IAppActionSection;
import com.mocha.template.IAppSection;
import com.mocha.template.IAppView;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralSectionWidget;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class GeneralAppView extends VerticalLayout implements IAppView {

	private static final long serialVersionUID = -8099705376602570362L;
	
	private String left_layout_width = GeneralTemplateCst.left_edit_layout_width;
	private String left_content_width = GeneralTemplateCst.left_edit_content_width;
	
	private String label;
	private List<IAppSection> sections = Lists.newArrayList();
	private IAppActionSection actionSection;

	public GeneralAppView() {
		addStyleName("viewPanel");
		setSpacing(true);
	}

	public void attach() {
		// set view label
		if(label != null) {
			GeneralSectionContentVO sectionContent = new GeneralSectionContentVO();
			sectionContent.setSectionLabel(label);
			GeneralSectionWidget sectionWidget = GeneralTemplateWidgetFactory.buildGeneralSectionWidget(sectionContent);
			sectionWidget.setWidgetWidth(left_layout_width, left_content_width);
			this.addComponent(sectionWidget);
		}
		// add sections
		for(IAppSection section : sections) {
			this.addComponent(section);
		}
		// set action Panel
		if(actionSection != null) {
			this.addComponent(actionSection);
			this.setComponentAlignment(actionSection, Alignment.TOP_CENTER);
		}
	}
	
	public List<Result> validate() {
		List<Result> errorResults = Lists.newArrayList();
		for(IAppSection section : sections) {
			List<Result> sectionErrors = section.validate();
			if(sectionErrors.size() > 0) {
				errorResults.addAll(sectionErrors);
			}
		}
		return errorResults;
	}
	
	public void setReadOnly(boolean readonly) {
		for(IAppSection section : sections) {
			section.setReadOnly(readonly);
		}
	}
	
	public void addSection(IAppSection section) {
		sections.add(section);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setActionSection(IAppActionSection actionSection) {
		this.actionSection = actionSection;
	}
}
