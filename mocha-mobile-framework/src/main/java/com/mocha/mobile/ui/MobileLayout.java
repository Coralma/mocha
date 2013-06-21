package com.mocha.mobile.ui;

import java.util.Map;

import com.coral.foundation.md.model.ViewField;
import com.coral.vaadin.resource.ModelCenter;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.google.common.collect.Maps;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 * 
 */
public class MobileLayout extends VerticalLayout {// implements EntityFormLayout
													// {

	private static final long serialVersionUID = 1L;

	protected Map<String, VerticalComponentGroup> sectionGroups = Maps
			.newHashMap();
	protected Map<String, Layout> sectionLayouts = Maps.newHashMap();
	protected ViewContext viewContext;

	public MobileLayout(ViewContext viewContext) {
		super();
		this.viewContext = viewContext;
	}

	@Override
	public void addComponent(Component c) {
		ViewField field = ModelCenter.getViewField(viewContext.getEntity(),
				((AbstractComponent) c).getData().toString());
		// field.getViewSection().get
		String sectionCaption = field.getViewSection().getLabel();
		Layout formLayout = sectionLayouts.get(sectionCaption);
		if (formLayout == null) {
			VerticalComponentGroup componentGroup = new VerticalComponentGroup();
			componentGroup.setCaption(sectionCaption);
			if (c instanceof IView) {
				formLayout = new VerticalLayout();
			} else {
				formLayout = new FormLayout();
			}
			sectionGroups.put(sectionCaption, componentGroup);
			sectionLayouts.put(sectionCaption, formLayout);
			componentGroup.addComponent(formLayout);
			super.addComponent(componentGroup);
		}
		formLayout.addComponent(c);
	}

	// @Override
	// public void addComponent(Component c) {
	// if(c instanceof AbstractComponent &&
	// ((AbstractComponent)c).getData() instanceof ModelViewElement) {
	// final ModelViewElement mve =
	// (ModelViewElement)((AbstractComponent)c).getData();
	// String sectionCaption = mve.getSectionCaption();
	// Layout formLayout = sectionLayouts.get(sectionCaption);
	// if(formLayout == null) {
	// VerticalComponentGroup componentGroup = new VerticalComponentGroup();
	// componentGroup.setCaption(sectionCaption);
	// if(c instanceof IView) {
	// formLayout = new VerticalLayout();
	// } else {
	// formLayout = new FormLayout();
	// }
	// sectionGroups.put(sectionCaption, componentGroup);
	// sectionLayouts.put(sectionCaption, formLayout);
	// componentGroup.addComponent(formLayout);
	// super.addComponent(componentGroup);
	// }
	// formLayout.addComponent(c);
	// }
	// }
	//
	// @Override
	// public void showComponent(Component c) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void hideComponent(Component c) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void attachComponentsToSectionPanel(String sectionName,
	// List<? extends Component> components, Alignment alignment) {
	// if(components.size()==0) return;
	// List<String> sectionCaptions =
	// NatExprLangPropertyDescriptor.getArrayValues(sectionName);
	// ComponentContainer container =
	// sectionGroups.get(getSectionCaptionKey(sectionCaptions));
	// if(container != null) {
	// HorizontalLayout sectionBottom = new HorizontalLayout();
	// sectionBottom.setWidth("100%");
	// HorizontalLayout hLayout = new HorizontalLayout();
	// // hLayout.setMargin(true,false,false,false);
	// hLayout.setStyleName("v-button-layout");
	// hLayout.setSpacing(true);
	// for(Component component : components) {
	// hLayout.addComponent(component);
	// }
	// sectionBottom.addComponent(hLayout);
	// sectionBottom.setComponentAlignment(hLayout, alignment);
	// container.addComponent(sectionBottom);
	// }
	// }
	//
	// private static String getSectionCaptionKey (List<String> sectionCaptions)
	// {
	// if(sectionCaptions==null || sectionCaptions.isEmpty())
	// return null;
	//
	// StringBuilder builder = new StringBuilder();
	// for(String sectionCaption : sectionCaptions) {
	// if(builder.length()>0)
	// builder.append(">>");
	// builder.append(sectionCaption);
	// }
	// return builder.toString();
	// }
}
