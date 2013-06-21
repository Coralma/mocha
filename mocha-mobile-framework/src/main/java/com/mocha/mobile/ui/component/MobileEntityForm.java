/**
 * 
 */
package com.mocha.mobile.ui.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.vaadin.addon.customfield.CustomField;

import com.coral.vaadin.widget.view.builder.ViewContext;
import com.mocha.mobile.ui.INavigation;
import com.mocha.mobile.ui.IView;
import com.mocha.mobile.ui.MobileLayout;
import com.mocha.mobile.ui.builder.IMobileFieldBuilder;
import com.mocha.mobile.ui.builder.MobileFieldBuilder;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings({ "rawtypes", "serial","unchecked" })
@Configurable(preConstruction = true)
public class MobileEntityForm extends CustomField implements IView { //, ActionHandler {

    private ViewContext viewContext;
    private INavigation nav;
//    @Autowired
//    protected ModelRepository modelRepo;
//    @Autowired
//    private NelFieldBuilder.Form fieldBuilder;
    private Object pojo;
    protected List<Button> userActions;
    protected Map<String, Button> userActionMap;
    private Form form;
//    protected List<NelActionDescription> actionDescs;
    
    public Class<?> getType() {
        return null;
    }
    
    public MobileEntityForm(ViewContext viewContext, INavigation nav) {
        this.viewContext = viewContext;
        this.nav = nav;
//        actionDescs = modelRepo.getActions(viewContext.getEntityType().getName(), viewContext.getModelView());
//        if(!actionDescs.isEmpty()) {
//            userActions = Lists.newArrayList();
//            userActionMap = Maps.newHashMap();
//        }
    }
    
    @Override
    public void attach() {
        super.attach();
        form = new Form();
        form.setLayout(new MobileLayout(viewContext));
        pojo = viewContext.getPojo();
        if(pojo == null) {
            pojo = initPojo(viewContext.getEntityClass());
//        } else {
//            if(pojo instanceof Collection) {
//                pojo = initPojo(viewContext.getEntityType());
//            }
        }
        BeanItem item = new BeanItem(pojo, viewContext.getViewElements().keySet());
        form.setImmediate(true);
        form.setWriteThrough(true);
        form.setFormFieldFactory(getFormFieldFactory());
        form.setItemDataSource(item);
        setCompositionRoot(form);
    }
    
    /**
     * Set the defined section button to specified section.
     */
//    public void setSectionButton() {
//        Map<String, List<Button>> sectionContact = createSectionActionButton(this);
//        if(sectionContact == null) return;
//        EntityFormLayout layout = (EntityFormLayout)form.getLayout();
//        for(Entry<String, List<Button>> entry : sectionContact.entrySet()) {
//            layout.attachComponentsToSectionPanel(entry.getKey(), entry.getValue(), Alignment.BOTTOM_RIGHT);
//        }
//    }
//    
//    /**
//     * Create a section action button list.
//     * @return Map<String, List<Button>> a collection of section and button list.
//     */
//    public Map<String, List<Button>> createSectionActionButton(ActionHandler handler){
////        List<NelActionDescription> actionDescs = modelRepo.getActions(viewContext.getEntityType().getName(), viewContext.getModelView());
//        Map<String, List<Button>> sectionContents = Maps.newHashMap();
//        if (actionDescs.isEmpty())
//            return null;
//        for (final NelActionDescription actionDesc : actionDescs) {
//            String sectionPath = getActionProperty(actionDesc,"section");
//            if(sectionPath != null) {
//                List<Button> buttons = sectionContents.get(sectionPath);
//                if(buttons == null) {
//                     buttons = new ArrayList<Button>();
//                }
//                buttons.add(createCustomizeButton(actionDesc,handler));
//                sectionContents.put(sectionPath, buttons);
//            }
//        }
//        return sectionContents;
//    }
//    
//    public String getActionProperty(NelActionDescription actionDesc, String key) {
//        Iterator<NelProperty> properties = actionDesc.getProperties().iterator();
//        while(properties.hasNext()) {
//            NelProperty nelProperty = properties.next();
//            if(key.equals(nelProperty.getName())) {
//                return nelProperty.getValue();
//            }
//        }
//        return null;
//    }
//    
//    public Button createCustomizeButton(NelActionDescription actionDesc, final ActionHandler handler) {
//        Button userAction = new Button(getActionProperty(actionDesc, "label"));
//        NelMicroFlowInvoker nelMicroFlowInvoker = new NelMicroFlowInvoker(actionDesc.getActionInvoker());
//        userAction.setData(nelMicroFlowInvoker);
//        userActions.add(userAction);
//        userActionMap.put(actionDesc.getActionInvoker().getMicroFlow().getName(), userAction);
//        userAction.addListener(new ClickListener() {
//            public void buttonClick(ClickEvent event) {
//                Button userAction = event.getButton();
//                userAction.setComponentError(null);
//                NelMicroFlowInvoker nelMicroFlowInvoker = (NelMicroFlowInvoker) userAction
//                        .getData();
//                nelMicroFlowInvoker.execute(pojo, handler);
//                MobileEntityForm.this.requestRepaintAll();
//            }
//        });
//        return userAction;
//    }
    
    public Object initPojo(Class entityType) {
        Object value = null;
        try {
            value = entityType.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    
    private FormFieldFactory getFormFieldFactory() {
        FormFieldFactory factory = new FormFieldFactory() {
            
            public Field createField(Item item, Object propertyId, Component uiContext) {
            	IMobileFieldBuilder builder = new MobileFieldBuilder();
            	Field field = builder.build(viewContext.getViewField(propertyId.toString()));
            	return field;
            }
            
        };
        return factory;
    }

    /**
     * @return the pojo
     */
    public Object getPojo() {
        return pojo;
    }

    /**
     * @param pojo the pojo to set
     */
    public void setPojo(Object pojo) {
        this.pojo = pojo;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(INavigation nav) {
        this.nav = nav;
    }

    @Override
    public void refresh(Object value) {
        
    }

//    @Override
//    public void enableField(Object propertyId) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void disableField(Object propertyId) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void showField(Object propertyId) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void hideField(Object propertyId) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void updateField(Object propertyId) {
//        // TODO Auto-generated method stub
//        
//    }
}
