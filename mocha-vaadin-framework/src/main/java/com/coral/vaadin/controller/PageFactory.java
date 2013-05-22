/**
 * 
 */
package com.coral.vaadin.controller;

import com.coral.foundation.core.impl.MochaEventBus;

/**
 * @author Administrator
 *
 */
public interface PageFactory {

	public Presenter getPresenter(String viewerName, MochaEventBus eventBus);
}
