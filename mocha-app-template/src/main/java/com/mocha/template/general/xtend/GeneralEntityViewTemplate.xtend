package com.mocha.template.general.xtend

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.View
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VAppGenHelper

class GeneralEntityViewTemplate {
	
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
		
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.fields.FieldStatus;
		import com.mocha.template.IAppActionSection;
		import com.mocha.template.IAppSection;
		import com.mocha.template.IAppView;
		import com.mocha.template.general.GeneralEntityView;
		import com.mocha.template.general.utils.GeneralTemplateCst;
		import com.vaadin.ui.Layout;
		import com.vaadin.ui.VerticalLayout;
		
		import «view.entity.entityClass»;
	'''
	
	def GENClassHead()'''
		public class «viewClassName» extends GeneralEntityView implements Viewer {
			
			public «viewClassName»() {
				super();
			}
	'''
	
	def GENBuildMethod()'''
		public void build() {
			final IAppView viewPanel = createGeneralAppView();
			viewPanel.setLabel("Leave Application");
			IAppSection sectionPanel;
			FieldStatus fieldStatus;
			
			«FOR section : view.getSections»
				sectionPanel = createGeneralAppSection("«section.getName»");
				sectionPanel.setLabel("«section.label»");
				viewPanel.addSection(sectionPanel);
				
				«FOR field : section.getViewFields»
					fieldStatus = «VAppGenHelper::generateFieldStatus(field,mochas)»
					sectionPanel.addField(createFieldWidget(fieldStatus));
					
				«ENDFOR»
			«ENDFOR»
			IAppActionSection actionPanel = createGeneralAppActionSection();
			viewPanel.setActionPanel(actionPanel);
			«FOR action : view.getViewActions»
				actionPanel.addButton(createActionButton("«action.name»", "«VAppGenHelper::generateActionLabel(action)»", "«action.action»"));
			«ENDFOR»
			
			addComponent(viewPanel);
		}
		
		@Override
		public Layout buildControlView() {
			VerticalLayout layout = new VerticalLayout();
			layout.setWidth(GeneralTemplateCst.right_control_layout_width);
			return layout;
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