package com.coral.vaadin.template

import com.coral.foundation.md.model.helper.GLog
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.vaadin.widget.Viewer
import java.util.List
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Mocha

class PresenterFactory {

	List<Mocha> corals
	String viewClassName
  
  	def init(List<Mocha> corals, String viewClassName) {
  		this.corals = corals;
  		this.viewClassName = viewClassName; 
  	}

	def generate()'''
«««		Generate package name & import.
		«GENPackageImport»
«««		Generate class name and class javadoc
		«GENClassHead»
«««			***Build widget page***
			«GENBuildMethod»
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::GENERATED_PAGE»;
		
		import com.coral.vaadin.controller.PageFactory;
«««		import «SystemConstant::TCREATE_PAGE_PKG+".*"»;
«««		import «SystemConstant::TSEARCH_PAGE_PKG+".*"»;
		«IF VGenHelper::hasView(corals)»
			import «SystemConstant::TCREATE_PRESENTER_PKG+".*"»;
		«ENDIF»
«««		import «SystemConstant::TSEARCH_PRESENTER_PKG+".*"»;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.view.builder.ViewContext;
		import com.coral.foundation.core.impl.MochaEventBus;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		
		
	'''
	def GENClassHead()'''
		«GLog::startClass(viewClassName)»
		/**
		  * «viewClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «viewClassName» implements PageFactory {
			
	'''
	
	def GENBuildMethod()'''
«««		//TODO create factory method.
		public Presenter getPresenter(String entityName, MochaEventBus eventBus) {
			«FOR coral : corals»
				«FOR entity : coral.getEntityList»
					«IF VGenHelper::getCurrentView(entity, Viewer::TCreate) != null»
						«val createPresenterClassName = VGenHelper::genCreatePresenterClassName(entity.entityName)»
«««						«val createViewClassName = VGenHelper::genCreateViewClassName(entity.entityName)»
						«GENGetterMethod(createPresenterClassName,entity.entityName)»
					«ENDIF»
«««					«IF VGenHelper::getCurrentView(entity, Viewer::TSearch) != null»
«««						«val searchPresenterClassName = VGenHelper::genSearchPresenterClassName(entity.entityName)»
«««						«val searchViewClassName = VGenHelper::genSearchViewClassName(entity.entityName)»
«««						«GENGetterMethod(searchPresenterClassName,searchViewClassName)»
«««					«ENDIF»
				«ENDFOR»
			«ENDFOR»
			return null;
		}
	'''
	def GENGetterMethod(String presenterClass, String entityName) '''
		if("«entityName»".equals(entityName)) {
			ViewContext viewContext = new ViewContext(entityName);
			return new «presenterClass»(viewContext, eventBus);
		}
	'''
	
	def GENClassEnd()'''
		«GLog::endClass(viewClassName)»
		}
	'''
}