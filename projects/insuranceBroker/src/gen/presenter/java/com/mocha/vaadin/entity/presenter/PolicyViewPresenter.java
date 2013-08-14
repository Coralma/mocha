package com.mocha.vaadin.entity.presenter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.fields.StringFieldWidget;
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
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
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
		// filter
		final UnitSelectionWidget companyWidget = (UnitSelectionWidget)policyView.getField("insuranceCompany");
		final UnitSelectionWidget categoryWidget = (UnitSelectionWidget)policyView.getField("category");
		final UnitSelectionWidget productWidget = (UnitSelectionWidget)policyView.getField("insuranceProduct");
		List<InsuranceCompany> companys = companyDao.loadAll();
		List<InsuranceProduct> products = productDao.loadAll();
		companyWidget.setItems(companys, InsuranceCompany.class, "companyName");
		categoryWidget.setItems("Vehicle","Accident","Life","Property","Liability","Contract");
		productWidget.setItems(products, InsuranceProduct.class, "productName");
		companyWidget.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				InsuranceCompany company = (InsuranceCompany)companyWidget.getSeletedItem();
				if(company != null) {
					List<InsuranceProduct> relatedProducts = productDao.findProductByCompany(company);
					productWidget.setItems(relatedProducts, InsuranceProduct.class, "productName");
				}
			}
		});
		
		// commission calculator
		final StringFieldWidget commissionWidget = (StringFieldWidget)policyView.getField("commission");
		final StringFieldWidget premiumWidget = (StringFieldWidget)policyView.getField("premium");
		premiumWidget.addBlurListener(new BlurListener() {
			@Override
			public void blur(BlurEvent event) {
				String premium = (String)premiumWidget.getValue();
				if(!StrUtils.isEmpty(premium) && StrUtils.isNumber(premium)) {
					InsuranceProduct insuranceProduct = (InsuranceProduct)productWidget.getSeletedItem();
					if(insuranceProduct != null && StrUtils.isNumber(insuranceProduct.getCommissionRate())) {
						BigDecimal premiumDecimal = new BigDecimal(premium);
						BigDecimal rateDecimal = new BigDecimal(insuranceProduct.getCommissionRate());
						BigDecimal commission = premiumDecimal.multiply(rateDecimal); 
						DecimalFormat df = new DecimalFormat("0.00");
						String commissionValue = df.format(commission);
						commissionWidget.setValue(commissionValue);
					}
					
				}
			}
		});
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

