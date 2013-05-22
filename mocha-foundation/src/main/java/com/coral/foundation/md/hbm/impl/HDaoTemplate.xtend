package com.coral.foundation.md.hbm.impl

import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.Mocha

class HDaoTemplate {
	
	def generate(Mocha coral, Entity entity) '''
		«val packageName = coral.getDaoImplPackage»
		«val entityPackage = coral.getEntityPackage»
		«val entityName = entity.getEntityName»
		«val daoName = entityName+"DAO"»
		package «packageName»;
		
		import com.coral.foundation.hibernate.impl.GenericHibernateDAO;
		import «entityPackage».«entityName»;
		
		/**
		  * @author Coral
		  *
		  */
		public class «daoName» extends GenericHibernateDAO<«entityName», Long> {
			
		}
	'''
}