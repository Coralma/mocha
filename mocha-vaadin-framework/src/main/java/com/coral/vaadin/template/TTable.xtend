package com.coral.vaadin.template

import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.md.model.helper.GLog
import com.coral.vaadin.widget.Viewer
import java.util.List
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Mocha

class TTable {
	
	Entity entity
	List<Mocha> corals
  	String tableClassName
  	String entityName
  	String tableContainer
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		tableClassName = VGenHelper::genTableClassName(entityName)
  		tableContainer = VGenHelper::genTableContainerClassName(entityName)
  	}
  	
	def generateTable()'''
«««		Generate package name & import.
		«GENPackageImport»
«««		Generate class name and class javadoc
		«GENClassHead»
«««			***Build verible ***
			«GENBuildVeriable»
«««			Class Constructor
			«GENConstructor»
«««			***Build widget page***
			«GENBuildContainerClass»
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::TTable_PKG»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import java.io.Serializable;
		import com.vaadin.data.util.BeanItemContainer;
		import com.coral.vaadin.widget.table.AbstractTable;
		import com.coral.vaadin.widget.table.EntityTable;
		import com.coral.vaadin.widget.table.EntityContainer;
		
	'''
	def GENClassHead()'''
		«GLog::startClass(tableClassName)»
		/**
		  * «tableClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «tableClassName» extends AbstractTable {
			
			private static final long serialVersionUID = «VGenHelper::genserialVersionUID»L;
			
	'''
	def GENConstructor()'''
		public «tableClassName»(String viewClass) {
			this(EntityTable.POPUP, viewClass);
		}
		
		public «tableClassName»(String type, String viewClass) {
			super(type,viewClass);
			table.setDataContainer(new «tableContainer»());
		}
		
	'''
	def GENBuildVeriable()'''
		«val viewer = VGenHelper::getCurrentView(entity, Viewer::TTable)»
		public static final Object[] NATURAL_COL_ORDER = new Object[] {
			«FOR section : viewer.getSections»
				«FOR field : section.viewFields»
					"«field.fieldName»",
				«ENDFOR»
			«ENDFOR»
		};
		
		public static final String[] COL_HEADERS_ENGLISH = new String[] {
			«FOR section : viewer.getSections»
				«FOR field : section.viewFields»
					«val p = VGenHelper::getFieldProperty(entity, field.fieldName)»
					"«p.label»",
				«ENDFOR»
			«ENDFOR»
		};
	'''
	def GENBuildContainerClass()'''
		public class «tableContainer» extends BeanItemContainer<«entityName»> implements Serializable, EntityContainer {

			private static final long serialVersionUID = «VGenHelper::genserialVersionUID»L;
		
			public «tableContainer»() {
				super(«entityName».class);
			}
			
			@Override
			public Object[] getVisibleColumns() {
				return NATURAL_COL_ORDER;
			}

			@Override
			public String[] getColumnHeaders() {
				return COL_HEADERS_ENGLISH;
			}
		}
	'''
	
	
	
	
	def GENClassEnd()'''
		«GLog::endClass(tableClassName)»
		}
	'''
}