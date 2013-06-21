package com.mocha.mobile.ui;
import java.util.List;

import com.coral.foundation.md.model.MobileMenu;
import com.google.common.collect.Lists;
import com.mocha.mobile.model.MenuData;
import com.mocha.mobile.model.MobileMenuContainer;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * @author Coral.Ma
 *
 */
public class NavigateMenuView extends NavigationView {

    private static final long serialVersionUID = 1L;
    private List<CssLayout> menuLabels = Lists.newArrayList();
    
    public NavigateMenuView(final INavigation nav, List<MenuData> menus, String captuion) {
        setCaption(captuion);
        setWidth("100%");
        setHeight("100%");
        
        MobileMenuContainer ds = new MobileMenuContainer();
        ds.addAll(menus);
        Table table = new Table(null, ds);
        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        table.setVisibleColumns(new Object[] { "name" });
        table.setSizeFull();
        
        table.addGeneratedColumn("name", new Table.ColumnGenerator() {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("serial")
            public Component generateCell(Table source, Object itemId,
                    Object columnId) {
                if (columnId.equals("name")) {
                    final MenuData menu = (MenuData) itemId;

                    List<MenuData> subMenus = menu.getMenus();
                    if(subMenus != null && subMenus.size() > 0) {
                        NavigationButton btn = new NavigationButton(menu.getTitle());
                        btn.addStyleName("pill");
                        btn.addListener(new Button.ClickListener() {
    
                            private static final long serialVersionUID = 1L;
    
                            public void buttonClick(ClickEvent event) {
                                nav.navigateTo(new NavigateMenuView(nav, menu.getMenus(),menu.getTitle()));
                            }
                        });
                        return btn;
                    } else {
                        final CssLayout layout = new CssLayout();
                        if("index".equals(menu.getPage().getName())) {
                            layout.addStyleName("m-selecte-label");
                        }
                        menuLabels.add(layout);
                        StringBuilder spacing = new StringBuilder();
                        spacing.append("<div style='float:left; width:32px; font-size: 17px; font-weight: bold; line-height: 43px;margin-left: 5px;'>&nbsp;"+ menu.getTitle() +"</div>");
                        layout.setHeight("43px");
                        Label menuLabel = new Label(spacing.toString(),Label.CONTENT_XHTML);
                        layout.addComponent(menuLabel);
                        layout.addListener(new LayoutClickListener() {
                            public void layoutClick(LayoutClickEvent event) {
                                menuSelected(layout);
                                navigatePage(menu.getPage());
                            }
                        });
                        return layout;
                    }
                }
                return null;
            }
        });
        setContent(table);
    }
    
    public void menuSelected(CssLayout selecteLabel) {
        selecteLabel.addStyleName("m-selecte-label");
        for(CssLayout otherLabel : menuLabels) {
            if(!selecteLabel.equals(otherLabel)) {
                otherLabel.removeStyleName("m-selecte-label");
            }
        }
    }
    
    private void navigatePage(final MobileMenu page) {
        ComponentContainer app = getApplication().getMainWindow().getContent();
        if (app instanceof PageView) {
            PageView mainView = (PageView) app;
            mainView.navigatePage(page);
        }
    }

}
