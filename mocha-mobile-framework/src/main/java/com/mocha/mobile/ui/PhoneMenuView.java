package com.mocha.mobile.ui;
import java.util.List;

import com.coral.foundation.md.model.MobileMenu;
import com.mocha.mobile.model.MenuData;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral.Ma
 *
 */
public class PhoneMenuView extends NavigationView {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")
    public PhoneMenuView(final INavigation nav, List<MenuData> menus, String captuion) {
        setCaption(captuion);
        VerticalComponentGroup menuLayout = new VerticalComponentGroup();
        menuLayout.setWidth("100%");
        for(final MenuData f : menus) {
            List<MenuData> subMenus = f.getMenus();
              if(subMenus != null && subMenus.size() > 0) {
                  NavigationButton btn = new NavigationButton(f.getTitle());
                  btn.setWidth("100%");
                  btn.addStyleName("pill");
                  btn.addListener(new Button.ClickListener() {
                      public void buttonClick(ClickEvent event) {
                          nav.navigateTo(new PhoneMenuView(nav, f.getMenus(),f.getTitle()));
                      }
                  });
                  menuLayout.addComponent(btn);
              } else {
                  CssLayout layout = new CssLayout();
                  StringBuilder spacing = new StringBuilder();
                  spacing.append("<div style='float:left; width:200px; font-size: 17px; font-weight: bold; line-height: 43px;margin-left: 1px;'>"+ f.getTitle() +"</div>");
                  layout.setHeight("43px");
                  layout.setWidth("100%");
                  Label text = new Label(spacing.toString(),Label.CONTENT_XHTML);
                  layout.addComponent(text);
                  layout.addListener(new LayoutClickListener() {
                      public void layoutClick(LayoutClickEvent event) {
                          System.out.println(f.getPage().getName() + " was clicked!");
//                          navigatePage(f.getPage());
                      }
                  });
                  menuLayout.addComponent(layout);
              }
          }
        setContent(menuLayout);
    }
    
    private void navigatePage(final MobileMenu page) {
        ComponentContainer app = getApplication().getMainWindow().getContent();
        if (app instanceof PageView) {
            PageView mainView = (PageView) app;
            mainView.navigatePage(page);
        }
    }
}
