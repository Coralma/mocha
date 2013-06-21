/**
 * 
 */
package com.mocha.mobile.ui.component;

import org.apache.commons.lang.StringUtils;
import org.vaadin.addon.customfield.CustomField;

import com.coral.vaadin.widget.view.builder.ViewContext;
import com.mocha.mobile.ui.INavigation;
import com.mocha.mobile.ui.IReference;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 *
 */
public class NavigateForm extends CustomField implements IReference {

    private static final long serialVersionUID = 1L;
    ViewContext viewContext;
    String propertyName;
    INavigation nav;
    NavigationButton button;
    
    public NavigateForm(ViewContext viewContext, Object propertyId) {
        this.viewContext = viewContext;
        if(propertyId != null) {
//            propertyName = StringUtils.uncamelCase(propertyId.toString());
        }
    }
    
    @SuppressWarnings("serial")
    @Override
    public void attach() {
        super.attach();
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true,false,true,false);
        layout.setWidth("100%");
        button = new NavigationButton();
        button.setWidth("100%");
//        if(viewContext.getPojo() == null) {
//            button.setCaption("Create " + propertyName);
//        } else {
//            button.setCaption(ViewContextHelper.generateListItemCapture(viewContext, viewContext.getPojo()));
//        }
        
        button.addListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
//                nav.navigateTo(new MobileFormView(propertyName, viewContext, nav, NavigateForm.this));
            }
        });
        layout.addComponent(button);
        setCompositionRoot(layout);
    }
    
    public void refresh(Object value) {
//        button.setCaption(ViewContextHelper.generateListItemCapture(viewContext, value));
//        viewContext.setPojo(value);
//        super.setValue(value);
    }
    
    @Override
    public void remove(Object obj) {
        button.setCaption("Create " + propertyName);
        super.setValue(null);
    }

    @Override
    public Class<?> getType() {
        return null;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(INavigation nav) {
        this.nav = nav;
    }

}
