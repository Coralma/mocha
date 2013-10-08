package com.mocha.businessSchool;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.vaadin.Application;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class BusinessSchoolHomeViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout mainLayout = new VerticalLayout();
	private BusinessProgramModuel profileBussiness;

	static int i;

	public BusinessSchoolHomeViewer() {
		i = 1;
	}

	@Override
	public void attach() {
		build();
	}

	@Override
	public void build() {
		addComponent(mainLayout);
		buildBusinessProgramModuel();
	}

	private void buildBusinessProgramModuel() {
		profileBussiness = new BusinessProgramModuel("Profile Solutions", "Find More characteristic from Your Linkedin Connections");
		mainLayout.addComponent(profileBussiness);

		mainLayout
				.addComponent(new BusinessProgramModuel(
						"Computers & Internet Marketplace",
						"Our innovative full-time and part-time MBA programs are aimed at postgraduates from a wide range of fields and combine the best of online and onsite learning."));

		mainLayout.addComponent(new BusinessProgramModuel("Masters in Finance",
				"Designed for students with varying levels of experience, equipping them with leading-edge knowledge and skills in key areas of finance."));

		mainLayout.addComponent(new BusinessProgramModuel("Specialized Programs",
				"Specialized programs are designed for professionals with a career focus on a specific sector or area in which to deepen their knowledge."));

		mainLayout.addComponent(new BusinessProgramModuel("Doctoral Programs",
				"IE Business School offers two types of doctoral degrees: Doctor in Business Administration (DBA) & PhD in Management. "));

	}

	BusinessProgramModuel getProfileBussiness() {
		return profileBussiness;
	}

	public void setProfileBussiness(BusinessProgramModuel profileBussiness) {
		this.profileBussiness = profileBussiness;
	}

	public class BusinessProgramModuel extends Panel implements LayoutClickListener {

		private String businessName;
		private String businessValue;
		private NativeButton startBtn = new NativeButton("Run this App Now!");

		public BusinessProgramModuel(String businessName, String businessValue) {
			this.businessName = businessName;
			this.businessValue = businessValue;
			buildLayout();
		}

		private void buildLayout() {
			i++;
			VerticalLayout businessLayout = new VerticalLayout();
			businessLayout.addStyleName("program-module");
			mainLayout.addComponent(businessLayout);
			Label name = new Label(businessName);

			name.addStyleName("program-module-name");
			businessLayout.addComponent(name);

			if (i % 2 == 0) {
				name.addStyleName("blue");
			}
			if (i % 3 == 0) {
				name.addStyleName("red");
			}
			if (i % 4 == 0) {
				name.addStyleName("green");
			}
			if (i % 5 == 0) {
				name.addStyleName("grey");
			}
			if (i % 6 == 0) {
				name.addStyleName("blue");
			}
			if (i % 7 == 0) {
				name.addStyleName("black");
			}

			Label descritpion = new Label(businessValue);
			descritpion.addStyleName("program-module-description");
			businessLayout.addComponent(descritpion);

			getStartBtn().addStyleName("businessProgramModuel-start-button");
			getStartBtn().addStyleName("mocha-button");
			businessLayout.addComponent(getStartBtn());
			businessLayout.setComponentAlignment(getStartBtn(), Alignment.BOTTOM_CENTER);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {

		}

		public NativeButton getStartBtn() {
			return startBtn;
		}

		public void setStartBtn(NativeButton startBtn) {
			this.startBtn = startBtn;
		}
	}

}
