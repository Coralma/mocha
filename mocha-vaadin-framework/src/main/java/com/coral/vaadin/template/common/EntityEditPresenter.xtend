package com.coral.vaadin.template.common

import com.coral.foundation.md.model.View
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VAppGenHelper
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VGenHelper

class EntityEditPresenter {
	
	String entityName;
	String entityPackage;
	String viewPresenterName
	String viewClassName
	View view
	String entityDaoIntf
	String daoIntfPackage
	List<Mocha> mochas
	
	def init(View view, List<Mocha> mochas) {
		this.view = view;
		this.mochas = mochas;
		this.viewClassName = VAppGenHelper::generateClassName(view.name);
		this.viewPresenterName = VAppGenHelper::genAppMainPagePresenterClassName(view.name);
		this.entityName = view.getEntity.entityName;
		this.entityDaoIntf= VGenHelper::genDaoIntf(entityName);
		this.entityPackage = view.entity.mocha.entityPackage;
		this.daoIntfPackage = view.entity.mocha.daoIntfPackage;
	}

	def generate()'''
		«GENPackageImport»

		«GENClassHead»
		
			«GENGetMethod»
		
		«GENClassEnd»
	'''

	def GENPackageImport()'''
		package «SystemConstant::ENTITY_EDIT_PRESENTER_PKG»;
		
		import «daoIntfPackage+".*"»;
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.foundation.spring.bean.SpringContextUtils;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.view.AppCommonPresenter;
		import «SystemConstant::ENTITY_EDIT_VIEW_PKG».«viewClassName»;
		import «entityPackage».«entityName»;
		
		import com.vaadin.ui.Button;
		import com.vaadin.ui.Button.ClickEvent;
		import com.vaadin.ui.Button.ClickListener;
	'''

	def GENClassHead()'''
		public class «viewPresenterName» extends AppCommonPresenter implements Presenter {

			private «entityDaoIntf» dao = SpringContextUtils.getBean(«entityDaoIntf».class);
			
			public «viewPresenterName»(MochaEventBus eventBus) {
				this.eventBus = eventBus;
				«viewClassName» newView = new «viewClassName»();
				Object entity = this.eventBus.getContext().get("Entity");
				if(entity != null) {
					newView.setEntity(entity);
				}
				this.viewer = newView;
			}
	'''
	
	def GENGetMethod()'''
		@Override
		public String getPresenterName() {
			return "«viewClassName»";
		}
		
		@Override
		public void bind() {
			//TODO add and edit your action.
			«FOR viewAction : view.getViewActions»
			«val buttonName = viewAction.getName + "Button"»
			Button «buttonName» = viewer.getButton("«viewAction.getName»");
			«buttonName».addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					«IF "Save".equals(viewAction.getAction)»
					save();
					«ELSEIF "Back".equals(viewAction.getAction)»
					back();
					«ELSE»
					//TODO add action content.
					«ENDIF»
				}
			});
			«ENDFOR»
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
		
		public void back() {
			«val searchViewName = VAppGenHelper::getSearchViewName(view,mochas)»
			postViewer("«searchViewName»");
		}
	'''

	def GENClassEnd()'''
		}
	'''
}