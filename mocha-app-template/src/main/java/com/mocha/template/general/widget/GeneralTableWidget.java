package com.mocha.template.general.widget;

import java.util.Date;
import java.util.List;

import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.ReflectionUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GeneralTableWidget extends AbstractGeneralWidget {
	
	protected GeneralTableVO generalTableVO;
	protected GeneralTableListener tableListener;

	public GeneralTableWidget(GeneralTableVO generalTableVO, GeneralTableListener tableListener) {
		this.generalTableVO = generalTableVO;
		this.tableListener = tableListener;
	}

	public void attach() {
		// table caption
		VerticalLayout tableCaption = buildSectionHead(generalTableVO.getCaption());
		this.addComponent(tableCaption);
		
		VerticalLayout sectionTable = buildSectionTable();
		sectionTable.setWidth(left_content_width);
		this.addComponent(sectionTable);
	}
	
	public VerticalLayout buildSectionTable() {
		List<String> heads = generalTableVO.getTableHeadLabels();
		List<String> variables = generalTableVO.getTableHeadVariables();
		List<Object> datas = generalTableVO.getDatas();

		VerticalLayout table = new VerticalLayout();
		table.setWidth(left_content_width);
		table.addStyleName("app-section-content");
		
		if(heads.size() > 0) {
			HorizontalLayout tableHeadLayout = new HorizontalLayout();
			tableHeadLayout.addStyleName("app-section-table");
			tableHeadLayout.setWidth(left_content_width);
			for(String head : heads) {
				Label leaveTypeHead = WidgetFactory.createLabel(head);
				tableHeadLayout.addComponent(leaveTypeHead);
			}
			table.addComponent(tableHeadLayout);
		}

		for(Object data : datas) {
			HorizontalLayout tableDataLayout = new HorizontalLayout();
			tableDataLayout.setWidth(left_content_width);
			for(String variable : variables) {
				Object value = ReflectionUtils.getVariableValue(data, variable);
				String strValue = "";
				if(value != null) {
					if(value instanceof Date) {
						strValue = DateUtils.date2String((Date)value, DateUtils.dayFormat);
					} else {
						strValue = value.toString();
					}
				}
				Label dataText = WidgetFactory.createLabel(strValue);
				tableDataLayout.addComponent(dataText);
			}
			table.addComponent(tableDataLayout);
		}
		return table;
	}
}
