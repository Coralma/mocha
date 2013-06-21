/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.PageChangeEvent;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Shotcut;
import com.mocha.cooperate.model.ShotcutItem;
import com.mocha.cooperate.page.data.ExampleData;
import com.mocha.cooperate.page.event.HomePageEvent.ChangeHeadMenuStyleEvent;
import com.mocha.cooperate.page.event.HomePageEvent.ChangeShotCutEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public class ShotcutPanel extends VerticalLayout {
	
	private MochaEventBus eventBus;
	private List<ShotcutSection> shotcutPanelList = Lists.newArrayList();
	private Message message;
	private List<Shotcut> shotcuts;
	
	public ShotcutPanel(MochaEventBus eventBus, List<Shotcut> shotcuts) {
		this.eventBus = eventBus; 
		this.shotcuts = shotcuts; 
		eventBus.register(this);
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
//		List<Shotcut> shotcuts = ExampleData.getShotcut();
		for(Shotcut shotcut : shotcuts) {
			ShotcutSection shotcutPanel = new ShotcutSection(shotcut, eventBus);
			shotcutPanelList.add(shotcutPanel);
			this.addComponent(shotcutPanel);
		}
	}
	
	@Subscribe
	public void changeShotcutStyle(ChangeShotCutEvent event) {
		for(ShotcutSection section : shotcutPanelList) {
			for(NativeButton shotcutBtn :section.getShotcuts()) {
				ShotcutItem shotcut = (ShotcutItem)shotcutBtn.getData();
				if(event.getAction().equals(shotcut.getAction())) {
					shotcutBtn.addStyleName("v-nativebutton-selected");
				} else {
					shotcutBtn.removeStyleName("v-nativebutton-selected");
				}
			}
		}
	}

	public class ShotcutSection extends CustomComponent implements Button.ClickListener {
		private static final long serialVersionUID = 5763483966017706934L;
		private Shotcut shotcut;
		private MochaEventBus eventBus;
		private NativeButton notifyButton;
		private List<NativeButton> shotcuts = Lists.newArrayList();
		
		public ShotcutSection(Shotcut shotcut, MochaEventBus eventBus) {
			this.eventBus = eventBus;
			this.shotcut = shotcut;
		}
	
		@Override
	    public void attach() {
			super.attach();
			Panel panel = new Panel();
			panel.setSizeFull();
			panel.addStyleName(Reindeer.PANEL_LIGHT);
			panel.addStyleName("mocha-shotcut");
			VerticalLayout noMarginLayout = new VerticalLayout();
			noMarginLayout.setSizeFull();
			panel.setContent(noMarginLayout);
			// panel title.
			panel.setCaption(message.getString(shotcut.getTitle()));
			
			for(ShotcutItem item : shotcut.getShotcutItems()) {
				NativeButton itemButton = createNativeButton(item);
				panel.addComponent(itemButton);
			}
			setCompositionRoot(panel);
		}
		
		public NativeButton createNativeButton(ShotcutItem item) {
			NativeButton itemButton = new NativeButton(message.getString(item.getLabel()));
			itemButton.setWidth("100%");
			String icon = item.getIcon();
			if(icon != null) {
				itemButton.setIcon(new ThemeResource(icon));
			}
			itemButton.setData(item);
			itemButton.addListener(this);
			if(PresenterProperty.HOME.equals(item.getAction())) {
				itemButton.addStyleName("v-nativebutton-selected");
			}
			if(PresenterProperty.NOTIFICATION.equals(item.getAction())) {
				notifyButton = itemButton;
			}
			shotcuts.add(itemButton);
			return itemButton;
		}
		
		@Override
		public void buttonClick(ClickEvent event) {
			Button btn = event.getButton();
			ShotcutItem item = (ShotcutItem) btn.getData();
			String action = item.getAction();
			if(SystemProperty.PAGE_TYPE.equals(item.getType())) {
				PageChangeEvent pageChangeEvent = new PageChangeEvent(action);
				eventBus.post(pageChangeEvent);
			} else {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(action);
				eventBus.post(changeEvent);
			}
			// change the head menu to home page.
			changeToHomeMenuStyle();
			// change shotcut menu style.
			changeShotCutStyle(action);
		}
		
		public void changeToHomeMenuStyle() {
			ChangeHeadMenuStyleEvent event = new ChangeHeadMenuStyleEvent();
			event.setSelectedMenu(PresenterProperty.HOME);
			eventBus.post(event);
		}
		
		public void changeShotCutStyle(String action) {
			if(action == null) {
				return;
			}
			ChangeShotCutEvent event = new ChangeShotCutEvent();
			event.setAction(action);
			eventBus.post(event);
		}

		/**
		 * @return the notifyButton
		 */
		public NativeButton getNotifyButton() {
			return notifyButton;
		}

		/**
		 * @return the shotcuts
		 */
		public List<NativeButton> getShotcuts() {
			return shotcuts;
		}
	}
}