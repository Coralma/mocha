package com.coral.foundation.jpa.gen

import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import java.util.List

class JPADaoTemplate {
	
	Entity entity
	List<Mocha> corals
  	String entityName
  	String entityVariable
  	String daoImplClassName
  	String daoIntfPackage
  	String daoImplPackage
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		
  		val currentCoral = VGenHelper::getCurrentCoral(entity,corals);
  		daoImplPackage = currentCoral.getDaoImplPackage;
  		daoImplClassName = VGenHelper::genDao(entityName)
  	}
  	
//	DAO Implements
	def generateImplement()'''
«««		Generate package name & import.
		«GENImplementPackageImport»
«««		Generate class name and class javadoc
		«GENImplementHead»
			«GENDefaultMethod»
		«GENClassEnd(daoImplClassName)»
	'''
	def GENImplementPackageImport()'''
		package «daoImplPackage»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import com.coral.foundation.persistence.BaseDao;
		
	'''
	def GENImplementHead()'''
		/**
		  * «daoImplClassName» is a auto Generated class. Please don't modify it.
		  */
		public class «daoImplClassName» extends BaseDao<«entityName»> {
			
	'''
	def private String GENDefaultMethod() '''
		@Override
		public Class<«entityName»> getEntityClass() {
			return «entityName».class;
		}
	'''
	def GENClassEnd(String className)'''
		}
	'''
}