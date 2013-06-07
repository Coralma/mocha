package com.coral.vaadin.template.common

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.View
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VAppGenHelper

class EntityEditView {
	
	String viewClassName;
	View view
	List<Mocha> mochas
	
	def init(View view, List<Mocha> mochas) {
		this.view = view;
		this.mochas = mochas;
		this.viewClassName = VAppGenHelper::generateClassName(view.name);
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
		import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
		import com.coral.vaadin.view.template.sat.panel.IViewPanel;
		import com.coral.vaadin.view.template.sat.panel.IActionPanel;
		import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.fields.FieldStatus;
		import «view.entity.entityClass»;
	'''
	
	def GENClassHead()'''
		public class «viewClassName» extends EntityViewPanel implements Viewer {
			
			public «viewClassName»() {
				super();
			}
	'''
	
	def GENBuildMethod()'''
		public void build() {
			final IViewPanel viewPanel = createViewPanel();
			ISectionPanel sectionPanel;
			FieldStatus fieldStatus;
			
			«FOR section : view.getSections»
				sectionPanel = createSectionPanel("«section.getName»");
				sectionPanel.setLabel("«section.label»");
				viewPanel.addSection(sectionPanel);
				
				«FOR field : section.getViewFields»
					fieldStatus = «VAppGenHelper::generateFieldStatus(field,mochas)»
					sectionPanel.addField(createFieldWidget(fieldStatus));
					
				«ENDFOR»
			«ENDFOR»
			IActionPanel actionPanel = createActionPanel();
			viewPanel.setActionPanel(actionPanel);
			«FOR action : view.getViewActions»
				actionPanel.addButton(createActionButton("«action.name»", "«VAppGenHelper::generateActionLabel(action)»", "«action.action»"));
			«ENDFOR»
			
			addComponent(viewPanel);
		}
	'''
	
	def GENGetMethod()'''
		public Class getEntityClass() {
			return «view.entity.entityName».class;
		}
		
		public String getViewerTitle() {
			return "«VAppGenHelper::generateCreateEntityViewTitle(view)»";
		}
	'''
	
	def GENClassEnd()'''
		}
	'''
}