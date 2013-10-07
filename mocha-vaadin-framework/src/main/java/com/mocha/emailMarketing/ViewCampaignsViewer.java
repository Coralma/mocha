package com.mocha.emailMarketing;

import java.sql.Date;
import java.util.List;

import com.coral.foundation.security.model.Campaingns;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ViewCampaignsViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout mainLayout = new VerticalLayout();
	private NativeButton updateBtn = new NativeButton("Update");
	private List<Campaingns> campaingnsList;

	public ViewCampaignsViewer(List<Campaingns> campaingnsList) {
		this.setCampaingnsList(campaingnsList);
	}

	@Override
	public void build() {
		addComponent(mainLayout);
		mainLayout.addComponent(getUpdateBtn());
		getUpdateBtn().addStyleName("Mocha-Button");

		buildCampanginsTable();

	}

	private void buildCampanginsTable() {

		Table campanginsTable = new Table();
		campanginsTable.addContainerProperty("Campangins Name", String.class, null);
		campanginsTable.addContainerProperty("Campangins Create Time", Date.class, null);
		campanginsTable.setColumnHeader("name", "Name");
		campanginsTable.setColumnHeader("createDate", "CreateDate");
		BeanItemContainer<Campaingns> beansItemContainer = new BeanItemContainer<Campaingns>(Campaingns.class);
		campanginsTable.setContainerDataSource(beansItemContainer);
		for (Campaingns cam : getCampaingnsList()) {
			beansItemContainer.addBean(cam);
		}
		mainLayout.addComponent(campanginsTable);
	}

	@Override
	public void attach() {
		build();
	}

	public NativeButton getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(NativeButton updateBtn) {
		this.updateBtn = updateBtn;
	}

	public List<Campaingns> getCampaingnsList() {
		return campaingnsList;
	}

	public void setCampaingnsList(List<Campaingns> campaingnsList) {
		this.campaingnsList = campaingnsList;
	}

}
