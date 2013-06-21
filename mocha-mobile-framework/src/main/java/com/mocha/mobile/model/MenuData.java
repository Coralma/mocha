package com.mocha.mobile.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.md.model.MobileMenu;

/**
 * @author Coral.Ma
 *
 */
public class MenuData implements Serializable {

    private static final long serialVersionUID = 1L;
    public String name;
    public String title;
    public MenuData parent;
    public MobileMenu page;
    public List<MenuData> menus = new ArrayList<MenuData>();
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the menus
     */
    public List<MenuData> getMenus() {
        return menus;
    }
    /**
     * @param menus the menus to set
     */
    public void setMenus(List<MenuData> menus) {
        this.menus = menus;
    }
    /**
     * @return the parent
     */
    public MenuData getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    public void setParent(MenuData parent) {
        this.parent = parent;
    }
    /**
     * @return the page
     */
    public MobileMenu getPage() {
        return page;
    }
    /**
     * @param page the page to set
     */
    public void setPage(MobileMenu page) {
        this.page = page;
    }
}
