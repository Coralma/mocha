/**
 * 
 */
package com.coral.vaadin.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.coral.foundation.core.impl.Serialize;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.vaadin.widget.Viewer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Coral
 * @deprecated
 */
public class ModelCenter {
	private static List<Mocha> CORALS;
	private static List<String> entityNameList;
	private static Map<String,Entity> entityMap;
//	private static String MODEL_XML = "userRoleModel.xml";
	
	public static void initModel(String MODEL_XML) {
		getModel(MODEL_XML);
	}
	
	public static List<Mocha> getModel(String MODEL_XML) {
		try {
			if(CORALS == null) {
				CORALS = (List<Mocha>)Serialize.deserializeModel(MODEL_XML);
			}
		} catch (Exception e) {
			//FIXME logger
		}
		
		return CORALS;
	}
	
	public static List<Mocha> getModel() {
		if(CORALS == null) {
//			throw new Exception("Didn't init model by XML.");
			//FIXME logger
		}
		return CORALS;
	}
	
	public static List<String> getEntityNameList() {
		if(entityNameList == null) {
			initEntity();
		}
		return entityNameList;
	}
	
	public static boolean contains(String entityName) {
		return getEntityNameList().contains(entityName);
	}
	
	public static Entity getEntity(String entityName) {
		if(entityMap == null) {
			initEntity();
		}
//		System.out.println(entityMap.get(entityName).getEntityClass());
		return entityMap.get(entityName);
	}
	
	public static View getView(String entityName, String template) {
		Entity entity = getEntity(entityName);
		for(View view : entity.getViews()) {
			if(template.equals(view.getTemplate())) {
				return view;
			}
		}
		return null;
	}
	
	public static void initEntity() {
		entityMap = Maps.newHashMap();
		entityNameList = Lists.newArrayList();
		for(Mocha coral : CORALS) {
			List<Entity> entityList = coral.getEntityList();
			for(Entity entity: entityList) {
				String entityName = entity.getEntityName();
//				System.out.println(entity.getEntityClass());
				entityNameList.add(entityName);
				entityMap.put(entityName, entity);
			}
		}
		
		//init basicUser entity
		String basicUserEntityName="CargoHomeView";
		entityNameList.add(basicUserEntityName);
		Entity basicUserEntity=new Entity();
		basicUserEntity.setEntityClass("com.coral.foundation.security.BasicUser");
		List<View> views=new ArrayList<View>();
		View view=new View();
		view.setType("CREATE");
		view.setDialogSize("1000");
		view.setLabel("HomeLogin");
		view.setRoot(false);
		view.setTemplate("TCreate");
//		ArrayList<String> actionList=new ArrayList<String>();
//		actionList.add("CREATE");
//		view.setActions(actionList);
		view.setTemplate("TCreate");
		List<ViewSection> sections=new ArrayList<ViewSection>();
		ViewSection viewSection=new ViewSection();
		viewSection.setType("tipbox");
		viewSection.setColumn(2);
		viewSection.setLabel("MY Lable Is HERE");
		List<ViewField> viewFields=new ArrayList<ViewField>();
		ViewField viewField=new ViewField();
		viewField.setFieldName("UserName");
		Properties viewProperties=new Properties();
//		viewField.setViewProperties(viewProperties);
		viewFields.add(viewField);
		Property property=new Property();
		property.setRef("../../../../../../../properties/com.coral.foundation.md.model.Property[2]");
		property.setRequired(true);
		viewField.setProperty(property);
		viewSection.setViewFields(viewFields);
		sections.add(viewSection);
		view.setSections(sections);
		views.add(view);
		basicUserEntity.setViews(views);
		entityMap.put(basicUserEntityName, basicUserEntity);
		
	}
	
	public static String[][] getTableDef(Entity entity) {
		List<String> veriables = Lists.newArrayList();
		for(View view : entity.getViews()) {
			if(view.getTemplate().equals(Viewer.TTable)) {
				for(ViewField field : view.getSections().get(0).getViewFields()) {
					veriables.add(field.getFieldName());
				}
			}
		}
		List<String> heads = Lists.newArrayList();
		for(String veriable :veriables) {
			for(Property property : entity.getProperties()) {
				if(veriable.equals(property.getPropertyName())) {
					heads.add(property.getLabel());
				}
			}
		}
		String[][] results = new String[][]{heads.toArray(new String[0]),veriables.toArray(new String[0])};
		return results;
	}
	
	public static ViewField getViewField(Entity entity, String propertyName) {
		for(View view : entity.getViews()) {
			for(ViewSection section : view.getSections()) {
				return getFieldFromSection(section, propertyName);
//				for(ViewField field : section.getViewFields()) {
//					if(field.getFieldName().equals(propertyName))
//						return field;
//				}
			}
		}
		return null;
	}
	
	public static ViewField getFieldFromSection(ViewSection section, String propertyName) {
		for(ViewField field : section.getViewFields()) {
			if(field.getFieldName().equals(propertyName))
				return field;
		}
		for(ViewSection subSection : section.getViewSections()) {
			return getFieldFromSection(subSection, propertyName);
		}
		return null;
	}
	
	public static Property getProperty(Entity entity, String propertyName) {
		for(Property property : entity.getProperties()) {
			if(property.getPropertyName().equals(propertyName)) {
				return property;
			}
		}
		return null;
	}
	
}
