package com.mocha.template.general.generator;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.vaadin.gen.AbstractFaceGenerator;
import com.coral.vaadin.gen.AbstractFaceGenerator.GenerateModel;
import com.mocha.template.constants.GeneratorConstant;
import com.mocha.template.general.xtend.GeneralEntityViewTemplate;

public class GeneralTemplateGenerator extends AbstractFaceGenerator {

	public GeneralTemplateGenerator(Resource resource) {
		super(resource);
	}

	@Override
	public void generater() throws Exception {
		try {
			for(Mocha mocha : mochas) {
				for(View view : mocha.getViewList()) {
					if(GeneratorConstant.ENTITY_EDIT_VIEW.equals(view.getTemplate())) {
						generateEntityView(view);
						generateEntityPresenter(view);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateEntityView(View view) {
		GeneralEntityViewTemplate viewTemplate = new GeneralEntityViewTemplate();
		viewTemplate.init(view, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.generateClassName(view.getName()));
		generateModel.setClassContent(viewTemplate.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_VIEW_PKG);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
	
	public void generateEntityPresenter(View view) {
		
	}
}
