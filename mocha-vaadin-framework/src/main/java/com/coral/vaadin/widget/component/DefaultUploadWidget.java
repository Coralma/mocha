/**
 * 
 */
package com.coral.vaadin.widget.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.coral.foundation.oauth.APIKeys;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.SucceededEvent;

/**
 * @author Administrator
 * 
 */
public class DefaultUploadWidget extends CustomComponent implements Upload.SucceededListener, Upload.FailedListener, Upload.Receiver {

	private static final long serialVersionUID = -304606232015029691L;
	private File file;
	private Upload upload;
	private HorizontalLayout uploadPanel = new HorizontalLayout();
	private HorizontalLayout successInfo = new HorizontalLayout();
	private String fileName;
	private Label infoLabel;
	private Button editBtn;

	public DefaultUploadWidget() {
		upload = new Upload();
		upload.setReceiver(this);
		uploadPanel.addComponent(upload);
		// upload.setButtonCaption("Upload your file");
		// Listen for events regarding the success of upload.
		upload.addListener((Upload.SucceededListener) this);
		upload.addListener((Upload.FailedListener) this);
		// infoLabel = new Label("Upload succeeded");
		// successInfo.addComponent(infoLabel);
		// editBtn = new Button("Edit");
		// successInfo.addComponent(editBtn);

		setCompositionRoot(uploadPanel);
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		this.fileName = filename;
		FileOutputStream fos = null; // Output stream to write to
		file = new File(APIKeys.FILE_PATH + filename);
		try {
			// Open the file for writing.
			fos = new FileOutputStream(file);

		}
		catch (final java.io.FileNotFoundException e) {
			// Error while opening the file. Not reported here.
			e.printStackTrace();
			return null;
		}
		return fos; // Return the output stream to write to
	}

	@Override
	public void uploadFailed(FailedEvent event) {
		getWindow().showNotification("Upload Failed.");
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		getWindow().showNotification("Upload Succeeded.");
		// infoLabel.setValue(fileName);
		// replaceComponent(uploadPanel, successInfo);
	}

}
