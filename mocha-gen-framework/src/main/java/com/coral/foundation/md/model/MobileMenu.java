/**
 * 
 */
package com.coral.foundation.md.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class MobileMenu implements Serializable {
	
	public String label;
	public String name;
	public String page;
	public List<MobileMenu> subMenus = new ArrayList<MobileMenu>();
	public MobileMenu parentMenu;
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the subMenus
	 */
	public List<MobileMenu> getSubMenus() {
		return subMenus;
	}
	/**
	 * @param subMenus the subMenus to set
	 */
	public void setSubMenus(List<MobileMenu> subMenus) {
		this.subMenus = subMenus;
	}
	/**
	 * @return the parentMenu
	 */
	public MobileMenu getParentMenu() {
		return parentMenu;
	}
	/**
	 * @param parentMenu the parentMenu to set
	 */
	public void setParentMenu(MobileMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

}
