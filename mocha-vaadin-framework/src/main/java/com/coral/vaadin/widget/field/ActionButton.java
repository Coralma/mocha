package com.coral.vaadin.widget.field;

import com.vaadin.ui.Button;

public class ActionButton extends Button {

	public Object data;
	public ActionButton() {
       super();
    }

    public ActionButton(String caption) {
    	super(caption);
    }
    
    public ActionButton(String caption, Object data) {
    	super(caption);
    	this.data = data;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
