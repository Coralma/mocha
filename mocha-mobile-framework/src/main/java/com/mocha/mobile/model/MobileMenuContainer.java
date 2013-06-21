package com.mocha.mobile.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author Coral.Ma
 *
 */
public class MobileMenuContainer extends BeanItemContainer<MenuData> implements Container.Hierarchical {

    private static final long serialVersionUID = 1L;

    public MobileMenuContainer() {
        super(MenuData.class);
    }

    public Collection<? extends MenuData> getChildren(Object parent) {
        List<MenuData> children = new ArrayList<MenuData>();
        for (MenuData pojo : getAllItemIds()) {
            if (pojo.getParent() == parent) {
                children.add(pojo);
            }
        }
        return children;
    }

    public Object getParent(Object itemId) {
        MenuData pojo = (MenuData) itemId;
        return pojo.getParent();
    }

    /**
     * Root items are Mailboxes so this returns the mailboxes
     */
    public Collection<?> rootItemIds() {
        List<MenuData> pojos = getAllItemIds();
        if (pojos != null) {
            List<MenuData> mailboxes = new ArrayList<MenuData>();
            for (MenuData pojo : pojos) {
                if (isRoot(pojo)) {
                    mailboxes.add((MenuData) pojo);
                }
            }
            return mailboxes;
        }
        return null;
    }

    public boolean setParent(Object itemId, Object newParentId)
            throws UnsupportedOperationException {
        if (areChildrenAllowed(newParentId)) {
            MenuData pojo = (MenuData) itemId;
            pojo.setParent((MenuData) newParentId);
            return true;
        }
        return false;
    }

    public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not in use");
    }

    public boolean isRoot(Object itemId) {
        return itemId instanceof MenuData;
    }

    public boolean hasChildren(Object itemId) {
        for (MenuData pojo : getAllItemIds()) {
            if (pojo.getParent() == itemId) {
                return true;
            }
        }
        return false;
    }

    public void setFilter(Filter filter) {
        removeAllContainerFilters();
        if (filter != null) {
            addFilter(filter);
        }
    }


    public boolean areChildrenAllowed(Object itemId) {
        return false;
    }
}
