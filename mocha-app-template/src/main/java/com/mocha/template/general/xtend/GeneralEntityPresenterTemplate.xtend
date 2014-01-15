package com.mocha.template.general.xtend

import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.View
import com.coral.foundation.md.model.helper.VAppGenHelper
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.constant.SystemConstant
import java.util.List

class GeneralEntityPresenterTemplate {
	
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
	
	def private String GENPackageImport()'''
		package «SystemConstant::ENTITY_EDIT_PRESENTER_PKG»;
		
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.view.CommonPresenter;
		
		import «SystemConstant::ENTITY_EDIT_VIEW_PKG».«viewClassName»;
		import «entityPackage».«entityName»;
	'''
	
	def private String GENClassHead()'''
		public class «viewPresenterName» extends CommonPresenter implements Presenter {

			public «viewPresenterName»(MochaEventBus eventBus) {
				this.eventBus = eventBus;
				«viewClassName» newView = new «viewClassName»();
				this.viewer = newView;
			}
	'''
	
	def private String GENGetMethod() '''
	'''
	
	def private String GENClassEnd()'''
		}
	'''
}