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
		import com.coral.foundation.model.BaseEntity;
		import com.coral.foundation.jpa.search.SearchFilter;
		import com.coral.foundation.jpa.search.SearchFilterBuilder;
		import com.coral.foundation.jpa.search.SearchFilterFactory;
		import com.coral.foundation.spring.bean.SpringContextUtils;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.foundation.utils.StrUtils;
		import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
		import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
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
			«val viewVariable = VAppGenHelper::asVariable(viewClassName)»
			final «viewClassName» «viewVariable» = («viewClassName») viewer;
			«val editViewName = VAppGenHelper::getEditViewName(view,mochas)»
			«IF editViewName != null»
				«viewVariable».getConditionPanel().getCreateBtn().addListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						postViewer("«editViewName»");
					}
				});
				«viewVariable».getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
					@Override
					public void search(String condition) {
						List<«entityName»> customers = dao.fuzzySearch(buildFuzzySearch(condition));
						«viewVariable».setValue(customers);
						«viewVariable».buildSearchCardPanel();
					}
				});
				«viewVariable».setListener(new SearchListener() {
					@Override
					public void handleAction(String name, String action, Object entity) {
						if("Edit".equals(action)) {
							postViewer("«editViewName»",entity);
						} else if("Delete".equals(action)) {
							remove(entity);
							postViewer("«viewClassName»");
						}
					}
					
					@Override
					public String getSpecialIcon(Object value) {
						// TODO Auto-generated method stub
						return null;
					}
				});
			«ENDIF»
		}
		
		public void remove(Object entity) {
			if(entity != null) {
				dao.remove(((BaseEntity)entity).getID());
			}
		}
		
		public SearchFilterBuilder buildFuzzySearch(String condition) {
			if(StrUtils.isEmpty(condition)) {
				return null;
			}
			SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(«entityName».class);
			«FOR section : view.getSections»
				«IF "SearchCondition".equals(section.getTemplate)»
					«FOR field : section.getViewFields»
						filterBuilder.getSearchFilters().add(SearchFilter.like("«field.getFieldName»", condition));
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
			return filterBuilder;
		}
		
	'''

	def GENClassEnd()'''
		}
	'''	
}