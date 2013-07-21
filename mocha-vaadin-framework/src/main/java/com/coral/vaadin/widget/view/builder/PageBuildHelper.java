package com.coral.vaadin.widget.view.builder;

import java.io.File;

import com.coral.foundation.md.model.ViewSection;
import com.coral.vaadin.widget.layout.Section;
import com.vaadin.Application;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 * 
 */
public class PageBuildHelper {

	public static VerticalLayout buildPageTitle(String title) {
		VerticalLayout titleLayout = new VerticalLayout();
		titleLayout.setHeight("23px");
		titleLayout.setStyleName("mocha-page-title");
		Label pageTitle = new Label(title);
		titleLayout.addComponent(pageTitle);
		return titleLayout;
	}

	public static Section buildTipboxSection(String sectionTitle) {
		return buildSection(sectionTitle, "tipbox");
	}

	public static Section buildSection(String sectionTitle) {
		return buildSection(sectionTitle, null);
	}

	public static Section buildSection(String sectionTitle, String style) {
		ViewSection viewSection = new ViewSection();
		viewSection.setLabel(sectionTitle);
		if (style != null) {
			viewSection.setType(style);
		}
		viewSection.setColumn(1);
		SectionBuilder builder = new SectionBuilder();
		Section section = builder.build(viewSection, null);
		return section;
	}
	
	public static Embedded buildUserPhoto(String url, Application application) {
		Embedded userPhoto;
		if (url == null) {
			userPhoto = new Embedded(null, new ThemeResource(
					"images/user_photo.jpg"));
		} else {
			File imageFile = new File(url);
			FileResource fResource = new FileResource(imageFile, application);
			userPhoto = new Embedded(null, fResource);
		}
		return userPhoto;
	}
	
	public static Resource getUserPhotoResource(String url, Application application) {
		if (url == null) {
			return new ThemeResource("images/user_photo.jpg");
		} else {
			File imageFile = new File(url);
			FileResource fResource = new FileResource(imageFile, application);
			return fResource;
		}
	}
	
	public static Embedded buildThemeUserPhoto(String url, Application application) {
		Embedded userPhoto = new Embedded(null, new ThemeResource(url));
		return userPhoto;
	}

	public static Embedded buildUserPhoto(File imageFile,
			Application application) {
		FileResource fResource = new FileResource(imageFile, application);
		Embedded userPhoto = new Embedded(null, fResource);
		return userPhoto;
	}

}
