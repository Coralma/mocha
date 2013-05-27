/**
 * 
 */
package com.coral.vaadin.widget.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.layout.PageLayout;
import com.coral.vaadin.widget.layout.Section;
import com.coral.vaadin.widget.listener.PresenterListener;
import com.coral.vaadin.widget.table.AbstractTable;
import com.coral.vaadin.widget.view.builder.FieldBuilder;
import com.coral.vaadin.widget.view.builder.IFieldBuilder;
import com.coral.vaadin.widget.view.builder.SectionBuilder;
import com.coral.vaadin.widget.view.builder.ViewContext;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Administrator
 *
 */
public abstract class AbstractEntityViewer extends PageLayout {

	private static final long serialVersionUID = 7204332698759984019L;
	protected ViewContext viewContext;
	protected List<Section> sections;
	protected List<AbstractTable> tables;
	protected Map<String,ComponentContainer> container = new HashMap<String, ComponentContainer>();
	protected SectionBuilder sectionBuilder = new SectionBuilder();
	
	public AbstractEntityViewer(ViewContext viewContext) {
		this(viewContext.getCurrentView().getLabel());
		this.viewContext = viewContext;
		
	}
	
	public AbstractEntityViewer(String title) {
		super(title);
		sections = new ArrayList<Section>();
		tables = new ArrayList<AbstractTable>();
	}
	
	public Section buildSection(ViewSection viewSection, ComponentContainer parent) {
		Section section = sectionBuilder.build(viewSection,parent);
		// add the init section into cache list and page.
		if(section != null) {
			sections.add(section);
			//tab style section don't need add to parent
			if(!"tab".equals(viewSection.getType())) {
				parent.addComponent(section);
			}
		}
        return section;
	}
	
	public Widget buildWidget(ViewField viewField) {
		IFieldBuilder fieldBuilder = new FieldBuilder();
		Widget widget = fieldBuilder.build(viewField);
		widgets.put(viewField.getFieldName(), widget);
		return widget;
	}
	
	public Result validate(String type) {
		// TODO Auto-generated method stub
		return new Result();
	}

	public String getViewerTitle() {
		return viewContext.getCurrentView().getLabel();
	}

	public void setListener(PresenterListener listener) {
		// TODO Auto-generated method stub
	}

	public List<AbstractTable> getTables() {
		// TODO Auto-generated method stub
		return null;
	}
}
