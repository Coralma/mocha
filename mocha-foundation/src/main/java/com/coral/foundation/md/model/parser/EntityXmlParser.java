/**
 * 
 */
package com.coral.foundation.md.model.parser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coral.foundation.core.impl.Serialize;
import com.coral.foundation.md.model.App;
import com.coral.foundation.md.model.AppCreation;
import com.coral.foundation.md.model.AppMenu;
import com.coral.foundation.md.model.AppMenuGroup;
import com.coral.foundation.md.model.AppNavigation;
import com.coral.foundation.md.model.CodeTable;
import com.coral.foundation.md.model.CodeTableValue;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Menu;
import com.coral.foundation.md.model.MobileMenu;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.ReportColumnDef;
import com.coral.foundation.md.model.ReportDef;
import com.coral.foundation.md.model.ReportJoinDef;
import com.coral.foundation.md.model.ReportTableDef;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewAction;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.utils.StrUtils;
import com.google.common.collect.Lists;

/**
 * <p>
 * Title: com.ebao.gs.tools.generater.MappingXmlParser.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: eBaoTech Co., Ltd.
 * </p>
 * <p>
 * Create Time: 2009 11:49:39
 * </p>
 * 
 * @author coral.ma
 *         <p>
 *         Update Time:
 *         </p>
 *         <p>
 *         Updater:
 *         </p>
 *         <p>
 *         Update Comments:
 *         </p>
 */
@SuppressWarnings("unchecked")
public class EntityXmlParser {
	
	public List<Mocha> mochas = Lists.newArrayList();

	/**
	 * Parse Mapping Xml to generate a mapping model.
	 * 
	 * @return Mapping
	 * @throws Exception
	 */
	public List<Mocha> parseMappingXml(Resource resource) throws Exception {
		List<Mocha> corals = toObject(resource);
		serializeObject(corals, resource);
		return corals;
	}

	public void serializeObject(List<Mocha> corals, Resource resource) throws Exception {
		File bulidFile = new File(resource.getEntityGenPath());
		if (!bulidFile.exists()) {
			bulidFile.mkdirs();
		}
		String fileName = "/cmodel.xml";
		if(resource.getComponentName() != null) {
			fileName = "/"+ resource.getComponentName() + "Model.xml";
		}
		FileWriter fw = new FileWriter(resource.getEntityGenPath() + fileName);
		// PrintWriter pw = new PrintWriter(fw);
		Serialize.serialize(corals, fw);
	}

	public List<Mocha> toObject(Resource resource) throws Exception {
		List<String> mappingXMLs = resource.getEntityXMLPaths();
		for (String mappingXml : mappingXMLs) {
			File configFile = new File(mappingXml).getAbsoluteFile();
			if (!configFile.exists()) {
				throw new Exception("Mapping XML does not exist!");
			}
			SAXReader reader = new SAXReader();
			Document document = reader.read(configFile);
			List<Element> root = document.selectNodes("mocha");
			Element coralRoot = root.get(0);
			Mocha mocha = parseMocha(coralRoot);
			mochas.add(mocha);
			// parse the app
			mocha.setApps(parseApp(coralRoot));
			// parse the view 
			mocha.setViewList(parseView(coralRoot));
			// parse the menu
			mocha.setMenuList(createMenu(coralRoot.elements("menu"), null));
			// parse the report
			mocha.setReportDefList(createReportDef(coralRoot.elements("report")));
			//TODO Mobile menu.
//			mocha.setMobileMenuList(createMobileMenu(coralRoot.elements("mobileMenu"), null));
		}
		return mochas;
	}
	
