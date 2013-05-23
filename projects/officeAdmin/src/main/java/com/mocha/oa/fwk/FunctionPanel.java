/**
 * 
 */
package com.mocha.oa.fwk;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.SearchTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 *
 */
public class FunctionPanel extends VerticalLayout {

	public VerticalLayout headLayout = new VerticalLayout();
	public VerticalLayout contentLayout = new VerticalLayout();
	
	public FunctionPanel() {
		this.setWidth("766px");
//		this.setHeight("100%");
		this.addStyleName("app-function-main");
	}
	
	public void attach() {
		FunctionControllerPanel controllerPanel = new FunctionControllerPanel();
		headLayout.addComponent(controllerPanel);
		addComponent(headLayout);
		setExpandRatio(headLayout, 1);
		contentLayout.setMargin(true);
		contentLayout.setSizeFull();
		addComponent(contentLayout);
		setExpandRatio(contentLayout, 100);
	}
	
	public void setContent(Viewer viewer) {
		contentLayout.removeAllComponents();
		contentLayout.addComponent(viewer);
	}
	
	public void changeToEntityEditTitlePanel(String title) {
		headLayout.removeAllComponents();
		EntityEditTitlePanel titlePanel = new EntityEditTitlePanel(title);
		headLayout.addComponent(titlePanel);
	}
	
	public void changeToFunctionControllerPanel() {
		headLayout.removeAllComponents();
		FunctionControllerPanel controllerPanel = new FunctionControllerPanel();
		headLayout.addComponent(controllerPanel);
	}

	public class AbstractHeadPanel extends HorizontalLayout {
		
		public HorizontalLayout getToolLayout() {
			HorizontalLayout toolLayout = new HorizontalLayout();
			toolLayout.addStyleName("tools-head");
			toolLayout.setSpacing(true);
//			Button settingButton = new Button("Setting");
			Button toolButton = new Button("Tools");
//			toolLayout.addComponent(settingButton);
			toolLayout.addComponent(toolButton);
			return toolLayout;
		}
	}
	public class FunctionControllerPanel extends AbstractHeadPanel {
		
		public FunctionControllerPanel() {
			this.addStyleName("controller-panel");
			this.setHeight("40px");
			this.setWidth("100%");
		}
		
		public void attach() {
			HorizontalLayout controlLayout = new HorizontalLayout();
			controlLayout.addStyleName("controller-head");
			controlLayout.setSpacing(true);
			Button createButton = new Button("Create");
			createButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					changeToEntityEditTitlePanel("Create Entity");
				}
			});
			controlLayout.addComponent(createButton);

			SearchTextField searchField = new SearchTextField();
			controlLayout.addComponent(searchField);
			this.addComponent(controlLayout);
			this.setComponentAlignment(controlLayout, Alignment.MIDDLE_LEFT);
			
			HorizontalLayout toolLayout = getToolLayout();
			this.addComponent(toolLayout);
			this.setComponentAlignment(toolLayout, Alignment.MIDDLE_RIGHT);
		}
	}
	
	public class EntityEditTitlePanel extends AbstractHeadPanel {
		private String title;
		public EntityEditTitlePanel(String title) {
			this.title = title;
			this.addStyleName("controller-panel");
			this.setHeight("40px");
			this.setWidth("100%");
		}
		
		public void attach() {
			HorizontalLayout controlLayout = new HorizontalLayout();
			controlLayout.addStyleName("controller-head");
			Button backButton = new Button("Back");
			backButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					changeToFunctionControllerPanel();
				}
			});
			controlLayout.addComponent(backButton);
			this.addComponent(controlLayout);
			this.setComponentAlignment(controlLayout, Alignment.MIDDLE_LEFT);
			this.setExpandRatio(controlLayout, 1.0f);
			
			Label titleLabel = new Label(title);
			titleLabel.addStyleName("entity-edit-title");
			HorizontalLayout titleLayout = new HorizontalLayout();
			titleLayout.addComponent(titleLabel);
			this.addComponent(titleLayout);
			this.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);
			this.setExpandRatio(titleLayout, 100.0f);
			
			HorizontalLayout toolLayout = getToolLayout();
			this.addComponent(toolLayout);
			this.setComponentAlignment(toolLayout, Alignment.MIDDLE_RIGHT);
		}
	}
}