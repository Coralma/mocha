/**
 * 
 */
package com.mocha.cooperate.page.test;

import com.bsb.common.vaadin.embed.support.EmbedVaadin;
import com.google.common.eventbus.EventBus;
import com.mocha.cooperate.page.FilePresenter;
import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Coral.Ma
 * 
 */
public class FileViewerTestApplication extends Application {

	private static final long serialVersionUID = -8900581273653297334L;

	public static void main(String[] args) {
		EmbedVaadin.forApplication(FileViewerTestApplication.class)
				.openBrowserAt("fileViewer/?restartApplication")
				.withContextRootDirectory("src/main/webapp").start();
	}

	@Override
	public void init() {
		setTheme("mocha");
		Window window = new Window();
        setMainWindow(window);
        window.setImmediate(true);
        
//        EventBus eventBus = new EventBus();
//        FilePresenter filePresenter = new FilePresenter(eventBus);
        Label label=new Label("Hello");
        VerticalLayout layout=new VerticalLayout();
        layout.addComponent(label);
        window.setContent(layout);
	}

}
