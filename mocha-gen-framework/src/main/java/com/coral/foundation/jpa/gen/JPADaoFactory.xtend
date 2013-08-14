package com.coral.foundation.jpa.gen

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper
import java.util.List
 
/*
 * public class GeneratedDaoFactory implements DaoFactory {

	public Dao getDao(Class entityClass) {
		if(entityClass.equals(CargoPolicy.class)) {
			return SpringContextUtils.getBean(CargoPolicyDao.class);
		}
		if(entityClass.equals(CargoInsured.class)) {
			return SpringContextUtils.getBean(CargoInsuredDao.class);
		}
		return null;
	}
}
*/
class JPADaoFactory {
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
		import «SystemConstant::TCREATE_PAGE_PKG+".*"»;
		import «SystemConstant::TSEARCH_PAGE_PKG+".*"»;
		import «SystemConstant::TCREATE_PRESENTER_PKG+".*"»;
		import «SystemConstant::TSEARCH_PRESENTER_PKG+".*"»;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.foundation.core.impl.MochaEventBus;
		
	'''
	def GENClassHead()'''
		/**
		  * «viewClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «viewClassName» implements PageFactory {
			
	'''
	def GENBuildMethod()'''
«««		//TODO create factory method.
		public Dao getDao(Class entityClass) {
			«FOR coral : corals»
				«FOR entity : coral.getEntityList»
					«val entityName = entity.entityName»
					«val daoIntfClassName = VGenHelper::genDaoIntf(entityName)»
					«val daoImplClassName = VGenHelper::genDaoImpl(entityName)»
					«val daoImplPackage = coral.getDaoImplPackage»
				«ENDFOR»
			«ENDFOR»
			return null;
		}
	'''
	def GENGetterMethod(String presenterClass, String viewClass) '''
		if("«viewClass»".equals(viewerName)) {
			return new «presenterClass»(new «viewClass»(), eventBus);
		}
	'''
	def GENClassEnd()'''
		}
	'''
}