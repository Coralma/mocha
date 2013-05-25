package com.homepage.pages;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

public class CloseButtonAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private ModalWindow modal;
	
	CloseButtonAjaxBehavior(ModalWindow modal){
		this.modal=modal;
	}
	
	@Override
	protected void respond(AjaxRequestTarget target) {
		System.out.println("close");
		modal.close(target);
	}

}
