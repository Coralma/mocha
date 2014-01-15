package com.mocha.template.general.widget;

import java.util.List;

import com.coral.foundation.utils.DefaultTypeUtils;
import com.coral.foundation.utils.ReflectionUtils;
import com.mocha.common.widget.EntityGridLayout;
import com.mocha.common.widget.vo.EntityGridVO;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.widget.listener.GeneralSectionListener;
import com.mocha.template.general.widget.vo.GeneralEntityCardSectionVO;
import com.vaadin.ui.VerticalLayout;

public class GeneralEntityCardSectionWidget extends AbstractGeneralWidget {

	protected String right_section_width= GeneralTemplateCst.right_section_width;
	protected GeneralEntityCardSectionVO entityCardSectionVO;
	protected GeneralSectionListener sectionListener;
	
	public GeneralEntityCardSectionWidget(GeneralEntityCardSectionVO entityCardSectionVO) {
		this.entityCardSectionVO = entityCardSectionVO;
	}
	
	public void attach() {
		VerticalLayout sectionCaption = buildSectionHead(entityCardSectionVO.getSectionLabel());
		this.addComponent(sectionCaption);
		
		List<Object> entities = entityCardSectionVO.getEntities();
		String[] variables = entityCardSectionVO.getFieldVariables();
		String[] labels = entityCardSectionVO.getFieldLabels();
		// using the EntityGridLayout for layout 
		EntityGridLayout entityGridLayout = new EntityGridLayout();
		for(Object entity : entities) {
			for(int i=0; i < variables.length; i++) {
				String variable = variables[i];
				String label = labels[i];
				Object value = ReflectionUtils.getVariableValue(entity, variable);
				EntityGridVO entityGridVO = new EntityGridVO(label, DefaultTypeUtils.getDefaultTypeString(value));
				if(0 == i) {
					entityGridVO.setChangeLine(true);
				}
				entityGridLayout.addEntityGridVO(entityGridVO);
			}
		}
		this.addComponent(entityGridLayout);
	}
}