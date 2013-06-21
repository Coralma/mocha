package com.mocha.mobile.ui;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

import com.coral.foundation.md.model.MobileMenu;
import com.coral.foundation.md.model.Mocha;
import com.coral.vaadin.resource.ModelCenter;
import com.google.common.collect.Lists;
import com.mocha.mobile.model.MenuData;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("rawtypes")
@Configurable(preConstruction = true)
public class NavigateMenuManager extends NavigationManager implements INavigation {

    private static final long serialVersionUID = 1L;
    
//    @Autowired
//    protected ModelRepository modelRepo;
    
    public NavigateMenuManager() {
        setWidth("250px");
        addStyleName("mailboxes");
        navigateTo(new NavigateMenuView(this, createNavigationMenu(),"Control"));
    }
    
    protected List<MenuData> createNavigationMenu() {
    	List<MenuData> menuDatas = Lists.newArrayList();
    	List<Mocha> models = ModelCenter.getModel();
    	for (Mocha model : models) {
			List<MobileMenu> menuList = model.getMobileMenuList();
			if (menuList != null) {
				for (MobileMenu menu : menuList) {
					menuDatas.add(setupMenu(menu));
//					MenuData menuData = new MenuData();
//		            menuData.setName(menu.getName());
//		            menuData.setPage(menu);
//		            menuData.setTitle(menu.getLabel());
//		            for(MobileMenu subMenu : menu.getSubMenus()) {
//		            	menuData.getMenus().(setupMenu(subMenu));
//		            }
//	                menuDatas.add(menuData);
				}
			}
		}
		return menuDatas;
    }
    
    public MenuData setupMenu(MobileMenu menu) {
    	MenuData menuData = new MenuData();
        menuData.setName(menu.getName());
        menuData.setPage(menu);
        menuData.setTitle(menu.getLabel());
    	for(MobileMenu subMenu : menu.getSubMenus())
    		menuData.getMenus().add(setupMenu(subMenu));
        return menuData;
    }
//        List<MenuData> menuDatas = new ArrayList<MenuData>();
//        NelNavigtionMenu menu = modelRepo.getNavigationMenu();
//        if(menu==null) {
//            return null;
//        }
//        for (MenuItem subItem : menu.getItems()) {
//            MenuData menuData = new MenuData();
//            menuData.setName(subItem.getName());
//            menuData.setPage(subItem.getPage());
//            menuData.setTitle(subItem.getTitle());
//            if(subItem.getParent() != null) {
//                addChild(menuDatas, subItem.getParent(), menuData);
//            } else {
//                menuDatas.add(menuData);
//            }
//        }
//        
//        return menuDatas;
//    }
//    
//    public void addChild(List<MenuData> menuDatas, MenuItem parentItem, MenuData menuData) {
//        for(MenuData parent : menuDatas) {
//            if(parent.getName().equals(parentItem.getName())) {
//                parent.getMenus().add(menuData);
//            }
//        }
//    }
}
