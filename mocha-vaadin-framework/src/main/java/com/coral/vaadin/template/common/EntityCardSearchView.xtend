package com.coral.vaadin.template.common

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.View
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VAppGenHelper

class EntityCardSearch {

	String viewClassName;
	String cardClassName;
	View view
	List<Mocha> mochas
	
	def init(View view, List<Mocha> mochas) {
		this.view = view;
		this.mochas = mochas;
		this.viewClassName = VAppGenHelper::generateClassName(view.name);
		this.cardClassName = VAppGenHelper::generateCardClassName(view.name);
	}
	
	def generate()'''
		«GENPackageImport»
		
		«GENClassHead»
		
			«GENBuildMethod»
		
			«GENGetMethod»
		
		«GENClassEnd»
	'''
	
	def GENPackageImport()'''
		package «SystemConstant::ENTITY_EDIT_VIEW_PKG»;
		
		import java.util.List;

		import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
		import com.coral.vaadin.widget.field.FieldStatus;
		import com.google.common.collect.Lists;
		import «view.entity.entityClass»;
	'''
	
	def GENClassHead()'''
		public class «viewClassName» extends SearchPanel {
			
			private List entities = Lists.newArrayList();
		
	'''
	
	def GENBuildMethod()'''
		public void build() {
			FieldStatus fieldStatus = null;
			«FOR section : view.getSections»
				«IF "SearchCondition".equals(section.getTemplate)»
					«FOR field : section.getViewFields»
						fieldStatus = «VAppGenHelper::generateFieldStatus(field)»
						createFieldWidget(fieldStatus);
						
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		}
	'''
	
	def GENGetMethod()'''
		public Class getEntityCardClass() {
			return «cardClassName».class;
		}
		
		public String getViewerTitle() {
			return "«VAppGenHelper::generateSearchEntityViewTitle(view)»";
		}
		
		@Override
		public List getEntityList() {
			return entities;
		}
		
		@Override
		public void setValue(Object value) {
			if(value != null) {
				entities = (List) value;
			}
		}
	'''
	
	def GENClassEnd()'''
		}
	'''
}