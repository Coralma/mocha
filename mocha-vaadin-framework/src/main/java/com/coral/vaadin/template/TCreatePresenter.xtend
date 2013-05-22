package com.coral.vaadin.template

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.helper.GLog
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import java.util.List
import com.coral.foundation.md.model.Mocha

class TCreatePresenter {

	Entity entity
	List<Mocha> corals
  	String createPresenterClassName
  	String entityName
  	String entityVariable
  	String entityDaoIntf
  	String daoIntfPackage
  	
  	String searchViewClassName
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		createPresenterClassName = VGenHelper::genCreatePresenterClassName(entityName)
//  		createListenerName = VGenHelper::genCreateListenerName(entityName)
  		entityDaoIntf= VGenHelper::genDaoIntf(entityName)
  		val currentCoral = VGenHelper::getCurrentCoral(entity,corals);
  		daoIntfPackage = currentCoral.getDaoIntfPackage;
  		
  		searchViewClassName = VGenHelper::genSearchViewClassName(entityName);
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
«««		Ending class generation
		«GENClassEnd»
	'''
	def GENPackageImport()'''
		package «SystemConstant::TCREATE_PRESENTER_PKG»;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import «daoIntfPackage+".*"»;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.controller.PageChangeEvent;
		import com.vaadin.ui.Component;
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.view.*;
		import com.coral.vaadin.widget.view.builder.ViewContext;
		import com.coral.foundation.spring.bean.SpringContextUtils;
		import com.vaadin.ui.Button.ClickEvent;
		import com.vaadin.ui.Button.ClickListener;
		import com.coral.vaadin.widget.field.ActionButton;
		
	'''
	def GENClassHead()'''
		«GLog::startClass(createPresenterClassName)»
		/**
		  * «createPresenterClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «createPresenterClassName» extends CommonPresenter implements Presenter {
			
	'''
	def GENBuildVeriable()'''
«««		private EntityViewer viewer;
		private MochaEventBus eventBus;
		private ViewContext viewContext;
		private «entityDaoIntf» dao = SpringContextUtils.getBean(«entityDaoIntf».class);
		
	'''
	def GENConstructor()'''
		public «createPresenterClassName»(ViewContext viewContext) {
			this(viewContext, null);
		}
		public «createPresenterClassName»(ViewContext viewContext, MochaEventBus eventBus) {
			this.eventBus = eventBus;
			this.viewContext = viewContext;
			this.viewer = new EntityViewer(viewContext);
		}
		
	'''
	def GENBuildMethod()'''
		@Override
		public void setEditValue(Long id) {
			if(id != null) {
				«entityName» value = dao.findById(id);
				if(value != null) {
					viewer.setValue((«entityName»)value);
				}
			}
		}
		
«««		public Viewer go() {
«««			viewer.build();
«««			bind();
«««			return viewer;
«««		}
«««		
«««		public Viewer getViewer() {
«««			return viewer;
«««		}
		
		public String getPresenterName() {
			return "«entityName»";
		}
		
		@Override
		public ViewContext getViewContext() {
			return viewContext;
		}

		public void bind() {
			//TODO add and edit your action.
			ActionButton saveBtn = viewer.getButton("Save");
			if(saveBtn != null) {
				saveBtn.addListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						save();
					}
				});
			}
		}

		/**
		  * Save value of «entityName»CreateView.
		  */
		public void save() {
			«entityName» value = («entityName»)viewer.getValue();
			if(value != null) {
				dao.persist(value);
				back();
			}
		}
		
		/**
		  * Back to «searchViewClassName».
		  */
		public void back() {
			if(eventBus != null) {
				eventBus.post(new PageChangeEvent("CargoPolicySearchView"));
			}
		}
	'''
	
	def GENClassEnd()'''
		«GLog::endClass(createPresenterClassName)»
		}
	'''	
}