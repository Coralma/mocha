/**
 * 
 */
package com.coral.vaadin.widget.component;

import java.util.List;

import com.coral.foundation.utils.Message;
import com.google.common.collect.Lists;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

/**
 * @author Coral
 *
 */
public class Toolbar extends HorizontalLayout {
	
	public static String LINK = "link";
	public static String BUTTON = "button";
	public Message message;
	public List<ToolbarItem> leftLinks = Lists.newArrayList();
	public List<ToolbarItem> rightLinks = Lists.newArrayList(); 
	
	public Toolbar(Message message) {
		this.message = message;
	}
	
	public void attach() {
		HorizontalLayout topicHeadLeftLinks = new HorizontalLayout();
		topicHeadLeftLinks.setSpacing(true);
		
		for(int i=0; i < leftLinks.size(); i++) {
			ToolbarItem leftLink = leftLinks.get(i);
			Component topicBtn = createToolbarButton(leftLink);
			topicHeadLeftLinks.addComponent(topicBtn);
			if(i < (leftLinks.size() - 1)) {
				topicHeadLeftLinks.addComponent(space());
			}
		}
		this.addComponent(topicHeadLeftLinks);
		this.setComponentAlignment(topicHeadLeftLinks, Alignment.MIDDLE_LEFT);
		// right button
		HorizontalLayout topicHeadRightLinks = new HorizontalLayout();
		topicHeadRightLinks.setSpacing(true);
		for(ToolbarItem rightLink : rightLinks) {
			Component newPostButton = createToolbarButton(rightLink);
			topicHeadRightLinks.addComponent(newPostButton);
			topicHeadRightLinks.setComponentAlignment(newPostButton,Alignment.MIDDLE_LEFT);
		}
		this.addComponent(topicHeadRightLinks);
		this.setComponentAlignment(topicHeadRightLinks, Alignment.MIDDLE_RIGHT);
	}
	
	private Component createToolbarButton(ToolbarItem item) {
		Button toolbarBtn = null;
		if(Toolbar.LINK.equals(item.getStyle())) {
			toolbarBtn = new Button(message.getString(item.getLinkName()));
			toolbarBtn.addStyleName(BaseTheme.BUTTON_LINK);
			toolbarBtn.setData(item.getData());
			toolbarBtn.addListener(item.getClickListener());
		} else if(Toolbar.BUTTON.equals(item.getStyle())) {
			toolbarBtn = new NativeButton(message.getString(item.getLinkName()));
			toolbarBtn.addStyleName("mocha-button");
			toolbarBtn.setData(item.getData());
			toolbarBtn.addListener(item.getClickListener());
		}
		return toolbarBtn;
	}
	
	public void addLeftLink(String linkName, String data, ClickListener clickListener) {
		addLeftLink(linkName,data,null,Toolbar.LINK,clickListener);
	}
	
	public void addLeftLink(String linkName, String data, String style, ClickListener clickListener) {
		addLeftLink(linkName, data, null, style, clickListener);
	}
	
	public void addLeftLink(String linkName, String data, Object entity, String style, ClickListener clickListener) {
		leftLinks.add(createToolbarItem(linkName, data, entity, style, clickListener));
	}
	
	public void addRightLink(String linkName,String data, ClickListener clickListener) {
		addRightLink(linkName, data, null, Toolbar.LINK, clickListener);
	}
	
	public void addRightLink(String linkName,String data, String style, ClickListener clickListener) {
		addRightLink(linkName, data, null, style, clickListener);
	}
	
	public void addRightLink(String linkName,String data, Object entity, String style, ClickListener clickListener) {
		rightLinks.add(createToolbarItem(linkName, data, entity, style, clickListener));
	}
	
	public ToolbarItem createToolbarItem(String linkName, String data, Object entity, String style, ClickListener clickListener) {
		ToolbarItem item = new ToolbarItem();
		item.setLinkName(linkName);
		item.setData(data);
		item.setStyle(style);
		item.setEntity(entity);
		item.setClickListener(clickListener);
		return item;
	}
	
	public Label space() {
		Label space = new Label("|");
		space.addStyleName("c-seperate-space");
		return space;
	}

	public class ToolbarItem {
		private String linkName;
		private String data;
		private String style = Toolbar.LINK;
		private Object entity;
		private ClickListener clickListener;
		/**
		 * @return the linkName
		 */
		public String getLinkName() {
			return linkName;
		}
		/**
		 * @param linkName the linkName to set
		 */
		public void setLinkName(String linkName) {
			this.linkName = linkName;
		}
		/**
		 * @return the clickListener
		 */
		public ClickListener getClickListener() {
			return clickListener;
		}
		/**
		 * @param clickListener the clickListener to set
		 */
		public void setClickListener(ClickListener clickListener) {
			this.clickListener = clickListener;
		}
		/**
		 * @return the data
		 */
		public String getData() {
			return data;
		}
		/**
		 * @param data the data to set
		 */
		public void setData(String data) {
			this.data = data;
		}
		/**
		 * @return the entity
		 */
		public Object getEntity() {
			return entity;
		}
		/**
		 * @param entity the entity to set
		 */
		public void setEntity(Object entity) {
			this.entity = entity;
		}
		/**
		 * @return the style
		 */
		public String getStyle() {
			return style;
		}
		/**
		 * @param style the style to set
		 */
		public void setStyle(String style) {
			this.style = style;
		}
		
	}
}
