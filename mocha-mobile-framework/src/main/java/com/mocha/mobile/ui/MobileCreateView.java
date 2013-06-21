package com.mocha.mobile.ui;

import org.springframework.beans.factory.annotation.Configurable;

import com.coral.vaadin.widget.view.builder.ViewContext;
import com.mocha.mobile.ui.component.MobileEntityForm;
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
@SuppressWarnings("rawtypes")
@Configurable(preConstruction = true)
public class MobileCreateView extends NavigationView implements ClickListener {

    private static final long serialVersionUID = 5325191246876471048L;
    private INavigation nav;
    private IView superView;
    private ViewContext viewContext;
//    @Autowired
//    protected ModelRepository modelRepo;
//    @Autowired
//    private ConversationManager conversation;
    
    private CssLayout layout = new CssLayout();
    
    private Toolbar messageActions = new Toolbar();
    private Button saveButton;
    private Button deleteButton;
    
    MobileEntityForm form;
    
    public MobileCreateView(INavigation nav, ViewContext viewContext, IView superView) {
        this.nav = nav;
        this.viewContext = viewContext;
        this.superView = superView;
//        setCaption(viewContext.getModelView());
        initToolbar();
        form = new MobileEntityForm(viewContext, nav);
        layout.addComponent(form);
        setContent(layout);
    }
    
    private void initToolbar() {
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
//            deleteButton = new Button(null, this);
//            deleteButton.setData("delete");
//            deleteButton.setIcon(new ThemeResource("graphics/trash-icon.png"));
//            deleteButton.setStyleName("no-decoration");
//            messageActions.addComponent(deleteButton);
//            messageActions.setWidth("100px");
//        }
        setRightComponent(messageActions);
    }

    public void buttonClick(ClickEvent event) {
//        Object pojo = form.getPojo();
//        String action = event.getButton().getData().toString();
//        if("save".equals(action)) {
//            try {
//                conversation.startTranscation();
//                conversation.getEntityManager().persist(pojo);
//                conversation.commit();
//            } catch (Throwable ex) {
//                conversation.rollback();
//                throw new RuntimeException(ex);
//            }
//        } else if("delete".equals(action)) {
//            try {
//                conversation.startTranscation();
//                conversation.getEntityManager().remove(pojo);
//                conversation.commit();
//            } catch (Throwable ex) {
//                conversation.rollback();
//                throw new RuntimeException(ex);
//            }
//        }
//        superView.refresh(pojo);
//        nav.navigateBack();
    }
}
