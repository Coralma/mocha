package com.coral.vaadin.template.common

import com.coral.foundation.md.model.View
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VAppGenHelper
import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.helper.VGenHelper

class EntityCardSearchPresenter {

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
		import java.util.List;
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.foundation.spring.bean.SpringContextUtils;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.view.CommonPresenter;
		import «SystemConstant::ENTITY_EDIT_VIEW_PKG».«viewClassName»;
		import «entityPackage».«entityName»;
		
		import com.vaadin.ui.Button;
		import com.vaadin.ui.Button.ClickEvent;
		import com.vaadin.ui.Button.ClickListener;
	'''

	def GENClassHead()'''
		public class «viewPresenterName» extends CommonPresenter implements Presenter {

			private «entityDaoIntf» dao = SpringContextUtils.getBean(«entityDaoIntf».class);
			
			public «viewPresenterName»(MochaEventBus eventBus) {
				this.eventBus = eventBus;
				this.viewer = new «viewClassName»();
				// load all data.
				List entities = dao.findAll();
				viewer.setValue(entities);
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
		}
		
	'''

	def GENClassEnd()'''
		}
	'''	
}