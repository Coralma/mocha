/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.LinkedHashMap;
import java.util.List;

import com.coral.foundation.utils.Message;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mocha.cooperate.widget.listener.BreadcrumbListener;
import com.mocha.cooperate.widget.listener.BreadcrumbListener.BreadcrumbEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
public class BreadCrumb extends HorizontalLayout {
	
	private String currentStep = null;
	private List<String> steps = Lists.newArrayList();
	private LinkedHashMap<String, Long> stepMaps = Maps.newLinkedHashMap();
	private BreadcrumbListener breadcrumbListener;
	private Message message;
	
	public BreadCrumb() {
		setSpacing(true);
		addStyleName("mocha-breadcrumb");
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		String home = message.getString("cooperate.file.home");
		steps.add(home);
		stepMaps.put(home, null);
		build();
	}
	
	public void build() {
		removeAllComponents();
		for(int i=0; i < steps.size(); i++) {
			if(i == 0) {
				buildStep(steps.get(i));
			} else {
				addSeperate();
				buildStep(steps.get(i));
			}
		}
	}
	
	public void addSeperate() {
		Label seperate = new Label();
		seperate.addStyleName("seperate-label");
		seperate.setWidth("8px");
		seperate.setIcon(new ThemeResource("icons/folder_seperate.png"));
		addComponent(seperate);
//		setComponentAlignment(seperate, Alignment.MIDDLE_LEFT);
	}
	
	public void buildStep(final String stepName) {
		Button stepBtn = new Button(stepName);
		stepBtn.addStyleName(BaseTheme.BUTTON_LINK);
		stepBtn.addStyleName("breadcrumb-step");
		stepBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!stepName.equals(currentStep)) {
					gotoStep(stepName);
					if(breadcrumbListener != null) {
						BreadcrumbEvent breadcrumbEvent = new BreadcrumbEvent();
						breadcrumbEvent.setStepName(stepName);
						breadcrumbEvent.setStepID(stepMaps.get(stepName));
						breadcrumbListener.stepClick(breadcrumbEvent);
					}
				}
			}
		});
//		if(stepMaps.get(stepName) == null) {
//			stepBtn.setIcon(new ThemeResource("icons/folder_home.png"));
//		}
		addComponent(stepBtn);
	}
	
	public void addStep(String stepName, Long stepID) {
		currentStep = stepName;
		steps.add(stepName);
		stepMaps.put(stepName, stepID);
		build();
	}
	
	public void gotoStep(String stepName) {
		currentStep = stepName;
		int stepIndex = steps.size() -1;
		for(int i= stepIndex; i >= 0; i--) {
			if(stepName.equals(steps.get(i))) {
				build();
				return;
			} else {
				stepMaps.remove(steps.get(i));
				steps.remove(i);
			}
		}
	}

	/**
	 * @return the currentStep
	 */
	public String getCurrentStep() {
		return currentStep;
	}

	/**
	 * @param breadcrumbListener the breadcrumbListener to set
	 */
	public void setBreadcrumbListener(BreadcrumbListener breadcrumbListener) {
		this.breadcrumbListener = breadcrumbListener;
	}
}
