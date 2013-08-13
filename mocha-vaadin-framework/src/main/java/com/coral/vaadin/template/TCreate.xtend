package com.coral.vaadin.template

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.ViewSection
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import com.coral.vaadin.widget.Viewer
import com.coral.vaadin.widget.table.EntityTable
import java.util.List

class TCreate {

	Entity entity
	List<Mocha> corals
  	String viewClassName
  	String entityName
  	String entityVariable

  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		viewClassName = VGenHelper::genCreateViewClassName(entityName)
  	}
  	
	def generateCreateView()'''
«««		Generate package name & import.
		«GENPackageImport»
«««		Generate class name and class javadoc
		«GENClassHead»
			«GENBuildVeriable»
«««			Class Constructor
			«GENConstructor»
«««			***Build widget page***
			«GENBuildMethod»
«««			Widget Implement Method
			«GENWidgetImplMethod»
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::TCREATE_PAGE_PKG»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import «SystemConstant::TTable_PKG+".*"»;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.Widget;
		import com.coral.vaadin.widget.view.AbstractEntityViewer;
		import com.coral.vaadin.widget.view.WidgetParameter;
		import com.coral.vaadin.widget.layout.SectionLayout;
		import com.coral.vaadin.widget.field.ActionButton;
		import com.coral.vaadin.widget.listener.PresenterListener;
		import com.coral.vaadin.widget.table.EntityTable;
		import com.vaadin.terminal.ThemeResource;
		
	'''
	def GENClassHead()'''
		/**
		  * «viewClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «viewClassName» extends AbstractEntityViewer implements Viewer {
			
			private static final long serialVersionUID = «VGenHelper::genserialVersionUID»L;
			
	'''
	def GENBuildVeriable()'''
		private PresenterListener listener;
		private «entityName» «entityVariable» = new «entityName»();
		
	'''
	def GENConstructor()'''
		public «viewClassName»(ViewContext viewContext) {
			super(viewContext);
		}
		
	'''
	def GENBuildMethod()'''
		public void build() {
			Entity entity = ModelCenter.getEntity(«entityName»);
			ViewContext viewContext = new ViewContext(entity, null);
			«val viewer = VGenHelper::getCurrentView(entity, Viewer::TCreate)»
			«FOR section : viewer.getSections»
				«val sectionName = section.name»
				«GENSection("null",section)»
			«ENDFOR»
			
			«IF viewer.root»
				«GENViewAction»
			«ENDIF»
		}
	'''
	def GENSection(String parentSection, ViewSection section)'''
		«val sectionName = section.name»
		«val column = if (section.column==null) 3 else section.column»
		SectionLayout «sectionName» = buildSection(«section»);
		«IF parentSection.equals("null")»
			«parentSection».addComponent(«section»);
		«ENDIF»
		
		«IF section.viewFields != null»
			«FOR field : section.viewFields»
«««				«val viewProperty = field.getViewProperties»

			«ENDFOR»
		«ENDIF»
		«IF section.viewSections != null»
			«FOR subSection :  section.viewSections»
				«GENSection(sectionName,subSection)»
			«ENDFOR»
		«ENDIF»
		«IF section.viewer != null»
			addViewer(«sectionName», "«section.viewer»", "«section.data»");
		«ENDIF»
		
		«FOR action : section.viewActions»
			«val actionName = StrUtils::lowCaseFirstLetter(StrUtils::cleanString(action.getName))»
			ActionButton «actionName»Button = addButton("«action.label»","«actionName»", listener, null);
			«sectionName».addButton(«actionName»Button);
		«ENDFOR»
	'''
	
	def GENViewAction()'''
		ActionButton addButton = addButton("Save","save", listener, new ThemeResource("icons/save.png"));
		ActionButton backButton = addButton("Back","back", listener, new ThemeResource("icons/back.png"));
		setButtons(addButton,backButton);
	'''
	def GENWidgetImplMethod()'''
		public Object getValue() {
			return getEntityValue(«entityVariable»,«entityName».class);
		}
		
		public void setValue(Object value) {
			this.«entityVariable» = («entityName»)value;
			setEntityValue(value);
		}
		
		public boolean validate(String type) {
			// TODO Auto-generated method stub
			return true;
		}
		
		public String getViewerTitle() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public void setListener(PresenterListener listener) {
			this.listener = listener;
		}
		
	'''
	def GENClassEnd()'''
		}
	'''
	
	
	
	def check(Object value) {
		if(StrUtils::isEmpty(value)) {
			return "null"
		} else {
			return "\"" + value + "\""
		}
	}
	
	def checkTableStyle(String style) {
		if(style != null && style.equals("inline")) {
			return EntityTable::INLINE
		} else if(style != null && style.equals("default")) {
			return EntityTable::DEFAULT
		} else {
			return EntityTable::POPUP
		}
	}
}