package com.mocha.mobile.webapp;

import com.coral.vaadin.resource.ModelCenter;
import com.google.common.eventbus.EventBus;
import com.mocha.mobile.ui.PadApplication;
import com.mocha.mobile.ui.PhoneApplication;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("serial")
public abstract class MobileApplication extends TouchKitApplication {

    static CustomizedSystemMessages customizedSystemMessages = new CustomizedSystemMessages();
    private MobileWindow mobileWindow = new MobileWindow();
    
    static {
        // reload on session expired
        customizedSystemMessages.setSessionExpiredCaption(null);
        customizedSystemMessages.setSessionExpiredMessage(null);
    }

    public static SystemMessages getSystemMessages() {
        return customizedSystemMessages;
    }

    @Override
    public void init() {
    	// init model
//		ModelCenter.getModel(getModel());
		
    	mobileWindow.setApplicationCaption(getApplicationCapture());
        setMainWindow(mobileWindow);
        
        // Using mobile mail theme
        setTheme("mobilemail");

        getMainWindow().addApplicationIcon(
                getURL() + "VAADIN/themes/mobilemail/apple-touch-icon.png");
    }    
    
    @Override
    public void onBrowserDetailsReady() {
        if (isSmallScreenDevice()) {
            getMainWindow().setContent(new PhoneApplication());
        } else {
            getMainWindow().setContent(new PadApplication());
        }
    }

    public boolean isSmallScreenDevice() {
        float viewPortWidth = getMainWindow().getWidth();
        return viewPortWidth < 700;
    }
    
    public abstract String getApplicationCapture(); 
//    public abstract String getModel();
}
