package com.mocha.mobile.ui;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Configurable;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.MobileMenu;
import com.coral.foundation.model.BaseEntity;
import com.coral.vaadin.resource.ModelCenter;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.coral.vaadin.widget.view.builder.ViewContext.Action;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings({"rawtypes","unchecked","serial"})
@Configurable(preConstruction = true)
public class MobileSearchView extends NavigationView implements ClickListener,IView {

    private static final long serialVersionUID = 5325191246876471048L;
//    @Autowired
//    private ModelUtil modelUtil;
//    @Autowired
//    protected ModelRepository modelRepo;
//    @Autowired
//    private ConversationManager conversation;
    
    private INavigation nav;
    private MobileMenu page;
    private CssLayout layout = new CssLayout();
    private VerticalComponentGroup componentGroup = new VerticalComponentGroup();
    VerticalComponentGroup group = new VerticalComponentGroup();
    private Toolbar messageActions = new Toolbar();
    private Button createButton;
    private Button homeButton;
    
    public MobileSearchView(INavigation nav, MobileMenu page) {
        this.nav = nav;
        this.page= page;
//        if (conversation.inProgress())
//            conversation.cancel();
//        conversation.begin();
        setCaption("Search View");
        buildToolbar();
        //set tool bar actions
        messageActions.setStyleName(null);
        messageActions.setWidth("50px");
        messageActions.setHeight("40px");
        messageActions.setMargin(false,true,false,false);
        setRightComponent(messageActions);
        if(nav instanceof MobileViewManager) {
            setLeftComponent(null);
        }
        // init main screen
        componentGroup.setCaption("Entity Search View");
        layout.addComponent(componentGroup);
        initFuzzySearch();
        initEntityResultGrid();
        layout.addComponent(group);
        setContent(layout);
    }
    
    public void setTitle(String title) {
        setCaption(title);
        componentGroup.setCaption(title);
    }
    
    public void initFuzzySearch() {
        HorizontalLayout conditionLayout = new HorizontalLayout();
        conditionLayout.setWidth("100%");
        TextField keyword = new TextField();
        keyword.setWidth("100%");
        Label search = new Label();
        search.setWidth("48px");
        search.setIcon(new ThemeResource(("graphics/search.png")));
        conditionLayout.addComponent(keyword);
        conditionLayout.setComponentAlignment(keyword,Alignment.MIDDLE_CENTER);
        conditionLayout.addComponent(search);
        conditionLayout.setComponentAlignment(search,Alignment.MIDDLE_RIGHT);
        conditionLayout.setExpandRatio(keyword, 10.0f);
        componentGroup.addComponent(conditionLayout);
    }
    
    public void initEntityResultGrid() {
        group.removeAllComponents();
        group.setCaption("Search Result");
        Set entitySet = null; 
//        SimpleEditorViewWidget widget = (SimpleEditorViewWidget) page.getWidget();
//        NelEntity nelEntity = widget.getEntity();
//        Class entityClazz = modelUtil.getClass(nelEntity);
//        List entityList = findAllEntityData(entityClazz);
//        if(entityList != null) {
//            entitySet = new HashSet();
//            entitySet.addAll(entityList);
//        }
//        ViewContext viewContext = ViewContext.Create(entityClazz, Action.SEARCH, null, entitySet);
//        EntityCollection collection = new EntityCollection(viewContext, "Search Results", this);
//        collection.setNav(nav);
//        group.addComponent(collection);
    }
    
    private void buildToolbar() {
        createButton = new Button(null, this);
        createButton.setStyleName("no-decoration");
        createButton.setIcon(new ThemeResource("graphics/add-icon.png"));
        
        createButton.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
//                SimpleEditorViewWidget widget = (SimpleEditorViewWidget) page.getWidget();
//                NelEntity nelEntity = widget.getEntity();
//                Class entityClazz = modelUtil.getClass(nelEntity);
//                ViewContext viewContext = ViewContext.Create(entityClazz, Action.CREATE, null, getValue(entityClazz));
            	Entity entity = ModelCenter.getEntity(page.getPage());
            	ViewContext viewContext = new ViewContext(entity,null,Action.CREATE);
                nav.navigateTo(new MobileCreateView(nav,viewContext,MobileSearchView.this));
                
            }
        });
        messageActions.addComponent(createButton);
    }
    
    public List findAllEntityData(Class entityClass) {
//        CriteriaBuilder builder = conversation.getEntityManager().getCriteriaBuilder();
//        CriteriaQuery criteria = builder.createQuery(entityClass);
//        Root root = criteria.from(entityClass);
//        criteria.select(root);
//        return conversation.getEntityManager().createQuery(criteria).getResultList();
    	return null;
    }
    
    public BaseEntity getValue(Class entityClazz) {
        try {
//            return (BaseEntity) entityClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void buttonClick(ClickEvent event) {
        System.out.println(event.getButton());
    }

    @Override
    public void refresh(Object obj) {
        initEntityResultGrid();
    }

    @Override
    public void setNav(INavigation nav) {
        this.nav = nav;
    }
}
