package com.mocha.soicalAPI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.table.PagedTable;
import com.coral.vaadin.widget.table.PagedTableContainer;
import com.google.code.linkedinapi.client.constant.IndustryCodes;
import com.google.code.linkedinapi.schema.People;
import com.google.code.linkedinapi.schema.Person;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PeopleSearchViewer extends EntityViewPanel implements Viewer {

	private VerticalLayout mainLayout = new VerticalLayout();
	private NativeButton searchBtn = new NativeButton("Search");
	private Select select = new Select("");
	private People matchedResults;

	public PeopleSearchViewer() {

	}

	@Override
	public void attach() {
		addComponent(mainLayout);
		build();
	}

	@Override
	public void build() {
		// build Search Input Box
		mainLayout.addStyleName("peopleSearchLayout");
		getSelect().setCaption("Industry");
		getSelect().setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
		Class<IndustryCodes> LinkedIndustryCode = IndustryCodes.class;
		for (Field filed : LinkedIndustryCode.getDeclaredFields()) {
			getSelect().addItem(filed.getName());
		}
		getSelect().addStyleName("peopleSearchBox");
		mainLayout.addComponent(getSelect());
		mainLayout.addComponent(getSearchBtn());
		getSearchBtn().addStyleName("peopleSearchBoxBtn");
		getSearchBtn().addStyleName("mocha-button");
	}

	public void buildSearchResultPanle(People matchedResults) {
		if (matchedResults != null) {
			System.out.println("find return result from linkedin!");
			VerticalLayout userInfoLayout = new VerticalLayout();
			userInfoLayout.setSpacing(true);
			userInfoLayout.addStyleName("linkedinUserInfoLayout");
			mainLayout.addComponent(userInfoLayout);
			HashSet<LinkedinConnectsCard> linkedinConnectsCards = new HashSet<LinkedinConnectsCard>();
			Indexed conatiner = new IndexedContainer(linkedinConnectsCards);
			PagedTableContainer ic = new PagedTableContainer(conatiner);
			ic.addContainerProperty("LinkedinConnectsCard", LinkedinConnectsCard.class, null);
			ic.setPageLength(matchedResults.getPersonList().size());
			int i = 0;
			for (Person person : matchedResults.getPersonList()) {
				LinkedinPersonProfile personProfile = new LinkedinPersonProfile();
				// personProfile.setCompanyName(person.getPositions().getPositionList().get(0).getCompany().getName());
				personProfile.setLastName(person.getLastName());
				personProfile.setFirstName(person.getFirstName());
				LinkedinConnection linkedinCon = new LinkedinConnection();
				linkedinCon.setCompanyName(person.getPositions().getPositionList().get(0).getCompany().getName());
				linkedinCon.setLastName(person.getLastName());
				linkedinCon.setFirstName(person.getFirstName());
				LinkedinConnectsCard lCard = new LinkedinConnectsCard(linkedinCon, personProfile);
				linkedinConnectsCards.add(lCard);
				Item item = conatiner.addItem(i++);
				item.getItemProperty("LinkedinConnectsCard").setValue(lCard);
			}
			PagedTable pt = new PagedTable("Connections");
			pt.setWidth("800px");
			pt.setContainerDataSource(conatiner);
			userInfoLayout.addComponent(pt);
			pt.getItemsPerPageSelect().setVisible(false);
			userInfoLayout.addComponent(pt.createControls());
			this.requestRepaintAll();
		}
	}

	public NativeButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(NativeButton searchBtn) {
		this.searchBtn = searchBtn;
	}

	public Select getSelect() {
		return select;
	}

	public void setSelect(Select select) {
		this.select = select;
	}

	class LinkedIndustryCode implements IndustryCodes {

	}
}