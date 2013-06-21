package com.mocha.mobile.ui;
import org.springframework.beans.factory.annotation.Configurable;

import com.coral.foundation.md.model.MobileMenu;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author Coral.Ma
 * 
 */
@Configurable(preConstruction = true)
public class PhoneApplication extends NavigationManager implements PageView, INavigation {
//
//    private static final long serialVersionUID = 1L;
//    @Autowired
//    protected ModelRepository modelRepo;
//
//    public PhoneApplication() {
//        setSizeFull();
//        addStyleName("phone");
//        // Object obj = createNavigationMenu();
//        // navigateTo(new ControlMenuView(this,
//        // navigateMenu.getMenuList(),"Control"));
//        navigateTo(new PhoneMenuView(this, createNavigationMenu(), "Control"));
//    }
//
//    protected List<MenuData> createNavigationMenu() {
//        List<MenuData> menuDatas = new ArrayList<MenuData>();
//        NelNavigtionMenu menu = modelRepo.getNavigationMenu();
//        if (menu == null) {
//            return null;
//        }
//        for (MenuItem subItem : menu.getItems()) {
//            MenuData menuData = new MenuData();
//            menuData.setName(subItem.getName());
//            menuData.setPage(subItem.getPage());
//            menuData.setTitle(subItem.getTitle());
//            // menuData.setMenus(null);
//            if (subItem.getParent() != null) {
//                addChild(menuDatas, subItem.getParent(), menuData);
//            } else {
//                menuDatas.add(menuData);
//            }
//        }
//
//        return menuDatas;
//    }
//
//    public void addChild(List<MenuData> menuDatas, MenuItem parentItem,
//            MenuData menuData) {
//        for (MenuData parent : menuDatas) {
//            if (parent.getName().equals(parentItem.getName())) {
//                parent.getMenus().add(menuData);
//            }
//        }
//    }
//
//    @Override
    public void navigatePage(MobileMenu page) {
//        if(page != null) {
//            MobileSearchView view = new MobileSearchView(this, page);
//            view.setTitle(page.getTitle());
//            navigateTo(view);
//        } else {
//            throw new RuntimeException("The NelPage is null. Please check you page model definition file.");
//        }
    }

}