	public Mocha parseMocha(Element coralRoot) throws Exception {
		Mocha mocha = new Mocha();
		mocha.setName(coralRoot.attributeValue("name"));
		mocha.setEntityPackage(coralRoot.attributeValue("entityPackage"));
		mocha.setDaoIntfPackage(coralRoot.attributeValue("daoIntfPackage"));
		mocha.setDaoImplPackage(coralRoot.attributeValue("daoImplPackage"));
		mocha.setServicePackage(coralRoot.attributeValue("servicePackage"));
		
		// code table
//		List<Element> codeTableNodeList = coralRoot.elements("codeTable");
//		List<CodeTable> codeTableList = new ArrayList<CodeTable>();
//		for (Element codeTableNode : codeTableNodeList) {
//			CodeTable codeTable = new CodeTable();
//			codeTable.setName(codeTableNode.attributeValue("name"));
			
//			codeTable.setType(codeTableNode.attributeValue("type"));
//			codeTable.setData(codeTableNode.getText());
//			codeTableList.add(codeTable);
//		}
		List<CodeTable> codeTableList = parseCodeTable(coralRoot);
		mocha.setCodeTableList(codeTableList);
		
		// entity
		List<Element> handlerNodeList = coralRoot.elements("entity");
		List<Entity> entityList = new ArrayList<Entity>();

		for (Element handlerNode : handlerNodeList) {
			Entity entity = new Entity();
			entity.setEntityName(handlerNode.attributeValue("entityName"));
			entity.setTableName(handlerNode.attributeValue("tableName"));
			entity.setEntityClass(mocha.getEntityPackage() + "." + handlerNode.attributeValue("entityName"));
			String needViewStr = handlerNode.attributeValue("needView");
			if (needViewStr != null) {
				entity.setNeedView(Boolean.parseBoolean(needViewStr));
			}

			List<Element> attributeList = handlerNode.elements("property");
			for (Element attributeNode : attributeList) {
				Property prop = new Property();
				String name = attributeNode.attributeValue("propertyName");
				if (name == null) {
					name = attributeNode.attributeValue("name");
				}
				prop.setPropertyName(name);
				prop.setColumnName(attributeNode.attributeValue("columnName"));
				prop.setLabel(attributeNode.attributeValue("label"));
				prop.setDefaultValue(attributeNode.attributeValue("default"));
				
				String type = attributeNode.attributeValue("type");
				prop.setType(type == null? "String" : type);
				String codeTable = attributeNode.attributeValue("code");
				if(codeTable !=null && isExistedCode(codeTableList, codeTable)) {
					prop.setCode(codeTable);
				}
				String required = attributeNode.attributeValue("required");
				if(!StrUtils.isEmpty(required)) {
					prop.setRequired(Boolean.parseBoolean(required));
				}
				String ignore = attributeNode.attributeValue("ignore");
				if(!StrUtils.isEmpty(ignore)) {
					prop.setIgnore(Boolean.parseBoolean(ignore));
				}
				String notnull = attributeNode.attributeValue("notnull");
				if(!StrUtils.isEmpty(notnull)) {
					prop.setNotnull(Boolean.parseBoolean(notnull));
				}
				prop.setMappedBy(attributeNode.attributeValue("mappedBy"));
				String refProperty = attributeNode.attributeValue("refProperty");
				prop.setRefProperty(refProperty);
				prop.setLength(attributeNode.attributeValue("length"));
				prop.setRef(attributeNode.attributeValue("ref"));
				prop.setStyle(attributeNode.attributeValue("style"));
				prop.setSize(attributeNode.attributeValue("size"));
				prop.setOrm(attributeNode.attributeValue("orm"));
				String refer = attributeNode.attributeValue("refer");
				if(!StrUtils.isEmpty(refer)) {
					prop.setRefer(Boolean.parseBoolean(refer));
				}
				entity.addProperty(prop);
			}
			entity.setMocha(mocha);
			entityList.add(entity);
		}
		mocha.setEntityList(entityList);
		return mocha;
	}
	
	public List<CodeTable> parseCodeTable(Element coralRoot) throws Exception {
		List<Element> codeTableNodeList = coralRoot.elements("codeTable");
		List<CodeTable> codeTableList = new ArrayList<CodeTable>();
		for (Element codeTableNode : codeTableNodeList) {
			CodeTable codeTable = new CodeTable();
			codeTable.setName(codeTableNode.attributeValue("name"));
			codeTable.setIds(codeTableNode.attributeValue("ids"));
			List<Element> valueList = codeTableNode.elements("values");
			for(Element valueNode : valueList) {
				CodeTableValue codeTableValue = new CodeTableValue();
				codeTableValue.setCodeTableName(codeTable.getName());
				codeTableValue.setLanguage(valueNode.attributeValue("language"));
				codeTableValue.setDatas(valueNode.getText());
				codeTable.addValue(codeTableValue);
			}
			codeTableList.add(codeTable);
		}
		return codeTableList;
	}
	
