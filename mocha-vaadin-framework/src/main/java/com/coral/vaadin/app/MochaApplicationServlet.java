/**
 * 
 */
package com.coral.vaadin.app;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationServlet;
import com.vaadin.ui.Window;

/**
 * @author Coral
 * 
 */
public class MochaApplicationServlet extends ApplicationServlet {
	


	@Override
	protected void writeAjaxPageHtmlVaadinScripts(Window window, String themeName, Application application, BufferedWriter page, String appUrl,
			String themeUri, String appId, HttpServletRequest request) throws ServletException, IOException {
		page.write("<script type=\"text/javascript\">\n");
		page.write("//<![CDATA[\n");
		page.write("document.write(\"<script language='javascript' src='" + request.getContextPath() + "/VAADIN/jquery/jquery-1.4.4.min.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript' src='" + request.getContextPath() + "/VAADIN/js/highcharts.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript' src='" + request.getContextPath() + "/VAADIN/js/modules/exporting.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript' src='" + request.getContextPath() + "/VAADIN/jquery/jquery.dropdown.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript' src='" + request.getContextPath() + "/VAADIN/js/modules/modernizr.custom.63321.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript' src='http://tinymce.cachefly.net/4.0/tinymce.min.js'><\\/script>\");\n");
		page.write("document.write(\"<script language='javascript'>tinymce.init({selector:'textarea'});<\\/script>\");\n");
		page.write("//]]>\n</script>\n");
		super.writeAjaxPageHtmlVaadinScripts(window, themeName, application, page, appUrl, themeUri, appId, request);
	}
	

}
