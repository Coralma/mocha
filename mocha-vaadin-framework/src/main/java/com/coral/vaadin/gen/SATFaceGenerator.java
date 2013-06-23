/**
 * 
 */
package com.coral.vaadin.gen;

import java.util.List;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.CodeTable;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.ReportDef;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.helper.VAppGenHelper;
import com.coral.vaadin.template.common.CodeTableScript;
import com.coral.vaadin.template.common.EntityCard;
import com.coral.vaadin.template.common.EntityCardSearch;
import com.coral.vaadin.template.common.EntityCardSearchPresenter;
import com.coral.vaadin.template.common.EntityEditPresenter;
import com.coral.vaadin.template.common.EntityEditView;
import com.coral.vaadin.template.common.ReportDefinition;
import com.coral.vaadin.template.sat.TAppMainPage;
import com.coral.vaadin.template.sat.TAppMainPagePresenter;
import com.coral.vaadin.template.sat.TControllerMenuPanel;
import com.coral.vaadin.template.sat.TFunctionPanel;

/**
 * @author Coral.Ma
 *
 */
public class SATFaceGenerator extends AbstractFaceGenerator {
	
	
	public SATFaceGenerator(Resource resource) {
		super(resource);
	}

	@Override
	public void generater() throws Exception {
		try {
			for(Mocha mocha : mochas) {
				for(App app : mocha.getApps()) {
					generateAppMainPagePresenter(app);
					generateAppMainPage(app);
					generateControllerMenuPanel(app);
					generateFunctionPanel(app);
				}
				for(View view : mocha.getViewList()) {
					if("EntityEditView".equals(view.getTemplate())) {
						generateEntityView(view);
						generateEntityPresenter(view);
					}
					if("EntityCardSearch".equals(view.getTemplate())) {
						generateEntitySearch(view);
						generateEntitySearchPresenter(view);
					}
				}
				for(ReportDef reportDef : mocha.getReportDefList()) {
					generateReportDef(reportDef);
				}
			}
			generateCodeTableScript();
//			generateFactory(coralList);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void generateCodeTableScript() {
		boolean hasCodeTable = false;
		for(Mocha mocha : this.mochas) {
	          List<CodeTable> codeTableList = mocha.getCodeTableList();
	          if(codeTableList.size() > 0) {
	        	  hasCodeTable = true;
	        	  break;
	          }
		}
		if(hasCodeTable) {
			CodeTableScript codeTableScript = new CodeTableScript();
			codeTableScript.init(mochas);
			GenerateModel generateModel = new GenerateModel();
			generateModel.setClassName("codeTable");
			generateModel.setClassContent(codeTableScript.generate().toString());
			generateModel.setPath(resource.getDdlGenPath());
			generateModel.setSuffix("sql");
			fileGenerater(generateModel);
		}
	}

	public void generateAppMainPagePresenter(App app) {
		TAppMainPagePresenter appMainPagePresenter = new TAppMainPagePresenter();
		appMainPagePresenter.init(app, mochas);
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genAppMainPagePresenterClassName(app.getName()));
		generateModel.setClassContent(appMainPagePresenter.generate().toString());
		generateModel.setPkg(SystemConstant.GENERATED_PRESENTER);
		generateModel.setPath(resource.getPresenterGenPath());
		generateModel.setIgnoreExisted(true);
		fileGenerater(generateModel);
	}

	public void generateAppMainPage(App app) {
		TAppMainPage appMainPage = new TAppMainPage();
		appMainPage.init(app, mochas);
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genAppMainPageClassName(app.getName()));
		generateModel.setClassContent(appMainPage.generate().toString());
		generateModel.setPkg(SystemConstant.GENERATED_PAGE);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}

	public void generateControllerMenuPanel(App app) {
		TControllerMenuPanel controllerMenuPanel = new TControllerMenuPanel();
		controllerMenuPanel.init(app, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genControllerMenuPanelClassName(app.getName()));
		generateModel.setClassContent(controllerMenuPanel.generate().toString());
		generateModel.setPkg(SystemConstant.GENERATED_PAGE);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
	
	public void generateFunctionPanel(App app) {
		TFunctionPanel functionPanel = new TFunctionPanel();
		functionPanel.init(app, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genFunctionPanelClassName(app.getName()));
		generateModel.setClassContent(functionPanel.generate().toString());
		generateModel.setPkg(SystemConstant.GENERATED_PAGE);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
	
	public void generateEntityView(View view) {
		EntityEditView editView = new EntityEditView();
		editView.init(view, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.generateClassName(view.getName()));
		generateModel.setClassContent(editView.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_VIEW_PKG);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
	
	public void generateEntityPresenter(View view) {
		EntityEditPresenter editPresenter = new EntityEditPresenter();
		editPresenter.init(view, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genAppMainPagePresenterClassName(view.getName()));
		generateModel.setClassContent(editPresenter.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_PRESENTER_PKG);
		generateModel.setPath(resource.getPresenterGenPath());
		generateModel.setIgnoreExisted(true);
		fileGenerater(generateModel);
	}
	
	public void generateEntitySearch(View view) {
		// generate the search page it will include the related card.
		EntityCardSearch entityCardSearch = new EntityCardSearch();
		entityCardSearch.init(view, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.generateClassName(view.getName()));
		generateModel.setClassContent(entityCardSearch.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_VIEW_PKG);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
		
		// generate the card file
		EntityCard entityCard = new EntityCard();
		entityCard.init(view, mochas);
		
		generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.generateCardClassName(view.getName()));
		generateModel.setClassContent(entityCard.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_VIEW_PKG);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
	
	public void generateEntitySearchPresenter(View view) {
		EntityCardSearchPresenter entityCardSearchPresenter = new EntityCardSearchPresenter();
		entityCardSearchPresenter.init(view, mochas);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(VAppGenHelper.genAppMainPagePresenterClassName(view.getName()));
		generateModel.setClassContent(entityCardSearchPresenter.generate().toString());
		generateModel.setPkg(SystemConstant.ENTITY_EDIT_PRESENTER_PKG);
		generateModel.setPath(resource.getPresenterGenPath());
		generateModel.setIgnoreExisted(true);
		fileGenerater(generateModel);
	}
	
	public void generateReportDef(ReportDef reportDef) {
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.init(mochas, reportDef);
		
		GenerateModel generateModel = new GenerateModel();
		generateModel.setClassName(reportDef.getName());
		generateModel.setClassContent(reportDefinition.generate().toString());
		generateModel.setPkg(SystemConstant.GENERATED_PAGE);
		generateModel.setPath(srcGenPath);
		fileGenerater(generateModel);
	}
}
