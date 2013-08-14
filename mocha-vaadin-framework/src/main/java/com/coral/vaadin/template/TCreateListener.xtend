package com.coral.vaadin.template

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import java.util.List

class TCreateListener {
	
	Entity entity
	List<Mocha> corals
  	String listenerName
  	String entityName
  	String entityVariable
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		listenerName = VGenHelper::genCreateListenerName(entityName)
  	}
  	
	def generate()'''
«««		Generate package name & import.
		«GENPackageImport»
«««		Generate class name and class javadoc
		«GENClassHead»
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::TCREATE_LISTENER_PKG»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.Widget;
		import com.coral.vaadin.widget.listener.PresenterListener;
		
	'''
	def GENClassHead()'''
		/**
		  * «listenerName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public interface «listenerName» extends PresenterListener {
			
	'''
	
	def GENClassEnd()'''
		}
	'''	
}