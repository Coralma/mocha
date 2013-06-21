package com.mocha.mobile.ui;
import org.springframework.beans.factory.annotation.Configurable;

import com.coral.foundation.md.model.MobileMenu;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Component;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("rawtypes")
@Configurable(preConstruction = true)
public class MobileViewManager extends NavigationManager implements INavigation {

    private static final long serialVersionUID = 1L;
    private static String index = "index";
//    @Autowired
//    protected ModelRepository modelRepo;
    protected MobileSearchView currentView;
    protected Component popupMenu;

    public MobileViewManager() {
        setWidth("100%");
        addStyleName("mailboxes");
//        NelNavigtionMenu menu = modelRepo.getNavigationMenu();
//        NelPage page = null;
//        for (MenuItem subItem : menu.getItems()) {
//            if(index.equals(subItem.getPage().getName())) {
//                page = subItem.getPage();
//                break;
//            }
//        }
//        if(page != null) {
//            navigatePage(page);
//        } else {
//            throw new RuntimeException("The index page of NelPage didn't define. Please check you page model definition file.");
//        }
    }
    
    public void navigatePage(MobileMenu page) {
        if(page != null) {
            MobileSearchView view = new MobileSearchView(this, page);
            view.setTitle(page.getLabel());
            currentView = view;
            navigateTo(view);
        } else {
            throw new RuntimeException("The NelPage is null. Please check you page model definition file.");
        }
    }
//
//    public void setLeftComponent(Component popupMenu) {
//        this.popupMenu = popupMenu;
//        currentView.setLeftComponent(popupMenu);
//    }
}
