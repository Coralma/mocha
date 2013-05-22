package com.coral.vaadin.template

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.helper.GLog
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import com.coral.vaadin.widget.Viewer
import java.util.List
import com.coral.foundation.md.model.Mocha

class TSearch {
	
	Entity entity
	List<Mocha> corals
  	String searchViewClassName
  	String entityName
  	String entityVariable
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		searchViewClassName = VGenHelper::genSearchViewClassName(entityName)
  	}
  	
	def generate()'''
«««		Generate package name & import.
		«GENPackageImport»
«««		Generate class name and class javadoc
		«GENClassHead»
			«GENBuildVeriable»
«««			Class Constructor
			«GENConstructor»
«««			***Build search condition section and result table***
			«GENBuildMethod»
«««			Widget Implement Method
			«GENWidgetImplMethod»
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::TSEARCH_PAGE_PKG»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import «SystemConstant::TTable_PKG+".*"»;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.Widget;
		import com.coral.vaadin.widget.field.ActionButton;
		import com.coral.vaadin.widget.listener.PresenterListener;
		import com.coral.vaadin.widget.view.AbstractView;
		import com.coral.vaadin.widget.layout.SectionLayout;
		import com.coral.vaadin.widget.table.EntityTable;
		import com.vaadin.terminal.ThemeResource;
		
	'''
	def GENClassHead()'''
		«GLog::startClass(searchViewClassName)»
		/**
		  * «searchViewClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «searchViewClassName» extends AbstractView implements Widget,Viewer {
			
			private static final long serialVersionUID = «VGenHelper::genserialVersionUID»L;
			
	'''
	def GENBuildVeriable()'''
		private PresenterListener listener;
		private «entityName» «entityVariable» = new «entityName»();
	'''
	def GENConstructor()'''
«««		«val viewClassName = VGenHelper::genCreateViewClassName(entity.entityName)»
		public «searchViewClassName»() {
			super();
		}
		
		public «searchViewClassName»(String title) {
			super(title);
		}
		
	'''
	def GENBuildMethod()'''
		public void build() {
			«val viewer = VGenHelper::getCurrentView(entity, Viewer::TSearch)»
			«val section = viewer.getSections.get(0)»
			«val sectionName = section.name»
			«val column = if (section.column==null) 3 else section.column»
			SectionLayout «sectionName» = createSection("«section.label»", «column»);
			«FOR field : section.viewFields»
				«val p = VGenHelper::getFieldProperty(entity, field.fieldName)»
				«IF p.ref != null»
					«GLog::error(p.propertyName + " is a " + p.type + " type. System can not generate it as a query condition.")»
				«ELSE»
					addWidget(«sectionName», "«p.label»", "«p.propertyName»", "«p.type»",false);
				«ENDIF»
			«ENDFOR»
«««			Search action
			«GENSearchAction(sectionName)»
«««			***Build search result table
			«GENResultTable»
«««			***Build view action
			«GENViewAction»
		}
	'''
	def GENSearchAction(String sectionName)'''
		ActionButton searchButton = addButton("查询","query",listener,new ThemeResource("icons/search.png"));
		ActionButton clearButton = addButton("清空","clean",listener,new ThemeResource("icons/refresh.png"));
		«sectionName».setButtons(searchButton, clearButton);
	'''
	def GENResultTable()'''
		«val entityTable = VGenHelper::genTableClassName(entityName)»
		«val entityTableVariable = StrUtils::lowCaseFirstLetter(entityTable)»
		«val entityTableSection = entityTableVariable + "Section"»
		SectionLayout «entityTableSection» = createSection("供应商查询结构", 1);
		addTableWidget(«entityTableSection», null , "«entityTable»" , null ,«entityTable».class, EntityTable.DEFAULT);
	'''
	
	def GENViewAction()'''
		ActionButton addButton = addButton("新增","add",listener,new ThemeResource("icons/add.png"));
		ActionButton editButton = addButton("修改","edit",listener,new ThemeResource("icons/edit.png"));
		ActionButton deleteButton = addButton("删除","delete", listener, new ThemeResource("icons/del.png"));
		setButtons(addButton,editButton,deleteButton);
		
	'''
	def GENWidgetImplMethod()'''
		public «entityName» getValue() {
			return getEntityValue(«entityVariable»,«entityName».class);
		}
		
		public void setValue(Object value) {
			this.«entityVariable» = («entityName»)value;
			setEntityValue(value);
		}
		
		public boolean validate(String type) {
			// TODO Auto-generated method stub
			return false;
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
		«GLog::endClass(searchViewClassName)»
		}
	'''
}