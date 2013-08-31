package com.homepage.pages;


import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;

public class SimpleHomePage<T> extends SimpleBasePage<T> {

	/**
	 * Construct.
	 * 
	 * @param parameters
	 *            the current page parameters.
	 */
	public SimpleHomePage(PageParameters parameters) {
		super(parameters);

		Image image1 = new Image("1.jpg", new ContextRelativeResource("/images/1.jpg"));
		add(image1);

		Image image2 = new Image("2.jpg", new ContextRelativeResource("/images/2.jpg"));
		add(image2);

		Image image3 = new Image("3.jpg", new ContextRelativeResource("/images/3.jpg"));
		add(image3);

		Image image4 = new Image("4.jpg", new ContextRelativeResource("/images/4.jpg"));
		add(image4);

		Image image5 = new Image("5.jpg", new ContextRelativeResource("/images/5.jpg"));
		add(image5);

		Image image6 = new Image("6.jpg", new ContextRelativeResource("/images/6.jpg"));
		add(image6);

		buildMainSlideComponent();
	}

	private void buildMainSlideComponent() {
		
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		// response.render(JavaScriptReferenceHeaderItem.forReference(basepageJs));
		// response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
		// response.render(CssHeaderItem.forReference(basePageCss));
	}
}
