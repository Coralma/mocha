package com.coral.vaadin.template

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.Entity
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.utils.StrUtils
import java.util.List

class TSearchPresenter {

	Entity entity
	List<Mocha> corals
  	String searchPresenterClassName
  	String searchListenerName
  	String entityName
  	String entityVariable
  	
  	String entityDaoIntf
  	String daoIntfPackage
  	
  	String createViewClassName
  	
  	def init(Entity entity,List<Mocha> corals) {
  		this.entity = entity;
  		this.corals = corals;
  		entityName = entity.entityName;
  		entityVariable = StrUtils::lowCaseFirstLetter(entityName);
  		searchPresenterClassName = VGenHelper::genSearchPresenterClassName(entityName)
  		searchListenerName = VGenHelper::genSearchListenerName(entityName)
  		
  		entityDaoIntf= VGenHelper::genDaoIntf(entityName)
  		val currentCoral = VGenHelper::getCurrentCoral(entity,corals);
  		daoIntfPackage = currentCoral.getDaoIntfPackage;
  		
  		createViewClassName = VGenHelper::genCreateViewClassName(entityName);
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
		package «SystemConstant::TSEARCH_PRESENTER_PKG»;
		
		import java.util.Collection;
		import java.util.List;
		
		import «VGenHelper::getCurrentEntityPackage(entity,corals)+".*"»;
		import «SystemConstant::TSEARCH_LISTENER_PKG+".*"»;
		import «daoIntfPackage+".*"»;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.widget.Viewer;
		import com.coral.vaadin.widget.listener.*;
		import com.coral.vaadin.controller.PageChangeEvent;
		import com.coral.vaadin.widget.table.AbstractTable;
		import com.vaadin.ui.Component;
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.foundation.spring.bean.SpringContextUtils;
		import org.vaadin.dialogs.ConfirmDialog;
	'''
	def GENClassHead()'''
		/**
		  * «searchPresenterClassName» is a auto Generated class. Please don't modify it.
		  * @author Coral
		  */
		public class «searchPresenterClassName» extends AbstractActionListener implements Presenter, «searchListenerName» {
			
			private static final long serialVersionUID = «VGenHelper::genserialVersionUID»L;
			
	'''
	def GENBuildVeriable()'''
		private Viewer viewer;
		private MochaEventBus eventBus;
		private «entityDaoIntf» dao = SpringContextUtils.getBean(«entityDaoIntf».class);
		
	'''
	def GENConstructor()'''
«««		«val viewClassName = VGenHelper::genCreateViewClassName(entity.entityName)»
		public «searchPresenterClassName»(Viewer viewer, MochaEventBus eventBus) {
			this.viewer = viewer;
			this.eventBus = eventBus;
			viewer.setListener(this);
			viewer.build();
			clean();
		}
		
	'''
	def GENBuildMethod()'''
		public Component go() {
			return (Component)viewer;
		}
		
		public void setEditValue(Long id) {
			// ignore in search Presenter.
		}
		
		/**
		  * Query a list result of «entityName».
		  */
		public void query() {
			
		}
		
		/**
		  * Show all result of «entityName».
		  */
		public void clean() {
			List<«entityName»> valueList = dao.findAll();
			AbstractTable table = viewer.getTables().get(0);
			table.setValue(valueList);
			table.refreshRowCache();
		}
		
		/**
		  * Add a new «entityName».
		  */
		public void add() {
			eventBus.post(new PageChangeEvent("«createViewClassName»"));
		}
		
		/**
		  * Edit a exited «entityName».
		  */
		public void edit() {
			«entityName» value = («entityName») getSelectedEntity();
			if(value != null) {
				eventBus.post(new PageChangeEvent("«createViewClassName»",value.getID()));
			}
		}
		
		/**
		  * Remove a «entityName»CreateView.
		  */
		public void delete() {
			final «entityName» value = («entityName») getSelectedEntity();
			if(value != null) {
				ConfirmDialog.show(viewer.getWindow(), "请确认","您是否真的要删除这条数据?", "是的", "取消",new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							dao.remove(value.getID());
							viewer.getTables().get(0).getTable().removeItem(value);
						}
					}
				});
			}
		}
		
		public «entityName» getSelectedEntity() {
			AbstractTable table = viewer.getTables().get(0);
			Collection<Object> selections = table.getTable().getSelection();
			if(selections.size() > 0) {
				«entityName» value = («entityName»)selections.iterator().next();
				return value;
			}
			return null;
		}
	'''
	
	def GENClassEnd()'''
		}
	'''	
}