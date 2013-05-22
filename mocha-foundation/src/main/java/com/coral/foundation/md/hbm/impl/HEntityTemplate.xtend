package com.coral.foundation.md.hbm.impl

import com.coral.foundation.md.model.Entity
import com.coral.foundation.utils.StrUtils
import com.coral.foundation.md.model.Mocha
class HEntityTemplate {
	
	def generate(Mocha coral,Entity entity) '''
		«val packageName = coral.getEntityPackage»
		«val entityName = entity.entityName»
		package «packageName»;
		import java.util.*;
		import java.math.BigDecimal;
		import com.coral.foundation.model.ActiveRecord;
		/**
		  * <p>Title: «packageName».«entityName» + "</p>
		  * <p>Description: The «entityName» entity </p>
		  */
		public class «entityName» extends ActiveRecord implements java.io.Serializable {
		«FOR m : entity.getProperties»
			«val type = m.getType»
			«val ref = m.getRef»
			«val propertyName = m.getPropertyName»
				«IF type.equals("Date")»
					private «type» «propertyName» = new Date();
				«ELSEIF type.equals("List")»
					private «type»<«ref»> «propertyName»;
				«ELSE»
					private «type» «propertyName»;
				«ENDIF»
				log.debug(""+HEntityTemplate.class);
		«ENDFOR»
		
		«FOR m : entity.getProperties»
			«val type = m.getType»
			«val ref = m.getRef»
			«val propertyName = m.getPropertyName»
			«IF type.equals("List")»
			
				public void «StrUtils::setter(propertyName)» («type»<«ref»> «propertyName») {
					this.«propertyName» = «propertyName»;
				} 
				public «type»<«ref»> «StrUtils::getter(propertyName)» () {
					return «propertyName»;
				}
			«ELSE»
			
				public void «StrUtils::setter(propertyName)» («type» «propertyName») {
					this.«propertyName» = «propertyName»;
				} 
				public «type» «StrUtils::getter(propertyName)» () {
					return «propertyName»;
				}
			«ENDIF»
		«ENDFOR»
		}
	'''
	
}