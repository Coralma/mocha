/**
 * 
 */
package com.coral.vaadin.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Resource;
import com.coral.foundation.md.model.helper.VGenHelper;
import com.coral.vaadin.template.PresenterFactory;
import com.coral.vaadin.template.TCreate;
import com.coral.vaadin.template.TCreateListener;
import com.coral.vaadin.template.TCreatePresenter;
import com.coral.vaadin.template.TSearch;
import com.coral.vaadin.template.TSearchListener;
import com.coral.vaadin.template.TSearchPresenter;
import com.coral.vaadin.template.TTable;
import com.coral.vaadin.widget.Viewer;

/**
 * @author Coral
 *
 */
public class VaadinFaceGenerator extends AbstractFaceGenerator {
	
	private String fileSperateor=System.getProperty("file.separator");

	public VaadinFaceGenerator(Resource resource) {
		super(resource);
	}
	
	public void generater() throws Exception {
		try {
			for(Mocha mocha: mochas) {
				for(Entity entity : mocha.getEntityList()) {
					generateTCreate(entity, mochas);
					generateTTable(entity, mochas);
//					generateTSearch(entity, coralList);
//					generateTCreateDialog(entity, coralList);
//					generateTSearchDialog(entity, coralList);
					
				}
			}
			generateFactory(mochas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateTCreate(Entity entity, List<Mocha> corals) throws Exception {
		TCreate tCreate = new TCreate();
		tCreate.init(entity, corals);
		String pageName = VGenHelper.genCreateViewClassName(entity.getEntityName());
		
		TCreateListener listener = new TCreateListener();
		listener.init(entity, corals);
		String listenerName = VGenHelper.genCreateListenerName(entity.getEntityName());
		
		TCreatePresenter presenter = new TCreatePresenter();
		presenter.init(entity, corals);
		String presenterName = VGenHelper.genCreatePresenterClassName(entity.getEntityName());
		
		if(VGenHelper.getCurrentView(entity, Viewer.TCreate) !=null) {
//			fileGenerater(srcGenPath, pageName, SystemConstant.TCREATE_PAGE_PKG,tCreate.generateCreateView().toString(), false);
//			fileGenerater(srcGenPath, listenerName, SystemConstant.TCREATE_LISTENER_PKG, listener.generate().toString(), false);
			fileGenerater(srcGenPath, presenterName, SystemConstant.TCREATE_PRESENTER_PKG, presenter.generate().toString(), false);
		}
	}

	public void generateTSearch(Entity entity, List<Mocha> corals) throws Exception {
		TSearch tSearch = new TSearch();
		tSearch.init(entity, corals);
		String pageName = VGenHelper.genSearchViewClassName(entity.getEntityName());

		TSearchListener searchListener = new TSearchListener();
		searchListener.init(entity, corals);
		String listenerName = VGenHelper.genSearchListenerName(entity.getEntityName());

		TSearchPresenter presenter = new TSearchPresenter();
		presenter.init(entity, corals);
		String presenterName = VGenHelper.genSearchPresenterClassName(entity.getEntityName());
		
		if(VGenHelper.getCurrentView(entity, Viewer.TSearch) !=null) {
			fileGenerater(srcGenPath, pageName, SystemConstant.TSEARCH_PAGE_PKG, tSearch.generate().toString(), false);
			fileGenerater(srcGenPath, listenerName, SystemConstant.TSEARCH_LISTENER_PKG, searchListener.generate().toString(), false);
			fileGenerater(srcGenPath, presenterName, SystemConstant.TSEARCH_PRESENTER_PKG, presenter.generate().toString(), false);
		}
	}
	
	public void generateTTable(Entity entity, List<Mocha> corals) throws Exception {
		TTable table = new TTable();
		table.init(entity, corals);
		String tableName = VGenHelper.genTableClassName(entity.getEntityName());
		if(VGenHelper.getCurrentView(entity, Viewer.TTable) !=null) {
			fileGenerater(srcGenPath, tableName, SystemConstant.TTable_PKG, table.generateTable().toString(),false);
		}
	}
	
	public void generateFactory(List<Mocha> corals) throws Exception {
		String fileName = "GeneratedPageFactory";
		PresenterFactory pageFactory = new PresenterFactory();
		pageFactory.init(corals,fileName);
		fileGenerater(srcGenPath, fileName, SystemConstant.GENERATED_PAGE, pageFactory.generate().toString(),false);
	}
	
	public void fileGenerater(String path, String className, String pkg, String classContent,boolean ignoreExisted) throws Exception {
		FileWriter fw = null;
		PrintWriter pw = null;
		String genPath ="";
		if(pkg != null) {
			genPath = path + pkg.replace(".",fileSperateor) + fileSperateor;
		}
		// These codes don't replace the generated file again after it generated.
//		if(ignoreExisted) {
//			File existedFile = new File(genPath + className + ".java");
//			if (existedFile.exists()) {
//				return;
//			}
//		}
		try{
			File bulidFile = new File(genPath);
			if (!bulidFile.exists()) {
				bulidFile.mkdirs();
			}
			
			fw = new FileWriter(genPath + className + ".java");
			pw = new PrintWriter(fw);
			pw.println(classContent);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			pw.close();
			fw.close();
		}
	}
	
}