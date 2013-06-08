package com.coral.vaadin.template.common

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.View
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VAppGenHelper

class EntityCard {
	
	String cardClassName
	View view
	List<Mocha> mochas
	
	def init(View view, List<Mocha> mochas) {
		this.view = view;
		this.mochas = mochas;
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
		import com.coral.vaadin.view.template.sat.panel.IActionPanel;
		import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
		import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
		import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;
		import com.vaadin.ui.Alignment;
		import com.vaadin.ui.HorizontalLayout;
		import com.vaadin.ui.Layout;
		import com.coral.vaadin.widget.fields.FieldStatus;
		import «view.entity.entityClass»;
	'''
	
	def GENClassHead()'''
		public class «cardClassName» extends SearchEntityCard {
			
	'''
	
	def GENBuildMethod()'''
		public void attach() {
			HorizontalLayout hlayout = new HorizontalLayout();
			hlayout.setWidth(cardWidth);
			this.addComponent(hlayout);
			
			Layout icon = getSearchIcon();
			hlayout.addComponent(icon);
			hlayout.setComponentAlignment(icon,Alignment.MIDDLE_CENTER);
			
			ISectionPanel sectionPanel = new DefaultSectionPanel();
			sectionPanel.setWidth(cardInfoWidth);
			sectionPanel.setSpacing(false);
			
			FieldStatus fieldStatus = null;
			«FOR section : view.getSections»
				«IF "SearchCard".equals(section.getTemplate)»				
					«FOR field : section.getViewFields»
						fieldStatus = «VAppGenHelper::generateFieldStatus(field, mochas)»
						sectionPanel.addField(createFieldWidget(fieldStatus));
						
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
			hlayout.addComponent(sectionPanel);
			hlayout.setComponentAlignment(sectionPanel,Alignment.TOP_LEFT);
			
			IActionPanel actionPanel = createCardActionPanel();
			«FOR section : view.getSections»
				«IF "SearchCard".equals(section.getTemplate)»				
					«FOR action : section.getViewActions»
						actionPanel.addButton(createActionButton("«action.name»", "«VAppGenHelper::generateActionLabel(action)»", "«action.action»"));		
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
			hlayout.addComponent(actionPanel);
			hlayout.setComponentAlignment(actionPanel, Alignment.MIDDLE_LEFT);
		}
	'''
	
	def GENGetMethod()'''
		@Override
		public String getIconName() {
			«FOR section : view.getSections»
				«IF "SearchCard".equals(section.getTemplate)»				
					«IF section.getIcon != null»
						return "«section.getIcon»";
					«ELSE»
						return null;
					«ENDIF»
				«ENDIF»
			«ENDFOR»
			
		}
	'''
	
	def GENClassEnd()'''
		}
	'''
}