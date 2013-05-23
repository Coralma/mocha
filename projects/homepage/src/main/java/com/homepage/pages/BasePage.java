package com.homepage.pages;

//import com.newrelic.api.agent.NewRelic;
import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarAjaxButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarAjaxLink;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.navigation.BootstrapPagingNavigator.Position;
//import de.agilecoders.wicket.samples.WicketApplication;
//import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
//import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
//import de.agilecoders.wicket.samples.components.site.Footer;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import de.agilecoders.wicket.settings.ITheme;
import mx4j.tools.remote.http.WebContainer;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.CloseButtonCallback;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.string.StringValue;
import com.homepage.mail.task.MailQueueConsumer;
import com.homepage.model.User;
import com.homepage.model.UserDao;
import com.homepage.pages.Homepage;
import com.homepage.pages.UserLoginPage;
import com.homepage.pages.login.AccountProfilePage;
import com.homepage.pages.login.UserLoginPanel;
import com.homepage.pages.login.UserLoginWindow;
import com.homepage.pages.register.RegisterPage;
import com.homepage.security.SecuritySession;

import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.text.SimpleAttributeSet;

public abstract class BasePage<T> extends GenericWebPage<T> {

	SecuritySession session = (SecuritySession) SecuritySession.get();

	private static boolean userLogined = false;

	private User user = null;

	private static NavbarAjaxLink<Object> userLoginLink = null;

	private static NavbarAjaxLink<Object> userNameLink = null;

