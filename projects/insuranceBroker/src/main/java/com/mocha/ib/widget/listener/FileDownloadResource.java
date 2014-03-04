/**
 * 
 */
package com.mocha.ib.widget.listener;

import java.io.File;
import java.io.FileInputStream;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;

/**
 * @author Coral.Ma
 *
 */
public class FileDownloadResource extends FileResource {

	public FileDownloadResource(File sourceFile, Application application) {
		super(sourceFile, application);
	}
	
	public DownloadStream getStream() {
        try {
            final DownloadStream ds = new DownloadStream(new FileInputStream(
            		getSourceFile()),  "application/octet-stream", getFilename());
//            ds.setParameter("Content-Disposition", "attachment; filename=" + URLEncoder.encode(getFilename(),"utf-8"));
            ds.setParameter("Content-Disposition", "attachment; filename=" + new String(getFilename().getBytes("gb2312"),"iso8859-1"));
            ds.setCacheTime(0);
            return ds;
        } catch (final Exception e) {
            return null;
        }
    }
}

