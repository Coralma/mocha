/**
 * 
 */
package com.mocha.cooperate.help;

import java.util.List;

import org.vaadin.jouni.animator.AnimatorProxy;
import org.vaadin.jouni.animator.client.ui.VAnimatorProxy.AnimType;

import com.coral.vaadin.widget.WidgetFactory;
import com.google.common.collect.Lists;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 *
 */
public class GettingStartedWindow extends Window implements ClickListener {

	List<IGuidePanel> guidePanels = Lists.newArrayList();
	int index = 0;
	VerticalLayout contentLayout = new VerticalLayout();
	private AnimatorProxy proxy = new AnimatorProxy();
	
	public GettingStartedWindow() {
		this.setCaption("Getting Started");
		this.center();
		this.addStyleName("mocha-app");
		this.setWidth("860px");
		this.setClosable(true);
		this.setResizeLazy(true);
		this.setResizable(false);
		this.setModal(true);
		this.addStyleName(Reindeer.WINDOW_LIGHT);
	}
	
	@Override
	public void attach() {
		guidePanels.add(new HomePageGuide());
		guidePanels.add(new ForumGuide());
		guidePanels.add(new TodoGuide());
		guidePanels.add(new FileGuide());
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		HorizontalLayout gsLayout = new HorizontalLayout();
		gsLayout.addStyleName("gs-content");
		Button leftBtn = WidgetFactory.createIconButton(new ThemeResource("helps/gs-left.png"), "left", this);
		gsLayout.addComponent(leftBtn);
		gsLayout.setComponentAlignment(leftBtn, Alignment.MIDDLE_RIGHT);
		
		buildContentLayout(0);
		contentLayout.setHeight("500px");
		gsLayout.addComponent(contentLayout);
		
		Button rightBtn = WidgetFactory.createIconButton(new ThemeResource("helps/gs-right.png"), "right", this);
		gsLayout.addComponent(rightBtn);
		gsLayout.setComponentAlignment(rightBtn, Alignment.MIDDLE_LEFT);
		
		layout.addComponent(gsLayout);
		this.setContent(layout);
	}
	
	public void buildContentLayout(int i) {
		contentLayout.removeAllComponents();
		contentLayout.addComponent(proxy);
		Component guidePanel = guidePanels.get(i);
		contentLayout.addComponent(guidePanel);
		proxy.animate(guidePanel, AnimType.FADE_IN).setDuration(500).setDelay(500);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String data = (String) event.getButton().getData();
		if("left".equals(data)) {
			if(index > 0) {
				--index;
			} else {
				index = guidePanels.size() - 1;
			}
		} else if("right".equals(data)) {
			if(index < (guidePanels.size() - 1)) {
				++index;
			} else {
				index = 0;
			}
		}
		buildContentLayout(index);
	}

	
}
