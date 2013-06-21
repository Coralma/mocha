package com.mocha.mobile.webapp;

import com.vaadin.addon.touchkit.ui.TouchKitWindow;

public class MobileWindow extends TouchKitWindow {

    private static final long serialVersionUID = 1L;

    public MobileWindow() {
        setCaption("Mobile Application");
        setImmediate(true);
    }
    
    public void setApplicationCaption(String caption) {
    	if(caption != null) {
    		setCaption(caption);
    	}
    }

}
