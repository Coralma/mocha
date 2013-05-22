package com.mocha.cooperate.page;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.widget.deprecate.DocumentEditWidget;

@Deprecated
public class CreateDocumentViewer extends CommonViewer implements Viewer {
	
	@Override
	public void attach() {
		DocumentEditWidget documentEditWidget = new DocumentEditWidget();
		this.addComponent(documentEditWidget);
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

}
