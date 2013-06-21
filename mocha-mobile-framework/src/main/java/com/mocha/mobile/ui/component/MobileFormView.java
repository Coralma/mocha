/**
 * 
 */
package com.mocha.mobile.ui.component;

import com.coral.vaadin.widget.view.builder.ViewContext;
import com.mocha.mobile.ui.INavigation;
import com.mocha.mobile.ui.IReference;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

/**
 * @author Coral.Ma
 *
 */
public class MobileFormView extends NavigationView implements ClickListener {
    
    private static final long serialVersionUID = 1L;
    private CssLayout layout = new CssLayout();
    Button saveButton;
    INavigation nav;
    IReference reference;
    MobileEntityForm form;
    ViewContext viewContext;
    
    public MobileFormView(String caption, ViewContext viewContext, INavigation nav, IReference reference) {
        this.reference = reference;
        this.nav = nav;
        this.viewContext = viewContext;
        setCaption(caption);
        initToolbar();
        form = new MobileEntityForm(viewContext,nav);
        layout.addComponent(form);
        setContent(layout);
    }

    public void initToolbar() {
        Toolbar messageActions = new Toolbar();
        messageActions.setStyleName(null);
        messageActions.setWidth("50px");
        messageActions.setHeight("32px");
        messageActions.setMargin(false);
        
        saveButton = new Button(null, this);
        saveButton.setData("save");
        saveButton.setIcon(new ThemeResource("graphics/compose-icon.png"));
        saveButton.setStyleName("no-decoration");
        messageActions.addComponent(saveButton);
//        if(Action.UPDATE.equals(viewContext.getAction())) {
//            Button deleteButton = new Button(null, this);
//            deleteButton.setData("delete");
//            deleteButton.setIcon(new ThemeResource("graphics/trash-icon.png"));
//            deleteButton.setStyleName("no-decoration");
//            messageActions.addComponent(deleteButton);
//            messageActions.setWidth("100px");
//        }
        setRightComponent(messageActions);
    }

    public void buttonClick(ClickEvent event) {
//        form.commit();
//        Object value = form.getPojo();
//        String action = event.getButton().getData().toString();
//        if("save".equals(action)) {
//            reference.refresh(value);
//        } else if("delete".equals(action)) {
//            reference.remove(value);
//        }
//        nav.navigateBack();
    }
}
