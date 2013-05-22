package com.coral.foundation.jpa.gen

import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.helper.VGenHelper
import java.util.List
import com.coral.foundation.md.model.helper.GLog
import com.coral.foundation.utils.StrUtils
import com.coral.foundation.md.model.Mocha

class JPADaoTemplate {
	
	Entity entity
	List<Mocha> corals
  	String entityName
  	String entityVariable
  	String daoIntfClassName
  	String daoImplClassName
  	String daoIntfPackage
  	String daoImplPackage
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		
  		val currentCoral = VGenHelper::getCurrentCoral(entity,corals);
  		daoIntfPackage = currentCoral.getDaoIntfPackage;
  		daoImplPackage = currentCoral.getDaoImplPackage;
  		daoIntfClassName = VGenHelper::genDaoIntf(entityName)
  		daoImplClassName = VGenHelper::genDaoImpl(entityName)
  	}
  	
	def generateInterface()'''
«««		Generate package name & import.
		«GENInterfacePackageImport»
«««		Generate class name and class javadoc
		«GENInterfaceHead»
«««			«GENInterfaceMethod»
«««			«GENBuildVeriable»
«««			Class Constructor
«««			«GENConstructor»
«««			***Build widget page***
«««			«GENBuildMethod»
«««			Widget Implement Method
«««			«GENWidgetImplMethod»
«««		Ending class generation
		«GENClassEnd(daoIntfClassName)»
	'''
	def GENInterfacePackageImport()'''
		package «daoIntfPackage»;
		import java.util.List;
		import com.coral.foundation.jpa.Dao;
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		
	'''
	def GENInterfaceHead()'''
		«GLog::startClass(daoIntfClassName)»
		/**
		  * «daoIntfClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public interface «daoIntfClassName» extends Dao<«entityName»> {
			
	'''
	
//	DAO Implements
	def generateImplement()'''
«««		Generate package name & import.
		«GENImplementPackageImport»
«««		Generate class name and class javadoc
		«GENImplementHead»
«««			«GENInterfaceMethod»
«««			«GENBuildVeriable»
«««			Class Constructor
			«GENConstructor»
«««			***Build widget page***
«««			«GENBuildMethod»
«««			Widget Implement Method
«««			«GENWidgetImplMethod»
«««		Ending class generation
		«GENClassEnd(daoImplClassName)»
	'''
	def GENImplementPackageImport()'''
		package «daoImplPackage»;
		import «daoIntfPackage+".*"»;
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import com.coral.foundation.jpa.impl.JpaDao;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		
	'''
	def GENImplementHead()'''
		«GLog::startClass(daoImplClassName)»
		/**
		  * «daoImplClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «daoImplClassName» extends JpaDao<«entityName»> implements «daoIntfClassName» {
			
			Logger log=LoggerFactory.getLogger(«daoImplClassName».class);
	'''
	def GENConstructor()'''
		public «daoImplClassName»() {
			super();
			log.debug(""+«daoImplClassName».class);
		}
	'''
	def GENClassEnd(String className)'''
		«GLog::endClass(className)»
		}
	'''
}