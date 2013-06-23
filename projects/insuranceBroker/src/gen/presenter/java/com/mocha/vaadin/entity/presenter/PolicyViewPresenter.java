package com.mocha.vaadin.entity.presenter;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.CodeTable;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.CodeTableUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.fields.UnitSelectionWidget;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.ib.dao.InsuranceCompanyDao;
import com.mocha.ib.dao.InsuranceProductDao;
import com.mocha.ib.dao.PolicyDao;
import com.mocha.ib.model.InsuranceCompany;
import com.mocha.ib.model.InsuranceProduct;
import com.mocha.ib.model.Policy;
import com.mocha.vaadin.entity.view.PolicyView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PolicyViewPresenter extends AppCommonPresenter implements Presenter {

	private PolicyDao dao = SpringContextUtils.getBean(PolicyDao.class);
	private InsuranceCompanyDao companyDao = SpringContextUtils.getBean(InsuranceCompanyDao.class);
	private InsuranceProductDao productDao = SpringContextUtils.getBean(InsuranceProductDao.class);
	
	public PolicyViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PolicyView newView = new PolicyView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "PolicyView";
	}
	
	@Override
	public void bind() {
		PolicyView policyView = (PolicyView) viewer;
		initViewer(policyView);
		//TODO add and edit your action.
		Button saveButton = viewer.getButton("save");
		saveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		Button backButton = viewer.getButton("back");
		backButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				back();
			}
		});
	}
	
	public void initViewer(PolicyView policyView) {
		UnitSelectionWidget companyWidget = (UnitSelectionWidget)policyView.getField("insuranceCompany");
		final UnitSelectionWidget categoryWidget = (UnitSelectionWidget)policyView.getField("category");
		final UnitSelectionWidget productWidget = (UnitSelectionWidget)policyView.getField("insuranceProduct");
		List<InsuranceCompany> companys = companyDao.findAll();
		List<InsuranceProduct> products = productDao.findAll();
		companyWidget.setItems(companys, InsuranceCompany.class, "companyName");
		categoryWidget.setItems("Vehicle","Accident","Life","Property","Liability","Contract");
		productWidget.setItems(products, InsuranceProduct.class, "productName");
	}
	
	/**
	  * Save value of PolicyCreateView.
	  */
	public void save() {
		Policy value = (Policy)viewer.getValue();
		value.getCustomer().getPolicy().add(value);
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("PolicySearch");
	}

}