	public List<App> parseApp(Element coralRoot) throws Exception {
		List<App> apps = Lists.newArrayList();
		List<Element> appElementList = coralRoot.elements("app");
		for(Element appElement : appElementList) {
			App app = new App();
			app.setName(appElement.attributeValue("name"));
			app.setLabel(appElement.attributeValue("label"));
//			app.setPkg(appElement.attributeValue("pkg"));
			AppNavigation appNavigation = new AppNavigation();
			app.setAppNavigation(appNavigation);
			List<Element> navigationElementList = appElement.elements("navigation");
			for(Element navigationElement : navigationElementList) {
				List<Element> mainMenuElementList = navigationElement.elements("menu"); 
				for(Element mainMenu : mainMenuElementList) {
//					AppMenu appMenu = new AppMenu();
//					appMenu.setName(mainMenu.attributeValue("name"));
//					appMenu.setLabel(mainMenu.attributeValue("label"));
//					appMenu.setViewName(mainMenu.attributeValue("viewName"));
//					appMenu.setCustomizedClass(mainMenu.attributeValue("customizedClass"));
//					appNavigation.addAppMenu(appMenu);
					appNavigation.addAppMenu(parseAppMenu(mainMenu));
				}
				List<Element> mainMenuGroupElementList = navigationElement.elements("group");
				for(Element group : mainMenuGroupElementList) {
					AppMenuGroup menuGroup = new AppMenuGroup();
					menuGroup.setLabel(group.attributeValue("label"));
					List<Element> submainMenuElementList = group.elements("menu"); 
					for(Element mainMenu : submainMenuElementList) {
//						AppMenu appMenu = new AppMenu();
//						appMenu.setName(mainMenu.attributeValue("name"));
//						appMenu.setLabel(mainMenu.attributeValue("label"));
//						appMenu.setViewName(mainMenu.attributeValue("viewName"));
//						appMenu.setCustomizedClass(mainMenu.attributeValue("customizedClass"));
//						menuGroup.addAppMenu(appMenu);
						menuGroup.addAppMenu(parseAppMenu(mainMenu));
					}
					appNavigation.addAppMenuGroup(menuGroup);
				}
			}
			AppCreation appCreation = new AppCreation();
			app.setAppCreation(appCreation);
			List<Element> creationElementList = appElement.elements("creation");
			for(Element creationElement : creationElementList) {
				List<Element> mainMenuElementList = creationElement.elements("menu");
				for(Element mainMenu : mainMenuElementList) {
					appCreation.addAppMenu(parseAppMenu(mainMenu));
				}
			}
			apps.add(app);
		}
		return apps;
	}
	
	public AppMenu parseAppMenu(Element mainMenu) {
		AppMenu appMenu = new AppMenu();
		appMenu.setName(mainMenu.attributeValue("name"));
		appMenu.setLabel(mainMenu.attributeValue("label"));
		appMenu.setViewName(mainMenu.attributeValue("viewName"));
		appMenu.setCustomizedClass(mainMenu.attributeValue("customizedClass"));
		return appMenu;
	}
	
	public List<View> parseView(Element coralRoot) throws Exception {
		List<View> views = new ArrayList<View>();
		List<Element> viewList = coralRoot.elements("view");
		for(Element viewNode : viewList) {
			String entityName = viewNode.attributeValue("entity");
			List<Element> sectionList = viewNode.elements("section");
			// loop to create view section.
			List<ViewSection> viewSections = createViewSections(sectionList, findEntity(entityName));
			// loop to create view action
			List<Element> actionList = viewNode.elements("action");
			List<ViewAction> viewActions = createviewAction(actionList);
			
			String templateValue = viewNode.attributeValue("template");
			String label = viewNode.attributeValue("label");
			String name = viewNode.attributeValue("name");
			String type = viewNode.attributeValue("type");
			String dialogSize = viewNode.attributeValue("dialogSize");
			String rootViewer = viewNode.attributeValue("root");
			String[] templates = templateValue.split(",");
			
			for(String template : templates) {
				View view = new View();
				view.setTemplate(template);
				if(dialogSize != null) {
					view.setDialogSize(StrUtils.cleanString(dialogSize));
				}
				view.setEntity(findEntity(entityName));
				view.setName(name);
				view.setLabel(label);
				view.setType(type);
				view.setSections(viewSections);
				view.setRoot(rootViewer == null? false : Boolean.parseBoolean(rootViewer));
				view.setViewActions(viewActions);
				views.add(view);
			}
		}
		return views;
	}
	
