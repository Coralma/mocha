package com.coral.vaadin.template.sat

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.App
import com.coral.foundation.md.model.helper.VAppGenHelper
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper

class TFunctionPanel {
	
	App app
	List<Mocha> mochas	
	String functionPanel
	
	def init(App app, List<Mocha> mochas) {
  		this.app = app
		this.functionPanel = VAppGenHelper::genFunctionPanelClassName(app.getName);
  		this.mochas = mochas
  	}
  	
  	def generate()'''
  		«GENPackageImport»
  		«GENClassHead»
  			«GENAttachMethod»
  			«GENGetMethod»
  		«VGenHelper::classEnd»
  	'''
  	
  	def GENPackageImport()'''
		package «SystemConstant::GENERATED_PAGE»;
		
		import java.util.List;
		
		import com.coral.vaadin.view.template.sat.FunctionMenu;
		import com.coral.vaadin.view.template.sat.FunctionPanel;
		import com.google.common.collect.Lists;
		
	'''
	
	def GENClassHead()'''
		public class «functionPanel» extends FunctionPanel {
			
	'''
	
	def GENAttachMethod()'''
		@Override
		public List<FunctionMenu> getCreationFunctionMenu() {
			List<FunctionMenu> creationMenus = Lists.newArrayList();
			«IF app.getAppCreation != null»
				«FOR creationMenu : app.getAppCreation.getAppMenus»
					creationMenus.add(«VAppGenHelper::generateFunctionMenu(creationMenu)»);
				«ENDFOR»
			«ENDIF»
			return creationMenus;
		}

		@Override
		public List<FunctionMenu> getSettingFunctionMenu() {
			List<FunctionMenu> settingMenus = Lists.newArrayList();
			«IF app.getAppSetting != null»
				«FOR settingMenu : app.getAppSetting.getAppMenus»
					settingMenus.add(«VAppGenHelper::generateFunctionMenu(settingMenu)»);
				«ENDFOR»
			«ENDIF»
			settingMenus.add(FunctionMenu.create().setName("exit").setLabel("Back to homepage"));
			return settingMenus;
		}
	'''
	
	def GENGetMethod()'''
	'''
}