	/**
	 * Construct.
	 */
	public BasePage() {
		super();
		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 * 
	 * @param model
	 *            The model to use for this page
	 */
	public BasePage(IModel<T> model) {
		super(model);

		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 * 
	 * @param parameters
	 *            current page parameters
	 */
	public BasePage(PageParameters parameters) {
		super(parameters);
		commonInit(parameters);
	}

	/**
	 * common initializer
	 * 
	 * @param pageParameters
	 *            current page parameters
	 */
	private void commonInit(PageParameters pageParameters) {

		String str = "<ul class=\"dropdown-menu\">"
				+ ""
				+ "<li><a href=\"#\" onclick=\"return false\" id=\"chimpanzee\">Chimpanzee</a></li>"
				+ "<li><a href=\"#\" onclick=\"return false\" id=\"lemur\">Lemur</a></li></ul>";

		Label dynamticHtml = new Label("dynamticHtml", str);
		dynamticHtml.setEscapeModelStrings(false);
		
		add(new HtmlTag("html"));
		WebMarkupContainer navigationContainer = new WebMarkupContainer("nav");
		add(navigationContainer);
		navigationContainer.add(newNavbar("navbar"));
		navigationContainer.add(newNavigation("navigation"));
		// home page button

		Link homebtn = new Link("homeBtn") {

			@Override
			public void onClick() {
				setResponsePage(Homepage.class);
			}

		};
		add(homebtn);

		// User login logical here
		// if user signed successfully, thus should enable the profile link
		Button commonUserLoginLnk = new Button("commonUserLogin");
		String userProfileLinkName = "";
		NavbarAjaxLink<Object> commonUserLogoutLnk = new NavbarAjaxLink<Object>(
				"commonUserLogoutLnk", Model.of("LogOut")) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				setBasePageUser(null);
				setResponsePage(Homepage.class);
				session.signOut();
			}
		};
		commonUserLogoutLnk.setVisible(false);
		if (session.getUser() != null & session.isSignedIn()) {
			userProfileLinkName = session.getUser().getEmailAddress();
			commonUserLoginLnk.setVisible(false);
			commonUserLogoutLnk.setVisible(true);
		}

		NavbarAjaxLink<Object> commonUserLoginPrfileLnk = new NavbarAjaxLink<Object>(
				"commonUserLoginPrfileLnk", Model.of(userProfileLinkName)) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new AccountProfilePage());
			}
		};
		add(commonUserLoginPrfileLnk);
		add(commonUserLoginLnk);
		add(commonUserLogoutLnk);
		add(new UserLoginPanel("myUserLogin") {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			@Override
			public void onLogin(AjaxRequestTarget target) {
				boolean isSignIn = session.authenticate(getLoginUser()
						.getEmailAddress(), getLoginUser().getPw());
				setResponsePage(AccountProfilePage.class);
				// handler the login failed later
			}

			private User valdateUser(User user) {
				UserDao userDao = new UserDao();
				User resultUser = userDao.findUserByEmail(user
						.getEmailAddress());
				return resultUser;
			}

			@Override
			public void onCancel(AjaxRequestTarget target) {
				System.out.println("click cancel button ");
				// close(target);
			}

			@Override
			public User getInputUser() {
				return null;
			}
		});

		buildFooterComponent();
	}

	private void buildFooterComponent() {
		WebMarkupContainer footerPanel = new WebMarkupContainer("footer");
		add(footerPanel);
		Link aboutUs = new Link("aboutUs") {
			/**
    		 * 
    		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AboutUs.class);
			}
		};
		footerPanel.add(aboutUs);

		Link contactUs = new Link("contactUs") {
			/**
    		 * 
    		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(ContactUsPage.class);
			}
		};
		footerPanel.add(contactUs);

		Link pricing = new Link("pricing") {
			/**
    		 * 
    		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AboutUs.class);
			}
		};
		footerPanel.add(pricing);

		Link<Object> blog = new Link<Object>("blog") {
			/**
    		 * 
    		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AboutUs.class);
			}
		};
		footerPanel.add(blog);
		
		Link<Object> serverStatus = new Link<Object>("serverStatus") {
			/**
    		 * 
    		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AboutUs.class);
			}
		};
		footerPanel.add(serverStatus);

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
		navbar.setPosition(Navbar.Position.TOP);
		navbar.brandName(Model.of(""));
		final ModalWindow modal = new ModalWindow("userLoginPopupWindow");
		modal.setOutputMarkupId(true);
		add(modal);
		modal.setPageCreator(new ModalWindow.PageCreator() {
			public Page createPage() {
				return new UserLoginPage(BasePage.this.getPageReference(),
						modal);
			}
		});

		modal.setWindowClosedCallback(new WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget target) {
				System.out.println("setWindowClosedCallback User click login");
				setResponsePage(new Blog());
			}
		});
		modal.setTitle("");

		final UserLoginWindow userLoginWindow = new UserLoginWindow(
				"userLoginSampelWindow") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void onLogin(AjaxRequestTarget target) {
				boolean isSignIn = session.authenticate(getUser()
						.getEmailAddress(), getUser().getPw());
				session.setUser(getUser());
				setResponsePage(AccountProfilePage.class);
				// handler the login failed later
			}

			private User valdateUser(User user) {

				UserDao userDao = new UserDao();
				User resultUser = userDao.findUserByEmail(user
						.getEmailAddress());
				return resultUser;
			}

			@Override
			public void onCancel(AjaxRequestTarget target) {
				System.out.println("click cancel button ");
				close(target);
			}

		};
		add(userLoginWindow);

		userLoginLink = new NavbarAjaxLink<Object>(Model.of("SignIn")) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				target.appendJavaScript("Wicket.Window.unloadConfirmation =false;");
				userLoginWindow.show(target);
			}
		};

		String userProfileLinkName = "";
		if (session.getUser() != null & session.isSignedIn()) {
			userProfileLinkName = session.getUser().getEmailAddress();
		}

		NavbarAjaxLink<Object> userProfileLink = new NavbarAjaxLink<Object>(
				Model.of(userProfileLinkName)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new AccountProfilePage());
			}
		};

		NavbarAjaxLink<Object> userLogout = new NavbarAjaxLink<Object>(
				Model.of("Logout")) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setBasePageUser(null);
				setResponsePage(Homepage.class);
				session.signOut();

			}

		};

		if (session.isSignedIn()) {
			userProfileLink.setVisible(true);
			userLogout.setVisible(true);
			getUserloginlink().setVisible(false);
		} else if (session.isSessionInvalidated() || !session.isSignedIn()) {
			userProfileLink.setVisible(false);
			getUserloginlink().setVisible(true);
			userLogout.setVisible(false);
		}

		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				new NavbarButton<Homepage>(Homepage.class, Model.of("Home"))
						.setIconType(IconType.home).add(
								AttributeModifier.append("style",
										"color: black"))));

		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT, getSignUpLink(),
				// getUserloginlink().add(
				// AttributeModifier.append("data-toggle",
				// "dropdown")).add(AttributeModifier.append("class",
				// "dropdown-toggle"))
				getUserloginlink()));

		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT, userProfileLink, userLogout));

		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());

		return navbar;
	}

	private Component getSignUpLink() {
		NavbarButton navBarButton = (NavbarButton) new NavbarButton<Homepage>(
				RegisterPage.class, Model.of("SignUp"))
				.add(AttributeModifier
						.append("style",
								"line-height: 24px;background-color: #AB0001; background-image: "
										+ "-moz-linear-gradient(center top , #AB0001, #AB0001);"
										+ "background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);box-shadow: none;color: #FFFFFF;font-family: 'PTSansBold';text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.5);"));
		if (session.isSessionInvalidated() || !session.isSignedIn()) {
			navBarButton.setVisible(true);
		} else {
			navBarButton.setVisible(false);
		}
		return navBarButton;
	}

	/**
	 * sets the theme for the current user.
	 * 
	 * @param pageParameters
	 *            current page parameters
	 */
	private void configureTheme(PageParameters pageParameters) {
		StringValue theme = pageParameters.get("theme");

		if (!theme.isEmpty()) {
			IBootstrapSettings settings = Bootstrap
					.getSettings(getApplication());
			settings.getActiveThemeProvider().setActiveTheme("readable");
		}
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
		settings.getActiveThemeProvider().setActiveTheme("readable");
		configureTheme(getPageParameters());
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem
				.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
		// bootstrap-tabs.js
		response.render(CssHeaderItem
				.forReference(new PackageResourceReference(
						AccountProfilePage.class, "bootstrap-tabs.js")));
		response.render(CssHeaderItem
				.forReference(new PackageResourceReference(
						AccountProfilePage.class, "jquery.easytabs.js")));
		response.render(CssHeaderItem
				.forReference(new PackageResourceReference(
						AccountProfilePage.class, "bootstrap-tabs.js")));

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

		navigation.setVisible(hasNavigation());
		Navbar navbar = new Navbar("Sign In");
		navigation.add(navbar);
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT,
				new NavbarButton<Homepage>(Homepage.class, Model.of("New Link"))
						.setIconType(IconType.home).add(
								AttributeModifier.append("style",
										"color: black")), getUserloginlink()));
		add(navigation);
		return navigation;
	}

	public static boolean isUserLogined() {
		return userLogined;
	}

	public static void setUserLogined(boolean userLogined) {
		BasePage.userLogined = userLogined;
	}

	public static NavbarAjaxLink<Object> getUserloginlink() {
		return userLoginLink;
	}

	public static NavbarAjaxLink<Object> getUserNameLink() {
		return userNameLink;
	}

	public static void setUserNameLink(NavbarAjaxLink<Object> userNameLink) {
		BasePage.userNameLink = userNameLink;
	}

	public User getBasePageUser() {
		return user;
	}

	public void setBasePageUser(User user) {
		this.user = user;
	}

	public abstract void build();

}
