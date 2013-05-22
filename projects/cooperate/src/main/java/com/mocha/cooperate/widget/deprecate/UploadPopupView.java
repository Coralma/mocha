/**
 * 
 */
package com.mocha.cooperate.widget.deprecate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 * @deprecated
 */
public class UploadPopupView {

	public UploadPopupView() {
	}
	
	public PopupView build() {
		PopupView popupView = new PopupView(new PopupTextField());
		popupView.setHideOnMouseOut(false);
		return popupView;
	}

	public class PopupTextField implements PopupView.Content {

		private Button upload = new Button("Upload a your local file");
		private Button select = new Button("Select a file on Mocha");
        private VerticalLayout root = new VerticalLayout();

        public PopupTextField() {
        	root.addStyleName("upload-popup-view");
        	root.setWidth("180px");
        	root.setHeight("100px");
            
            root.setSpacing(true);
//            root.setMargin(true);
            
            root.addComponent(upload);
            root.addComponent(select);
        }

        public String getMinimizedValueAsHTML() {
            return "File";
        }

        public Component getPopupComponent() {
            return root;
        }
    };
}
