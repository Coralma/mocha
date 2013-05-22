/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.common.eventbus.EventBus;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Administrator
 *
 */
@Deprecated
public class CreateDocumentPresenter extends CommonPresenter implements Presenter {

	private EventBus eventBus;
	
	public CreateDocumentPresenter(EventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CreateDocumentViewer();
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.DOCUMENT;
	}

	@Override
	public void bind() {
		
	}

}
