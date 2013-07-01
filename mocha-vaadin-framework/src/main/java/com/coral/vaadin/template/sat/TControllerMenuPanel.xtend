package com.coral.vaadin.template.sat

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.App
import com.coral.foundation.md.model.helper.VAppGenHelper
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.AppMenu
import com.coral.foundation.md.model.AppNavigation
import com.coral.foundation.md.model.helper.VGenHelper

class TControllerMenuPanel {
	
	App app
	AppNavigation appNavigation
	String appName
	String appLabel
	List<Mocha> mochas	
	String controlMenuPanel
	
	def init(App app, List<Mocha> mochas) {
  		this.app = app
  		this.appNavigation = app.getAppNavigation
  		this.appName = app.getName
  		this.appLabel = app.getLabel
  		this.controlMenuPanel = VAppGenHelper::genControllerMenuPanelClassName(app.getName);
  		this.mochas = mochas
  	}
  	
  	def generate()'''
  		«GENPackageImport»
  		«GENClassHead»
  			«GENBuildMethod»
  			«GENGetMethod»
  		«VGenHelper::classEnd»
  	'''
  	
  	def GENPackageImport()'''
		package «SystemConstant::GENERATED_PAGE»;
		
		import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
		import com.vaadin.ui.Button;
		import com.vaadin.ui.Label;
	'''
	
	def GENClassHead()'''
		public class «controlMenuPanel» extends ControllerMenuPanel {
			
	'''
	
	def GENBuildMethod()'''
		public void build() {
			try {
				// init app name title
				«val appTitle = appName + "title"»
				Label «appTitle» = createAppTitle("«appLabel»");
				addComponent(«appTitle»);
				
				MenuAction action = null;
				Label groupTitle = null;
				Button menuItem = null;
				// create the main menu item.
				«FOR mainAppMenu : appNavigation.getAppMenus»
					«GENMenuItem(mainAppMenu)»
				«ENDFOR»
				
				// create the menu group and sub menu item.
				«FOR group : appNavigation.getAppMenuGroups»
					groupTitle = createMenuTitle("«group.getLabel»");
					addComponent(groupTitle);
					«FOR mainAppMenu : group.getAppMenus»
						«GENMenuItem(mainAppMenu)»
					«ENDFOR»		
				«ENDFOR»
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	'''
	
	def GENMenuItem(AppMenu menuItem)'''
		action = new MenuAction();
		«IF menuItem.getViewName != null»
			action.setView("«menuItem.getViewName»");
		«ELSEIF menuItem.getCustomizedClass != null»
			action.setPanel(Class.forName("«menuItem.getCustomizedClass»"));
		«ENDIF»
		menuItem = createMenu("«VAppGenHelper::generateMenuLabel(menuItem)»",action);
		addComponent(menuItem);
	'''
	
	def GENGetMethod()'''
	'''
}