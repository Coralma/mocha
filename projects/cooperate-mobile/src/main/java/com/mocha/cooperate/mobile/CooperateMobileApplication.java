/**
 * 
 */
package com.mocha.cooperate.mobile;

import com.mocha.cooperate.mobile.pad.PadLoginView;
import com.mocha.cooperate.mobile.phone.PhoneLoginView;
import com.mocha.mobile.webapp.MobileApplication;

/**
 * @author Coral
 *
 */
public class CooperateMobileApplication extends MobileApplication {

	@Override
    public void init() {
		super.init();
		setTheme("mobile");
	}
	
    @Override
    public void onBrowserDetailsReady() {
//    	String type = MobileConstants.PAD;
//    	if (isSmallScreenDevice()) {
//        	type = MobileConstants.PHONE;
//        }
    	String type = MobileConstants.PHONE;
    	getMainWindow().setContent(new MobileContentDelegate(type));
//        if (isSmallScreenDevice()) {
//        	getMainWindow().setContent(new PhoneLoginView());
//        } else {
//        	getMainWindow().setContent(new PadLoginView());
//        }
    }

	@Override
	public String getApplicationCapture() {
		return "Mocha Platform";
	}

}
