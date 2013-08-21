package com.homepage.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.string.StringValue;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.INavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.button.DropDownAutoOpen;
//import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
import de.agilecoders.wicket.themes.markup.html.metro.MetroTheme;

/**
 * Base wicket-bootstrap {@link org.apache.wicket.Page}
 * 
 * 
 */
abstract class SimpleBasePage<T> extends GenericWebPage<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final CssResourceReference basePageCss = new CssResourceReference(BasePage.class, "basepage.css");
	private static final JavaScriptResourceReference basepageJs = new JavaScriptResourceReference(BasePage.class, "unslider.js");

	/*
	 * Colin Slides Shows
	 */
	private static final CssResourceReference carouselCss = new CssResourceReference(BasePage.class, "full.css");

	/*
	 * boostarp-image-gallery
	 */
	private static final CssResourceReference bootstrapImageGalleryCss = new CssResourceReference(BasePage.class, "bootstrap-image-gallery.css");
	private static final JavaScriptResourceReference loadImageJs = new JavaScriptResourceReference(BasePage.class, "load-image.js");
	private static final JavaScriptResourceReference bootstrapImageGalleryJs = new JavaScriptResourceReference(BasePage.class, "bootstrap-image-gallery.js");
	private static final JavaScriptResourceReference mainJs = new JavaScriptResourceReference(BasePage.class, "main.js");

	/**
	 * Construct.
	 * 
	 * @param parameters
	 *            current page parameters
	 */
	public SimpleBasePage(final PageParameters parameters) {
		super(parameters);
		add(new HtmlTag("html"));
		add(new OptimizedMobileViewportMetaTag("viewport"));
		add(new ChromeFrameMetaTag("chrome-frame"));
		add(new MetaTag("description", Model.of("description"), Model.of("Company Description")));
		add(newNavbar("navbar"));
		add(newNavigation("navigation"));
		// add(new Footer("footer"));
		add(new BootstrapBaseBehavior());
		add(new Code("code-internal"));
	}

	/**
	 * creates a new {@link Navbar} instance
	 * 
	 * @param markupId
	 *            The components markup id.
	 * @return a new {@link Navbar} instance
	 */
	protected Navbar newNavbar(String markupId) {
		Navbar navbar = new Navbar(markupId);
		// navbar.setPosition(Navbar.Position.TOP);
		// show brand name
		navbar.brandName(Model.of("Company Name"));
		NavbarButton<Homepage> homepageBtn = new NavbarButton<Homepage>(Homepage.class, Model.of("Overview"));
		homepageBtn.setMarkupId("navButton");
		homepageBtn.setIconType(IconType.home);
		NavbarButton<Homepage> howWeWorkBtn = new NavbarButton<Homepage>(Homepage.class, Model.of("How We Works"));
		howWeWorkBtn.setMarkupId("navButton");
		NavbarButton<Homepage> contactUsBtn = new NavbarButton<Homepage>(Homepage.class, Model.of("Contact Us"));
		contactUsBtn.setMarkupId("navButton");
		NavbarButton<Homepage> aboutUsBtn = new NavbarButton<Homepage>(Homepage.class, Model.of("About Us"));
		aboutUsBtn.setMarkupId("navButton");
		List<INavbarComponent> components = NavbarComponents.transform(Navbar.ComponentPosition.LEFT, homepageBtn, howWeWorkBtn, contactUsBtn, aboutUsBtn);
		navbar.addComponents(components);
		return navbar;
	}

	/**
	 * @return new dropdown button for all addons
	 */

	private Component newAddonsDropDownButton() {
		return new NavbarDropDownButton(Model.of("Addons")) {
			@Override
			protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				// subMenu.add(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh));
				// subMenu.add(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time));
				// subMenu.add(new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.book));
				// subMenu.add(new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.alignjustify));
				return subMenu;
			}
		}.setIconType(IconType.thlarge).setInverted(true).add(new DropDownAutoOpen());
	}

	/**
	 * sets the theme for the current user.
	 * 
	 * @param pageParameters
	 *            current page parameters
	 */
	private void configureTheme(PageParameters pageParameters) {
		List<ITheme> avalThemes = Bootstrap.getSettings(getApplication()).getThemeProvider().available();
		StringValue theme = pageParameters.get("theme");
		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
		// settings.getActiveThemeProvider().setActiveTheme(new MetroTheme());
	}

	protected ITheme activeTheme() {
		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
		return settings.getActiveThemeProvider().getActiveTheme();
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		configureTheme(getPageParameters());
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptReferenceHeaderItem.forReference(basepageJs));
		response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
		response.render(CssHeaderItem.forReference(basePageCss));
		response.render(CssHeaderItem.forReference(carouselCss));

		response.render(CssHeaderItem.forReference(bootstrapImageGalleryCss));
		response.render(JavaScriptReferenceHeaderItem.forReference(loadImageJs));
		response.render(JavaScriptReferenceHeaderItem.forReference(bootstrapImageGalleryJs));
		response.render(JavaScriptReferenceHeaderItem.forReference(mainJs));

	}

	protected boolean hasNavigation() {
		return false;
	}

	/**
	 * creates a new navigation component.
	 * 
	 * @param markupId
	 *            The component's markup id
	 * @return a new navigation component.
	 */
	private Component newNavigation(String markupId) {
		WebMarkupContainer navigation = new WebMarkupContainer(markupId);
		navigation.add(new AffixBehavior("200"));
		navigation.setVisible(hasNavigation());
		return navigation;
	}

}