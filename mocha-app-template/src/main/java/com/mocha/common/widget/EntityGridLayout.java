package com.mocha.common.widget;

import java.util.List;

import com.google.common.collect.Lists;
import com.mocha.common.widget.vo.EntityGridVO;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class EntityGridLayout extends VerticalLayout {

	public int column = 3;
	public String wholeRowWidth = "650px";
	public List<EntityGridVO> entityGridVOs = Lists.newArrayList();
	
	public EntityGridLayout() {
		this.addStyleName("entity-grid-layout");
	}
	
	public void attach() {
		int index = 0;
		boolean initField = true;
		HorizontalLayout rowLayout = null;
		for(EntityGridVO entityGridVO : entityGridVOs) {
			Component field = entityGridVO.getWidget();
			if(index == 0 || index == column || entityGridVO.isChangeLine() ||entityGridVO.isWholeRow()) {
				rowLayout = new HorizontalLayout();
				rowLayout.setStyleName("entity-field-row");
				rowLayout.setWidth("100%");
				rowLayout.setSpacing(true);
				if(index == column || entityGridVO.isChangeLine()) {
					index = 0;
				}
				if(entityGridVO.isChangeLine() && !initField) {
					rowLayout.setStyleName("entity-field-row-seperate");
				}
				if(entityGridVO.isWholeRow()) {
					field.setWidth(getWholeRowWidth());
				}
				this.addComponent(rowLayout);
			}
			rowLayout.addComponent(field);
			rowLayout.setExpandRatio(field, 1.0f);
			index++;
			initField = false;
		}
	}
	
	/**
	 * @param entityGridVOs the entityGridVOs to set
	 */
	public void addEntityGridVO(EntityGridVO entityGridVO) {
		this.entityGridVOs.add(entityGridVO);
	}
	
	
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the entityGridVOs
	 */
	public List<EntityGridVO> getEntityGridVOs() {
		return entityGridVOs;
	}

	/**
	 * @param entityGridVOs the entityGridVOs to set
	 */
	public void setEntityGridVOs(List<EntityGridVO> entityGridVOs) {
		this.entityGridVOs = entityGridVOs;
	}
	
	/**
	 * @param wholeRowWidth the wholeRowWidth to set
	 */
	public void setWholeRowWidth(String wholeRowWidth) {
		this.wholeRowWidth = wholeRowWidth;
	}

	/**
	 * @return the wholeRowWidth
	 */
	public String getWholeRowWidth() {
		return wholeRowWidth;
	}
}
