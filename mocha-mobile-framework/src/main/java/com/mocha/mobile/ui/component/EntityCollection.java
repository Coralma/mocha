/**
 * 
 */
package com.mocha.mobile.ui.component;

import org.vaadin.addon.customfield.CustomField;

import com.mocha.mobile.ui.IReference;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked","serial"})
public class EntityCollection extends CustomField {//implements IReference {
//
//    private static final long serialVersionUID = 1L;
//    private Set collection = new HashSet();
//    private ViewContext viewContext;
//    private String propertyName;
//    private INavigation nav;
//    private Action action;
//    private IView superView;
//    private VerticalLayout layout = new VerticalLayout();
//    
//    public EntityCollection(ViewContext viewContext, Object propertyId, IView superView) {
//        this.viewContext = viewContext;
//        this.action = viewContext.getAction();
//        this.superView = superView;
//        if(viewContext.getPojo() != null) {
//            this.collection.addAll((Set)viewContext.getPojo()); 
//        }
//        if(propertyId != null) {
//            propertyName = StringUtils.uncamelCase(propertyId.toString());
//        }
//        layout.setWidth("100%");
//        layout.setMargin(true,false,true,false);
//        setCompositionRoot(layout);
//    }
//    
//    @Override
//    public void attach() {
//        super.attach();
//        collectEntity();
//    }
//    
//    private void collectEntity() {
//        layout.removeAllComponents();
//        for(final Object obj : collection) {
//            String capture = ViewContextHelper.generateListItemCapture(viewContext, obj);
//            NavigationButton button = new NavigationButton(capture);
//            button.setWidth("100%");
//            if(Action.SEARCH.equals(action)) {
//                button.addListener(new ClickListener() {
//                    public void buttonClick(ClickEvent event) {
//                        ViewContext context = ViewContext.Create(viewContext.getEntityType(), Action.UPDATE, null, obj);
//                        nav.navigateTo(new MobileCreateView(nav,context,superView));
//                    }
//                });
//            } else {
//                button.addListener(new ClickListener() {
//                    public void buttonClick(ClickEvent event) {
//                        viewContext.setPojo(obj);
//                        nav.navigateTo(new MobileFormView(propertyName, viewContext, nav, EntityCollection.this));
//                    }
//                });
//            }
//            layout.addComponent(button);
//        }
//        if(!Action.SEARCH.equals(action)) {
//            initAddButton();
//        }
//    }
//    
//    private void initAddButton() {
//        NavigationButton button = new NavigationButton("Add " + propertyName);
//        button.setWidth("100%");
//        button.addListener(new ClickListener() {
//            public void buttonClick(ClickEvent event) {
//                viewContext.setPojo(null);
//                nav.navigateTo(new MobileFormView(propertyName, viewContext, nav, EntityCollection.this));
//            }
//        });
//        layout.addComponent(button);
//        
//    }
//    
//    public void refresh(Object value) {
//        if(value != null) {
//            collection.add(value);
//            setValue(collection);
//            collectEntity();
//        }
//    }
//    
//    @Override
//    public void remove(Object value) {
//        if(value != null) {
//            collection.remove(value);
//            setValue(collection);
//            collectEntity();
//        }
//    }
//   
    @Override
    public Class<?> getType() {
        return null;
    }
//
//    /**
//     * @param nav the nav to set
//     */
//    public void setNav(INavigation nav) {
//        this.nav = nav;
//    }

}