	public Property getEntityProperty(Entity entity, String fieldName, String fieldPath) {
		if(fieldName.equals("customer.name")) {
			System.out.println("fieldName");
		}
		for(Property property : entity.getProperties()) {
			if(fieldName.equals(property.getPropertyName())) {
				return property;
			}
		}
		return null;
	}
	public List<ViewSection> createViewSections(List<Element> sectionList, Entity entity) throws Exception {
		List<ViewSection> viewSections = new ArrayList<ViewSection>();
		for(Element sectionNode : sectionList) {
			ViewSection viewSection = new ViewSection();
			viewSection.setStyle(sectionNode.attributeValue("style"));
			viewSection.setGroup(sectionNode.attributeValue("group"));
			viewSection.setLabel(sectionNode.attributeValue("label"));
			viewSection.setName(sectionNode.attributeValue("name"));
			viewSection.setIcon(sectionNode.attributeValue("icon"));
			viewSection.setViewer(sectionNode.attributeValue("viewer"));
			viewSection.setData(sectionNode.attributeValue("data"));
			viewSection.setType(sectionNode.attributeValue("type"));
			viewSection.setTemplate(sectionNode.attributeValue("template"));
			
			String column = sectionNode.attributeValue("column");
			if(column != null) {
				int columnValue = Integer.parseInt(column);
				if(columnValue > 3) {
					throw new Exception("\""+viewSection.getName() + "\" column definition can not large than 3.");
				}
				viewSection.setColumn(columnValue);
			}
			
			List<ViewField> viewFields = Lists.newArrayList();
			List<Element> fieldList = sectionNode.elements("field");
			for(Element field : fieldList) {
				ViewField viewField = new ViewField();
				viewField.setFieldName(field.attributeValue("name"));
				viewField.setLabel(field.attributeValue("label"));
				viewField.setPath(field.attributeValue("path"));
				viewField.setExpression(field.attributeValue("expression"));
				viewField.setCodeTable(field.attributeValue("codeTable"));
				viewField.setStyle(field.attributeValue("style"));
				String changeLine = field.attributeValue("changeLine");
				if(changeLine != null && "true".equals(changeLine)) {
					viewField.setChangeLine(true);
				}
				String wholeRow = field.attributeValue("wholeRow");
				if(wholeRow != null && "true".equals(wholeRow)) {
					viewField.setWholeRow(true);
				}
				String required = field.attributeValue("required");
				if(required != null && "true".equals(required)) {
					viewField.setRequired(true);
				}
				viewField.setProperty(getEntityProperty(entity, viewField.getFieldName(), viewField.getPath()));
				viewFields.add(viewField);
			}
			viewSection.setViewFields(viewFields);
			
			// loop to create view action
			List<Element> actionList = sectionNode.elements("action");
			List<ViewAction> viewActions = createviewAction(actionList);
			viewSection.setViewActions(viewActions);
			viewSections.add(viewSection);
		}
		return viewSections;
	}
	
	public List<ViewAction> createviewAction(List<Element> actionList) {
		List<ViewAction> viewActions = Lists.newArrayList();
		for(Element actionNode : actionList) {
			ViewAction viewAction = new ViewAction();
			viewAction.setName(actionNode.attributeValue("name"));
			viewAction.setLabel(actionNode.attributeValue("label"));
			viewAction.setAction(actionNode.attributeValue("action"));
			viewActions.add(viewAction);
		}
		return viewActions;
	}
	
	private List<String> getActionDefinition(String action) {
		String[] actionArray = action.split(",");
		List<String> actionList = new ArrayList<String>();
		for(String act : actionArray) {
			actionList.add(act);
		}
		return actionList;
	}
	
	private Property getRelatedProperty(Entity entity, String fieldName) throws Exception {
		for(Property property : entity.getProperties()) {
			if(property.getPropertyName().equals(fieldName)) {
				return property;
			}
		}
		throw new Exception("The " + fieldName + " field don't have related property. Please check whether it was defined.");
	}
	
	public List<Menu> createMenu(List<Element> menuNodeList, Menu parent) {
		List<Menu> menuList = new ArrayList<Menu>();

		for (Element menuNode : menuNodeList) {
			Menu menu = new Menu();
			menu.setName(menuNode.attributeValue("name"));
			menu.setLabel(menuNode.attributeValue("label"));
			menu.setPage(menuNode.attributeValue("page"));
			menu.setParentMenu(null);
			List<Element> subMenuList = menuNode.elements("menu");
			if(subMenuList.size() > 0) {
				for(Element subMenu : subMenuList) {
					menu.setSubMenus(createMenu(subMenuList,menu));
				}
			}
			menuList.add(menu);
		}
		return menuList;
	}
	
