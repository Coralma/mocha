/**
 * 
 */
package com.mocha.cooperate.widget.deprecate;

import com.coral.vaadin.widget.field.StringField;
import com.coral.vaadin.widget.field.StringRichField;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.widget.FunctionButtonLayout;
import com.mocha.cooperate.widget.NotifyTokenField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
@Deprecated
public class DocumentEditWidget extends VerticalLayout {
	
	private String docwidth ="630px";
	private String docHeight ="350px";
	
	private FormLayout docContentLayout = new FormLayout();
	private TextField titleField = new TextField("Title");
	private RichTextArea contentField = new RichTextArea("Content");
//	private StringField titleField = new StringField("Title");
//	private StringRichField contentField =new StringRichField("Content");
//	private CheckBox isPublic = new CheckBox("Public your work document.");

	public DocumentEditWidget() {
		this.setSpacing(true);
		build();
	}
	
	public void build() {
		VerticalLayout docLayout = PageBuildHelper.buildPageTitle("A New Document");
		addComponent(docLayout);
		
		docContentLayout.setMargin(true);
		docContentLayout.setWidth("99%");
		docContentLayout.setSpacing(true);
//		docContentLayout.addStyleName("mocha-box-layout");

//		titleField.setFieldWidth(docwidth);
		titleField.setWidth(docwidth);
		docContentLayout.addComponent(titleField);
		
//		isPublic.addStyleName("doc-checkbox");
//		docContentLayout.addComponent(isPublic);
//		contentField.setFieldWidth(docwidth);
//		contentField.setFieldHeight(docHeight);
		contentField.setWidth(docwidth);
		contentField.setHeight(docHeight);
		docContentLayout.addComponent(contentField);
		docContentLayout.addComponent(buildNotifyTokenField());
		docContentLayout.addComponent(buildAuthorityCombo());
		addComponent(docContentLayout);
		
		Button saveButton = new Button("Save Document");
		Button cancelButton = new Button("cancel");
		FunctionButtonLayout buttonLayout = new FunctionButtonLayout();
		buttonLayout.addButton(saveButton);
		buttonLayout.addButton(cancelButton);
		addComponent(buttonLayout);
	}
	
	public ComboBox buildAuthorityCombo() {
		ComboBox comboBox = new ComboBox();
		comboBox.setCaption("Authority");
//		comboBox.addStyleName("doc-checkbox");
		comboBox.addItem("Public");
		comboBox.addItem("Private");
		return comboBox;
	}
	
	public NotifyTokenField buildNotifyTokenField() {
		NotifyTokenField tokenField = new NotifyTokenField();
		tokenField.setCaption("Notify");
//		tokenField.setInputPrompt("Notificate other users..");
		tokenField.setWidth(docwidth);
//		tokenField.addStyleName("doc-checkbox");
		return tokenField;
	}
}
