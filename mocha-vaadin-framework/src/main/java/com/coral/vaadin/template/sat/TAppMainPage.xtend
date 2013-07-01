package com.coral.vaadin.template.sat

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.App
import com.coral.foundation.md.model.helper.VAppGenHelper
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.ReportDef
import com.coral.foundation.md.model.helper.VGenHelper

class TAppMainPage {

	String appClassName
	String controlMenuPanel
	String functionPanel
	App app
	List<Mocha> mochas
	ReportDef reportDef

	def init(App app, ReportDef reportDef, List<Mocha> mochas) {
  		this.app = app
  		this.reportDef = reportDef;
  		this.appClassName = VAppGenHelper::genAppMainPageClassName(app.getName)
  		this.controlMenuPanel = VAppGenHelper::genControllerMenuPanelClassName(app.getName);
  		this.functionPanel = VAppGenHelper::genFunctionPanelClassName(app.getName);
  		this.mochas = mochas
  	}
  	
  	def generate()'''
  		«GENPackageImport»
  		«GENClassHead»
  			«GENAttachMethod»
  			«GENImplementMethod»
  			«GENGetMethod»
  		«VGenHelper::classEnd»
  	'''
  	
  	def GENPackageImport()'''
		package «SystemConstant::GENERATED_PAGE»;
		
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.view.template.sat.AppMainPage;
		import com.coral.vaadin.view.template.sat.AppContentEvent;
		import com.coral.foundation.report.AbstrctAppRawData;
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
		import «SystemConstant::ENTITY_EDIT_PRESENTER_PKG».*;
		import «SystemConstant::GENERATED_PAGE».«controlMenuPanel»;
		import «SystemConstant::GENERATED_PAGE».«functionPanel»;
	'''
	
	def GENClassHead()'''
		public class «appClassName» extends AppMainPage implements ControllerMenuListener {
			
			private «controlMenuPanel» controllerMenu = new «controlMenuPanel»();
			private «functionPanel» functionPanel = new «functionPanel»();
			«IF reportDef != null»
				private static AbstrctAppRawData reportData = new «reportDef.name»();
			«ENDIF»
	'''
	
	def GENAttachMethod()'''
		public void attach() {
			addComponent(controllerMenu);
			addComponent(functionPanel);
			// init the main page.
			AppContentEvent event = new AppContentEvent();
			«val home = app.getAppNavigation.getAppMenus.get(0)»
			«IF home.getCustomizedClass != null»
				event.setCustomizeClass("«home.getCustomizedClass»");
				controllerMenu.setMenuStyle(null, "«home.getCustomizedClass»");
			«ELSEIF home.getViewName != null»
				event.setViewName("«home.getViewName»");
				controllerMenu.setMenuStyle("«home.getViewName»", null);
			«ENDIF»
			«IF reportDef != null»
				eventBus.put("appCustomReprotRowData", reportData);
			«ENDIF»
			eventBus.post(event);
			
		}
	'''
	
	def GENImplementMethod()'''
		public void showView(String viewName) {
			Presenter presenter = null;
			«FOR mocha : mochas»
				«FOR viewer : mocha.viewList»
					«val viewPresenterName = VAppGenHelper::genAppMainPagePresenterClassName(viewer.name)»
					if("«viewer.getName»".equals(viewName)) {
						presenter = new «viewPresenterName»(eventBus);
					}
				«ENDFOR»
			«ENDFOR»
			if(presenter == null) {
				throw new RuntimeException(this.getClass() + "show view : " + viewName + " doesn't exist.");
			}
			functionPanel.setContent(presenter.go());
			presenter.bind();
		}

		public void showPanel(Class customizedPresenter) {
			try {
				Presenter presenter = (Presenter) customizedPresenter.getConstructor(MochaEventBus.class).newInstance(eventBus);
				if(presenter.isFullSize()) {
					functionPanel.setFullContent(presenter.go());
				} else {
					functionPanel.setContent(presenter.go());
				}
				presenter.bind();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(this.getClass() + "Show Customized Class : " + customizedPresenter + " error.");
			}
		}
	'''
	
	def GENGetMethod()'''
		/**
		 * @return the controllerMenu
		 */
		public «controlMenuPanel» getControllerMenu() {
			return controllerMenu;
		}

		/**
		 * @return the functionPanel
		 */
		public «functionPanel» getFunctionPanel() {
			return functionPanel;
		}
	'''
}