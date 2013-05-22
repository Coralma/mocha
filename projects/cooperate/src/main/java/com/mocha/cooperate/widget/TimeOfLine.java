package com.mocha.cooperate.widget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.Message;
import com.mocha.cooperate.widget.listener.TimeOfLineListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class TimeOfLine extends VerticalLayout implements ClickListener, ValueChangeListener {
	
	private Message message;
	private Date startDate;
	private Date endDate;
	private DateField datePickup;
	private TimeOfLineListener listener; 
	private List<VerticalLayout> timeItems = new ArrayList<VerticalLayout>();
	
	public TimeOfLine(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.addStyleName("timeofline-panel");
		this.setSpacing(true);
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		VerticalLayout nowLayout = createTimeStep("Now", DateUtils.getCurrentYear());
		nowLayout.setStyleName("time-item-selected");
		this.addComponent(nowLayout);
		
		List<Integer> months = DateUtils.getAroundMonth(startDate); 
		for(Integer month : months) {
			this.addComponent(createTimeStep(message.getString("cooperate.tl.month"+month), month));
		}
		
		List<Integer> years = DateUtils.getAroundYear(startDate, endDate);
		for(Integer year : years) {
			this.addComponent(createTimeStep(String.valueOf(year), year));
		}
		datePickup = new DateField();
		datePickup.setImmediate(true);
		datePickup.addListener(this);
		datePickup.setWidth("100px");
		datePickup.setDescription("Start Date");
		datePickup.setResolution(InlineDateField.RESOLUTION_DAY);
		this.addComponent(datePickup);
	}
	
	public VerticalLayout createTimeStep(String label, Object data) {
		VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("time-item");
		Button nowButton = new Button(label);
		nowButton.addStyleName(BaseTheme.BUTTON_LINK);
		nowButton.setData(data);
		nowButton.addListener((ClickListener)this);
		layout.addComponent(nowButton);
		layout.setData(data);
		timeItems.add(layout);
		return layout;
	}
	
	public void selectDate(Object data) {
		for(VerticalLayout item : timeItems) {
			if(item.getData().equals(data)) {
				item.setStyleName("time-item-selected");
			} else {
				item.setStyleName("time-item");
			}
		}
	}


	@Override
	public void buttonClick(ClickEvent event) {
		Integer datePickup = (Integer)event.getButton().getData();
		selectDate(datePickup);
//		if the datePickup < 13 means get the month, if it's > 13 means year.
		if(datePickup < 13) {
			dateChoose(DateUtils.getCurrentYearMonthLastDate(datePickup));
		} else {
			dateChoose(DateUtils.getYearLastDate(datePickup));
		}
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		Date date = (Date)datePickup.getValue();
		dateChoose(date);
	}
	
	private void dateChoose(Date date) {
		listener.handleDateChoose(date);
	}

	/**
	 * @return the listener
	 */
	public TimeOfLineListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(TimeOfLineListener listener) {
		this.listener = listener;
	}
	

}
