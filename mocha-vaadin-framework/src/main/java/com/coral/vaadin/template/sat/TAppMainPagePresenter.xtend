package com.coral.vaadin.template.sat

import com.coral.foundation.constant.SystemConstant
import com.coral.foundation.md.model.App
import com.coral.foundation.md.model.helper.VAppGenHelper
import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.helper.VGenHelper

class TAppMainPagePresenter {

	String appPresenterClass
	String appMainPageClass
	String controlMenuPanel
	String functionPanel
	App app
	List<Mocha> corals

	def init(App app, List<Mocha> corals) {
  		this.app = app
  		this.appPresenterClass = VAppGenHelper::genAppMainPagePresenterClassName(app.getName);
  		this.appMainPageClass = VAppGenHelper::genAppMainPageClassName(app.getName)
  		this.controlMenuPanel = VAppGenHelper::genControllerMenuPanelClassName(app.getName);
  		this.functionPanel = VAppGenHelper::genFunctionPanelClassName(app.getName);
  		this.corals = corals
  	}
  	
  	def generate()'''
  		«GENPackageImport»
  		«GENClassHead»
  			«GENFunctionMethod»
  		«VGenHelper::classEnd»
  	'''
  	
  	def GENPackageImport()'''
		package «SystemConstant::GENERATED_PRESENTER»;
		
		import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;
		import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;
		import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;
		
		import com.coral.foundation.core.impl.MochaEventBus;
		import com.coral.vaadin.controller.Presenter;
		import com.coral.vaadin.view.template.sat.AppContentEvent;
		import com.coral.vaadin.view.template.sat.FunctionMenu;
		import com.coral.vaadin.widget.view.CommonPresenter;
		import «SystemConstant::GENERATED_PAGE».«appMainPageClass»;
		
	'''
	
	def GENClassHead()'''
		public class «appPresenterClass» extends CommonPresenter implements Presenter, ClickListener {
		
			public «appPresenterClass»() {
				this.viewer = new «appMainPageClass»();
			}
			
	'''

	def GENFunctionMethod()'''
		@Override
		public String getPresenterName() {
			return "«app.getName»";
		}

		@Override
		public void bind() {
			«appMainPageClass» page = («appMainPageClass») viewer;
			page.getControllerMenu().setControllerMenuListener(page);
			page.getFunctionPanel().getCreateContextMenu().addListener(this);
			page.getFunctionPanel().getSettingContextMenu().addListener(this);
		}
		
		@Override
		public void contextItemClick(ClickEvent event) {
			final ContextMenuItem clickedItem = event.getClickedItem();
			«appMainPageClass» oaPage = («appMainPageClass») viewer;
			oaPage.getControllerMenu().cleanMenuStyle();
			FunctionMenu functionMenu = oaPage.getFunctionPanel().getFunctionMenu(clickedItem);
			if("exit".equals(functionMenu.getName())) {
				PageChangeEvent changeEvent = new PageChangeEvent("index");
				changeEvent.setContentPresenterName("home");
				eventBus.post(changeEvent);
			} else {
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setViewName(functionMenu.getViewName());
				appContentEvent.setCustomizeClass(functionMenu.getCustomizeClass());
				eventBus.post(appContentEvent);
			}
		}
		
		/**
		 * @param eventBus the eventBus to set
		 */
		public void setEventBus(MochaEventBus eventBus) {
			this.eventBus = eventBus;
			((«appMainPageClass») viewer).setEventBus(eventBus);
		}
	'''
}