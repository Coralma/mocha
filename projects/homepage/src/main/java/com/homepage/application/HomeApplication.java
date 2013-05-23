package com.homepage.application;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.google.javascript.jscomp.CompilationLevel;
import com.homepage.mail.task.MailQueueConsumer;
import com.homepage.model.SystemPropertyDao;
import com.homepage.model.User;
import com.homepage.pages.BasePage;
import com.homepage.pages.Homepage;
import com.homepage.pages.login.AccountProfilePage;
import com.homepage.security.*;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.markup.html.themes.metro.MetroTheme;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.BootswatchThemeProvider;
import de.agilecoders.wicket.settings.ThemeProvider;

public class HomeApplication extends WebApplication {

	private static final String PERSISTENCE_UNIT_NAME = "homepage";

	private static EntityManagerFactory factory;

	private static boolean needDBinit = true;

	private ApplicationContext context;

	@Override
	public void init() {
		super.init();
		configureBootstrap();
		initDB();
		getSecuritySettings().setAuthorizationStrategy(
				new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
		MetaDataRoleAuthorizationStrategy.authorize(AccountProfilePage.class,
				"USER");
		configureApplication();
	}
	private void configureApplication() {
		// we need a system level configuration here
		SystemPropertyDao systemProperty = new SystemPropertyDao();
		String paypal_ipn_url = systemProperty
				.findSystemValueByKey("paypal_ipn_url");
		if(paypal_ipn_url!=null){
			HomepageSystemProperty.setPaypal_ipn_url(paypal_ipn_url);
		}
	}
	private void initDB() {
		if (needDBinit) {
			setFactory(Persistence
					.createEntityManagerFactory(PERSISTENCE_UNIT_NAME));
			EntityManagerImpl entityMfg = (EntityManagerImpl) factory
					.createEntityManager();
			entityMfg.find(User.class, 0l);
			entityMfg.close();
			needDBinit = false;
		}
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Homepage.class;
	}

	// @Override
	// public Session newSession(Request request, Response response) {
	// return new SignInSession(request);
	// }

	/**
	 * configure all resource bundles (css and js)
	 */
	private void configureResourceBundles() {
		setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());

		getResourceBundles()
				.addJavaScriptBundle(
						HomeApplication.class,
						"core.js",
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getJQueryReference(),
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getWicketEventReference(),
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getWicketAjaxReference(),
						(JavaScriptResourceReference) ModernizrJavaScriptReference.INSTANCE);

		// getResourceBundles().addJavaScriptBundle(HomeApplication.class,
		// "bootstrap.js",
		// (JavaScriptResourceReference)
		// Bootstrap.getSettings().getJsResourceReference(),
		// (JavaScriptResourceReference)
		// Bootstrap.getSettings().getJqueryPPResourceReference(),
		// (JavaScriptResourceReference)
		// BootstrapPrettifyJavaScriptReference.INSTANCE,
		// ApplicationJavaScript.INSTANCE
		// );

		getResourceBundles().addJavaScriptBundle(HomeApplication.class,
				"bootstrap-extensions.js",
				JQueryUIJavaScriptReference.instance(),
				Html5PlayerJavaScriptReference.instance());

		getResourceBundles().addCssBundle(HomeApplication.class,
				"bootstrap-extensions.css", Html5PlayerCssReference.instance());

		// getResourceBundles().addCssBundle(HomeApplication.class,
		// "application.css",
		// (CssResourceReference)
		// Bootstrap.getSettings().getResponsiveCssResourceReference(),
		// (CssResourceReference) BootstrapPrettifyCssReference.INSTANCE,
		// FixBootstrapStylesCssResourceReference.INSTANCE
		// );
	}

	private void configureBootstrap() {
		BootstrapSettings settings = new BootstrapSettings();
		settings.minify(true)
				// use minimized version of all bootstrap references
				.useJqueryPP(true).useModernizr(true).useResponsiveCss(true)
				.setJsResourceFilterName("footer-container")
				.getBootstrapLessCompilerSettings().setUseLessCompiler(false);

		ThemeProvider themeProvider = new BootswatchThemeProvider() {
			{
				// add(new MetroTheme());
				defaultTheme("wicket");
			}
		};
		settings.setThemeProvider(themeProvider);
		Bootstrap.install(this, settings);
	}

	public static EntityManagerFactory getFactory() {
		return factory;
	}

	public static void setFactory(EntityManagerFactory factory) {

		HomeApplication.factory = factory;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new SecuritySession(request);
	}

}
