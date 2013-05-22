/**
 * 
 */
package com.coral.vaadin.widget.field;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Administrator
 *
 */
public class StringUploadField extends AbstractField implements Field,Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {

	private static final long serialVersionUID = -8142790346061175420L;
	private HorizontalLayout successInfo = new HorizontalLayout();
	private String fileName;
	private String filePath;
	private Label infoLabel;
	private Button editBtn;
	private File file;
	
	Upload upload;
	public StringUploadField(String label) {
		super(label);
		upload = new Upload(null,this);
		upload.setButtonCaption("Upload file");
		upload.setImmediate(true);
		upload.setWidth(fieldWidth);
		upload.addListener((Upload.SucceededListener) this);
		upload.addListener((Upload.FailedListener) this);
		
		infoLabel = new Label("Upload succeeded");
		successInfo.setSpacing(true);
		successInfo.addComponent(infoLabel);
		editBtn = new Button("Edit");
		editBtn.setStyleName(BaseTheme.BUTTON_LINK);
		editBtn.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				replaceComponent(successInfo,upload);
			}
		});
		successInfo.addComponent(editBtn);
		
		addComponent(upload);
	}

	public Object getValue() {
		return filePath;
	}

	public void setValue(Object t) {
		filePath = (String)t;
	}

	@Override
	public Field getField() {
		return this;
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((TextField)field).setComponentError(new UserError(errorMsg));
		} else {
			((TextField)field).setComponentError(null);
		}
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null; // Output stream to write to
		file = new File("C:/upload/" + filename);
		try {
			// Open the file for writing.
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			// Error while opening the file. Not reported here.
			e.printStackTrace();
			return null;
		}
		this.fileName = filename;
		this.filePath = file.getPath();
		return fos;
	}


	@Override
	public void uploadFailed(FailedEvent event) {
		getWindow().showNotification("Upload Failed.");
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		getWindow().showNotification("Upload Succeeded.");
		infoLabel.setValue(fileName);
		replaceComponent(upload, successInfo);
	}
}
