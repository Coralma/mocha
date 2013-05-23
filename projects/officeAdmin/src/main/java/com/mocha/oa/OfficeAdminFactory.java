/**
 * 
 */
package com.mocha.oa;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.PageFactory;
import com.coral.vaadin.controller.Presenter;
import com.mocha.oa.page.OfficeAdminIndexPresenter;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminFactory implements PageFactory {

	@Override
	public Presenter getPresenter(String viewerName, MochaEventBus eventBus) {
		if("OfficeAdminIndex".equals(viewerName)) {
			return new OfficeAdminIndexPresenter(eventBus);
		}
		return null;
	}
	
//	public Presenter getPresenter(String entityName, MochaEventBus eventBus) {
//		if(PresenterProperty.INDEX.equals(entityName)) {
//			return new IndexPresenter(eventBus);
//		}
//	}

}
