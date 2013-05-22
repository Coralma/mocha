/**
 * 
 */
package com.coral.vaadin.widget.component;

import java.util.List;

import com.google.common.collect.Lists;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class ToolbarAdvance extends HorizontalLayout {

	public boolean needLeftSeperate = true;
	public boolean needRightSeperate = true;
	public List<Component> leftComponents = Lists.newArrayList();
	public List<Component> rightComponents = Lists.newArrayList();
	public String toolbarWidth = "768px";
	
	public void attach() {
		this.addStyleName("toolbar");
		this.setWidth(toolbarWidth);
		// left panel
		HorizontalLayout leftPanel = new HorizontalLayout();
		leftPanel.setSpacing(true);
		
		for(int i=0; i < leftComponents.size(); i++) {
			Component component = leftComponents.get(i);
			leftPanel.addComponent(component);
			if(component instanceof Button) {
				leftPanel.setComponentAlignment(component, Alignment.MIDDLE_LEFT);
			}
			if(needLeftSeperate && i < (leftComponents.size() - 1)) {
				Label spaceLabel = space();
				leftPanel.addComponent(spaceLabel);
				leftPanel.setComponentAlignment(spaceLabel, Alignment.MIDDLE_LEFT);
			}
		}
		this.addComponent(leftPanel);
		this.setComponentAlignment(leftPanel, Alignment.BOTTOM_LEFT);

		// right panel
		HorizontalLayout rightPanel = new HorizontalLayout();
		rightPanel.setSpacing(true);
		for(int i=0; i < rightComponents.size(); i++) {
			rightPanel.addComponent(rightComponents.get(i));
			if(needRightSeperate && i < (rightComponents.size() - 1)) {
				Label spaceLabel = space();
				rightPanel.addComponent(spaceLabel);
				rightPanel.setComponentAlignment(spaceLabel, Alignment.MIDDLE_LEFT);
			}
		}
		this.addComponent(rightPanel);
		this.setComponentAlignment(rightPanel, Alignment.BOTTOM_RIGHT);
	}
	
	public void addLeftComponent(Component component) {
		leftComponents.add(component);
	}
	
	public void addRightComponent(Component component) {
		rightComponents.add(component);
	}

	public Label space() {
		Label space = new Label("|");
		space.addStyleName("c-seperate-space");
		return space;
	}
	/**
	 * @return the needLeftSeperate
	 */
	public boolean isNeedLeftSeperate() {
		return needLeftSeperate;
	}

	/**
	 * @param needLeftSeperate the needLeftSeperate to set
	 */
	public void setNeedLeftSeperate(boolean needLeftSeperate) {
		this.needLeftSeperate = needLeftSeperate;
	}

	/**
	 * @return the needRightSeperate
	 */
	public boolean isNeedRightSeperate() {
		return needRightSeperate;
	}

	/**
	 * @param needRightSeperate the needRightSeperate to set
	 */
	public void setNeedRightSeperate(boolean needRightSeperate) {
		this.needRightSeperate = needRightSeperate;
	}

	/**
	 * @return the leftComponent
	 */
	public List<Component> getLeftComponent() {
		return leftComponents;
	}

	/**
	 * @param leftComponent the leftComponent to set
	 */
	public void setLeftComponent(List<Component> leftComponent) {
		this.leftComponents = leftComponent;
	}

	/**
	 * @return the rightComponent
	 */
	public List<Component> getRightComponent() {
		return rightComponents;
	}

	/**
	 * @param rightComponent the rightComponent to set
	 */
	public void setRightComponent(List<Component> rightComponent) {
		this.rightComponents = rightComponent;
	}

	/**
	 * @return the toolbarWidth
	 */
	public String getToolbarWidth() {
		return toolbarWidth;
	}

	/**
	 * @param toolbarWidth the toolbarWidth to set
	 */
	public void setToolbarWidth(String toolbarWidth) {
		this.toolbarWidth = toolbarWidth;
	}
}
