/**
 * 
 */
package com.coral.foundation.md.model.helper;

import java.util.List;

import com.coral.foundation.md.model.AppMenu;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.utils.StrUtils;

/**
 * @author Coral.Ma
 *
 */
public class VAppGenHelper {

	/**
	 * 'OfficeAdmin' to 'OfficeAdminPresenter'
	 * @param appName
	 * @return String
	 */
	public static String genAppMainPagePresenterClassName(String appName) {
		return generateClassName(appName) + "Presenter";
	}
	
	/**
	 * 'OfficeAdmin' to 'OfficeAdminMainPage'
	 * @param className
	 * @return String
	 */
	public static String genAppMainPageClassName(String appName) {
		return generateClassName(appName) + "MainPage";
	}
	
	/**
	 * 'OfficeAdmin' to 'OfficeAdminControllerMenuPanel'
	 * @param className
	 * @return String
	 */
	public static String genControllerMenuPanelClassName(String appName) {
		return generateClassName(appName) + "ControllerMenuPanel";
	}

	/**
	 * 'OfficeAdmin' to 'OfficeAdminFunctionPanel'
	 * @param appName
	 * @return
	 */
	public static String genFunctionPanelClassName(String appName) {
		return generateClassName(appName) + "FunctionPanel";
	}

	/**
	 * Change 'class' to 'Class'. Make first letter to UpperCase.
	 * @param className
	 * @return String
	 */
	public static String generateClassName(String className) {
		return className.substring(0, 1).toUpperCase() + className.substring(1);
	}
	
	/**
	 * Change 'class' to 'ClassCard'. Make first letter to UpperCase and add card suffix. 
	 * @param className
	 * @return String
	 */
	public static String generateCardClassName(String className) {
		String entityClassName = generateClassName(className);
		entityClassName = entityClassName + "Card";
		return entityClassName;
	}
	
	/**
	 * Return the label of xml definition. Display rule : viewField.label > Entity.property.label > Entity.property.name
	 * @param field
	 * @return String
	 */
	public static String generateFieldLabel(ViewField field) {
		try {
			if(field.getLabel() != null) {
				return field.getLabel();
			} else {
				if(field.getProperty().getLabel() != null) {
					return field.getProperty().getLabel();
				} else {
					return StrUtils.genLabel(field.getProperty().getPropertyName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Generate a flow field setter code.
	 * @param fieldStatus
	 * @param field
	 * @return String
	 */
	public static String generateFieldStatus(ViewField field) {
		String fieldStatus = "FieldStatus.create()";
		fieldStatus += ".setLabel(\"" + generateFieldLabel(field) + "\")";
		fieldStatus += ".setPath(\"" + field.getPath() + "\")";
		fieldStatus += ".setType(\"" + field.getProperty().getType() + "\")";
		if(field.isRequired()) {
			fieldStatus += ".setRequired(true)";
		}
		if(field.getStyle() != null) {
			fieldStatus += ".setStyle(\""+ field.getStyle() + "\")";
		}
		if(field.getCodeTable() != null) {
			fieldStatus += ".setCodeTableName(\"" + field.getCodeTable() + "\")";
		}
		if(field.isChangeLine()) {
			fieldStatus += ".setChangeLine(true)";
		}
		if(field.isWholeRow()) {
			fieldStatus += ".setWholeRow(true)";
		}
		fieldStatus += ";";
		return fieldStatus;
	}
	
	/**
	 * Return the label of xml definition. Display rule : viewField.label > viewField.name
	 * @param field
	 * @return String
	 */
	public static String generateMenuLabel(AppMenu menu) {
		if(menu.getLabel() != null) {
			return menu.getLabel();
		} else {
			return StrUtils.genLabel(menu.getName());
		}
	}
	
	public static String generateFunctionMenu(AppMenu menu) {
		String functionMenu = "FunctionMenu.create()";
		functionMenu += ".setName(\""+ menu.getName() +"\")";
		functionMenu += ".setLabel(\""+ generateMenuLabel(menu) +"\")";
		if(menu.getViewName() != null) {
			functionMenu += ".setViewName(\""+ menu.getViewName() +"\")";
		} else if(menu.getCustomizedClass() != null) {
			functionMenu += ".setCustomizeClass(\""+ menu.getCustomizedClass() +"\")";
		}
		return functionMenu;
	}
	
	/**
	 * Generate the action label. If the label didn't define return the name as label. 
	 * @param action
	 * @return String
	 */
	public static String generateActionLabel(ViewAction action) {
		if(action.getLabel() != null) {
			return action.getLabel();
		} else {
			return StrUtils.genLabel(action.getName());
		}
	}
	
	/**
	 * Generate the create entity view title if the view didn't define the label. 
	 * Example : EntityName Abc. The generated entity view is "Create Abc".
	 * @return
	 */
	public static String generateCreateEntityViewTitle(View view) {
		if(view.getLabel() != null) {
			return view.getLabel(); 
		} else {
			return "Create " + StrUtils.genLabel(view.getEntity().getEntityName());
		}
	}
	
	/**
	 * Generate the Search entity view title if the view didn't define the label. 
	 * Example : EntityName Abc. The generated entity view is "Search Abc".
	 * @return
	 */
	public static String generateSearchEntityViewTitle(View view) {
		if(view.getLabel() != null) {
			return view.getLabel(); 
		} else {
			return "Search " + StrUtils.genLabel(view.getEntity().getEntityName());
		}
	}
	
	public static String asVariable(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}
	
	public static String getEditViewName(View viewer, List<Mocha> mochas) {
		String entityName = viewer.getEntity().getEntityName();
		if(entityName == null) {
			throw new RuntimeException("[Error] EntityName of " + viewer.getName() + " can not be null!");
		}
		for(Mocha mocha : mochas) {
			for(View view : mocha.getViewList()) {
				if(entityName.equals(view.getEntity().getEntityName()) && "EntityEditView".equals(view.getTemplate())) {
					return view.getName();
				}
			}
		}
		throw new RuntimeException("[Error] Can not find the SearchView of " + viewer.getName() + ", Please check your definition!");
	}
	
	public static String getSearchViewName(View viewer, List<Mocha> mochas) {
		String entityName = viewer.getEntity().getEntityName();
		if(entityName == null) {
			throw new RuntimeException("[Error] EntityName of " + viewer.getName() + " can not be null!");
		}
		for(Mocha mocha : mochas) {
			for(View view : mocha.getViewList()) {
				if(entityName.equals(view.getEntity().getEntityName()) && "EntityCardSearch".equals(view.getTemplate())) {
					return view.getName();
				}
			}
		}
		throw new RuntimeException("[Error] Can not find the SearchView of " + viewer.getName() + ", Please check your definition!");
	}
}