	public List<MobileMenu> createMobileMenu(List<Element> mobileMenuNodeList, MobileMenu parent) {
		List<MobileMenu> menuList = new ArrayList<MobileMenu>();

		for (Element menuNode : mobileMenuNodeList) {
			MobileMenu menu = new MobileMenu();
			menu.setName(menuNode.attributeValue("name"));
			menu.setLabel(menuNode.attributeValue("label"));
			menu.setPage(menuNode.attributeValue("page"));
			menu.setParentMenu(null);
			List<Element> subMenuList = menuNode.elements("mobileMenu");
			if(subMenuList.size() > 0) {
				for(Element subMenu : subMenuList) {
					menu.setSubMenus(createMobileMenu(subMenuList,menu));
				}
			}
			menuList.add(menu);
		}
		return menuList;
	}
	
	/** report */
	public List<ReportDef> createReportDef(List<Element> reportNodeList) throws Exception {
		List<ReportDef> reportDefs = Lists.newArrayList();
		for (Element reportNode : reportNodeList) {
			ReportDef reportDef = new ReportDef();
			reportDefs.add(reportDef);
			reportDef.setName(reportNode.attributeValue("name"));
			List<Element> reportTableNodeList = reportNode.elements("reportTable");
			// set reportTable
			for(Element reportTable : reportTableNodeList) {
				ReportTableDef reportTableDef = new ReportTableDef();
				reportDef.addReportTable(reportTableDef);
				String entityName = reportTable.attributeValue("entity");
				Entity entity = findEntity(entityName);
				reportTableDef.setEntity(entity);
				reportTableDef.setName(StrUtils.lowCaseFirstLetter(entityName));
				reportTableDef.setLabel(StrUtils.genLabel(entityName));
				reportTableDef.setTableName("T_" + StrUtils.genDBName(entityName));

				// set column				
				String columnValue = reportTable.element("columns").getText();
				String[] cols = columnValue.split(",");
				for(String col : cols) {
					String column = col.trim();
					ReportColumnDef columnDef = new ReportColumnDef();
					reportTableDef.addColumn(columnDef);
					columnDef.setName(column);
					columnDef.setColumnName(StrUtils.genDBName(column));
					Property property = findProperty(entity, column);
					if(property.getLabel() != null) {
						columnDef.setLabel(property.getLabel());
					} else {
						columnDef.setLabel(StrUtils.genLabel(column));
					}
				}
				
				// set Join def
				String joinEntitys = reportTable.element("joinEntitys").getText();
				String[] jes = joinEntitys.split(",");
				for(String je : jes) {
					String joinEntity = je.trim();
					ReportJoinDef reportJoinDef = new ReportJoinDef();
					reportTableDef.addJoinDefs(reportJoinDef);
					reportJoinDef.setName(StrUtils.lowCaseFirstLetter(joinEntity));
				}
			}
		}
		return reportDefs;
	}
	
	
	/***************************************************************************************************/
    /*																								   */	
	/*									Validation Function											   */
	/*																								   */
	/***************************************************************************************************/
	private boolean isExistedCode(List<CodeTable> codeTableList, String codeTableName) throws Exception {
		for(CodeTable codeTable : codeTableList) {
			if(codeTable.getName().equals(codeTableName)) {
				return true;
			}
		}
		throw new Exception("The code \""+ codeTableName + "\" didn't define in <codeTable> tag. Please check the definition XML");
	}

	private Entity findEntity(String entityName) throws Exception {
		for(Mocha mocha : mochas) {
			for(Entity entity : mocha.getEntityList()) {
				if(entityName.equals(entity.getEntityName())) {
					return entity;
				}
			}
		}
		throw new Exception(entityName + "didn't existed, Please check your configuration file.");
	}
	
	private Property findProperty(Entity entity, String propertyName) throws Exception {
		for(Property property : entity.getProperties()) {
			if(property.getPropertyName().equals(propertyName)) {
				return property;
			}
		}
		throw new Exception(propertyName + "didn't existed in entity "+ entity.getEntityName() +", Please check your configuration file.");
	}
}
