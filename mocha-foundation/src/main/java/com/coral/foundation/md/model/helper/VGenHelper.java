package com.coral.foundation.md.model.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.utils.StrUtils;

/**
 * @author Coral
 *
 */
public class VGenHelper {

	static Random ran = new Random();
	
	public static View getCurrentView(Entity entity, String templateName) {
		for(View view : entity.getViews()) {
			String t = view.getTemplate();
			if(t!=null && t.equals(templateName)) {
				return view; 
			}
		}
		return null;
	}
	
	public static String genDaoIntf(String entityName) {
		return entityName+"Dao";
	}
	public static String genDaoImpl(String entityName) {
		return entityName+"DaoImpl";
	}
	public static String genCreateViewClassName(String entityName) {
		return entityName+"CreateView";
	}
	public static String genCreatePresenterClassName(String entityName) {
		return entityName+"CreatePresenter";
	}
	public static String genCreateListenerName(String entityName) {
		return entityName+"CreateListener";
	}
	public static String genSearchViewClassName(String entityName) {
		return entityName+"SearchView";
	}
	public static String genSearchListenerName(String entityName) {
		return entityName+"SearchListener";
	}
	public static String genSearchPresenterClassName(String entityName) {
		return entityName+"SearchPresenter";
	}
	public static String genTableClassName(String entityName) {
		return entityName+"Table";
	}
	public static String genTableContainerClassName(String entityName) {
		return entityName+"Container";
	}
	
	public static String genserialVersionUID() {
		return String.valueOf(ran.nextLong());
	}
	
	public static Property getFieldProperty(Entity entity, String fieldName) throws Exception {
		for(Property p : entity.getProperties()) {
			if(p.getPropertyName().equals(fieldName)) {
				return p;
			}
		}
		throw new Exception("Can not get field " + fieldName + " in Entity " + entity.getEntityName() + ".");
	}
	
	public static String getFieldController(Properties viewProperty, Property fieldProperty) {
		String type = fieldProperty.getType();
		String ref = fieldProperty.getRef();
		String orm = fieldProperty.getOrm();
//		String relate = fieldProperty.getRelate();
		if(type.equals("String") && ref == null ) {
			String code = fieldProperty.getCode();
			String viewCode = (String) viewProperty.get("code");
			if(viewCode != null) {
				code = viewCode;
			}
			if(!StrUtils.isEmpty(code)) {
				return "ComboField";
			} else {
				return "StringField";
			}
		} else if(type.equals("Date")) {
			return "DateField";
		} else if(type.equals("Integer") || type.equals("Long")) {
			return "IntegerField";
		} else if(type.equals("BigDecimal")) {
			return "BigDecimalField";
//		} else if(ref != null && "refer".equals(relate)) {
//			return "ReferField"; 
		} else if(type.equals("List")) {
			return fieldProperty.getRef() + "Table";
		}
		return null;
	}
	
	public static String buildFieldController(String sectionName, Properties viewProperty, Property fieldProperty) {
		String type = fieldProperty.getType();
		String ref = fieldProperty.getRef();
		String orm = fieldProperty.getOrm();
//		String relate = fieldProperty.getRelate();
		if(type.equals("String") && ref == null ) {
			String code = fieldProperty.getCode();
			String viewCode = (String) viewProperty.get("code");
			if(viewCode != null) {
				code = viewCode;
			}
			if(!StrUtils.isEmpty(code)) {
				return "ComboField("+sectionName+",\""+fieldProperty.getLabel()+"\",CodeTableFactory.getFactory().getCodeTable(\""+ code +"\"))";
			} else {
				return "StringField("+sectionName+",\""+fieldProperty.getLabel()+"\")";
			}
		} else if(type.equals("Date")) {
			return "DateField("+sectionName+",\""+fieldProperty.getLabel()+"\")";
		} else if(type.equals("Integer") || type.equals("Long")) {
			return "IntegerField("+sectionName+",\""+fieldProperty.getLabel()+"\")";
		} else if(type.equals("BigDecimal")) {
			return "BigDecimalField("+sectionName+",\""+fieldProperty.getLabel()+"\")";
//		} else if(ref != null && "refer".equals(relate)) {
//			return "ReferField("+sectionName+",\""+fieldProperty.getLabel()+"\",\""+fieldProperty.ref+"\",\""+fieldProperty.refProperty + "\")";
		} else if(type.equals("List")) {
			return "Table("+sectionName+",\""+fieldProperty.getLabel()+"\")";
		}
		return null;
	}
	
	public static String getCurrentDaoPackage(Entity entity,List<Mocha> corals) {
		for(Mocha coral : corals) {
			for(Entity e : coral.getEntityList()) {
				if(e.getEntityName().equals(entity.getEntityName())) {
					return coral.getDaoImplPackage();
				}
			}
		}
		return null;
	}
	
	public static String getCurrentEntityPackage(Entity entity,List<Mocha> corals) {
		Mocha coral = getCurrentCoral(entity, corals);
		if(coral != null) {
			return coral.getEntityPackage();
		}
		return null;
	}
	
	public static Mocha getCurrentCoral(Entity entity,List<Mocha> corals) {
		for(Mocha coral : corals) {
			for(Entity e : coral.getEntityList()) {
				if(e.getEntityName().equals(entity.getEntityName())) {
					return coral;
				}
			}
		}
		return null;
	}
	
	
}

