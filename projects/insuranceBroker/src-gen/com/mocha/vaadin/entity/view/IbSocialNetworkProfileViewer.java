package com.mocha.vaadin.entity.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.coral.foundation.facebook.FBUserModel;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.FacebookFriend;
import com.coral.foundation.security.model.FbFriendEduction;
import com.coral.foundation.security.model.FbFriendLanguage;
import com.coral.foundation.security.model.FbFriendWork;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.soicalAPI.AppAuthenciateWindow;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.Window.Notification;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

public class IbSocialNetworkProfileViewer extends EntityViewPanel implements Viewer {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private VerticalLayout mainLayout = new VerticalLayout();
  private NativeButton viewALlBtn = new NativeButton("ViewAll");

  private VerticalLayout settingContentPanel = new VerticalLayout();
  private Widget photoUploader;
  private Button changeButton;
  private String labelWidth = "120px";
  private String BASIC_INFO = "BasicInfo";
  private String CHANGE_PASSWORD = "ChangePassword";
  private NativeButton syncStatusBtn = new NativeButton("Syncing Facebook Profile Statups Now");
  private FacebookFriend fbFriend = new FacebookFriend();
  private BasicUser bu;
  private boolean needAuth;

  public IbSocialNetworkProfileViewer(FacebookFriend fbFriend, BasicUser bu, boolean needAuth) {
    this.addStyleName("mocha-coding-viewer");
    this.setFbFriend(fbFriend);
    this.needAuth = needAuth;
    this.bu = bu;
  }

  @Override
  public void attach() {
    if (getFbFriend() == null && needAuth == true) {
      // Facebook facebook = new FacebookFactory().getInstance();
      // facebook.setOAuthAppId(APIKeys.facebookAPIId, APIKeys.facebookSecertKey);
      // facebook.setOAuthCallbackURL(APIKeys.facebookCallBackUrl);
      // getApplication().getMainWindow().open(new ExternalResource(APIKeys.facebookOAuthUrl), "",
      // -1,
      // -1, Window.BORDER_DEFAULT);
    } else if (getFbFriend() == null && needAuth == false) {
      // getWindow().showNotification("Could not create a new work entry due to an internal error.",
      // Notification.TYPE_ERROR_MESSAGE);
    } else {
      buildProfileLayout();
    }
  }

  @Override
  public void build() {
    addComponent(mainLayout);
    viewALlBtn.addStyleName("mocha-button");
    syncStatusBtn.addStyleName("mocha-button");
    mainLayout.addComponent(getViewALlBtn());
  }

  private void buildFacebookLoginWindow() {
    AppAuthenciateWindow appAuthWin = new AppAuthenciateWindow(null, bu);
    appAuthWin.addListener(new CloseListener() {
      /**
         * 
         */
      private static final long serialVersionUID = 1L;

      @Override
      public void windowClose(CloseEvent e) {
        // listener.closeWindow();
      }
    });
    getWindow().addWindow(appAuthWin);
  }

  public NativeButton getViewALlBtn() {
    return viewALlBtn;
  }

  public void setViewALlBtn(NativeButton viewALlBtn) {
    this.viewALlBtn = viewALlBtn;
  }

  private void buildProfileLayout() {
    mainLayout.removeAllComponents();

    syncStatusBtn.addStyleName("mocha-button");

    ToolbarAdvance toolbar = new ToolbarAdvance();

    mainLayout.addComponent(toolbar);
    settingContentPanel.setWidth("100%");
    mainLayout.addComponent(settingContentPanel);
    this.addComponent(mainLayout);
    this.buildBasicInformationPanel();
  }

  public void buildBasicInformationPanel() {

    settingContentPanel.removeAllComponents();
    VerticalLayout settingSection = new VerticalLayout();
    settingSection.addStyleName("setting-user-info");
    settingSection.setWidth("500px");

    FormLayout userFormLayout = new FormLayout();
    // userFormLayout.setCaption("Basic Info");
    userFormLayout.setSpacing(true);

    IViewPanel viewPanel = createViewPanel();
    ISectionPanel sectionPanel;
    sectionPanel = createSectionPanel("Basic User");
    sectionPanel.setLabel("Basic Info");
    viewPanel.addSection(sectionPanel);

    HorizontalLayout hLyaout = new HorizontalLayout();
    hLyaout.addComponent(getSyncStatusBtn());
    hLyaout.setComponentAlignment(getSyncStatusBtn(), Alignment.MIDDLE_RIGHT);
    userFormLayout.addComponent(hLyaout);


    Component pic =
        WidgetFactory.createSocialAvatar(getFbFriend().getPic().toString(), getApplication());
    viewPanel.addComponent(pic);

    Label firstName = WidgetFactory.createSocialLabel("First Name", getFbFriend().getFirst_name());
    userFormLayout.addComponent(firstName);
    Label lastName = WidgetFactory.createSocialLabel("Last Name", getFbFriend().getLast_name());
    userFormLayout.addComponent(lastName);

    Label bio =
        WidgetFactory.createSocialLabel("Bio", getFbFriend().getAbout_me() == null ? ""
            : getFbFriend().getAbout_me());
    viewPanel.addComponent(bio);

    Label gender = WidgetFactory.createSocialLabel("Gender", getFbFriend().getSex());
    userFormLayout.addComponent(gender);

    Label email =
        WidgetFactory.createSocialLabel("Email Address", fbFriend.getEmail() == null ? ""
            : fbFriend.getEmail());
    userFormLayout.addComponent(email);

    Label onlinePresenece =
        WidgetFactory.createSocialLabel("Online Presence",
            fbFriend.getOnline_presence() == null ? "" : fbFriend.getOnline_presence());
    userFormLayout.addComponent(onlinePresenece);

    Label birthday =
        WidgetFactory.createSocialLabel("Birthday",
            fbFriend.getBirthday() == null ? "" : fbFriend.getBirthday());
    userFormLayout.addComponent(birthday);
    birthday.addStyleName("facebookProfileProperty");

    Label locale =
        WidgetFactory.createSocialLabel("Locale",
            fbFriend.getLocale() == null ? "" : fbFriend.getLocale());
    userFormLayout.addComponent(locale);

    Label mutualFirendsCount =
        WidgetFactory.createSocialLabel("Mutual Friends Count",
            fbFriend.getMutual_friend_count() == null ? "" : fbFriend.getMutual_friend_count());

    Label webSite =
        WidgetFactory.createSocialLabel("Web Site",
            fbFriend.getWebsite() == null ? "" : fbFriend.getWebsite());
    userFormLayout.addComponent(webSite);

    Label friendCount =
        WidgetFactory.createSocialLabel("Friend Count", fbFriend.getFriend_count() == null ? ""
            : fbFriend.getFriend_count());
    userFormLayout.addComponent(friendCount);


    Label relationShipStatus =
        WidgetFactory.createSocialLabel("RelationShip Status",
            fbFriend.getRelationship_status() == null ? "" : fbFriend.getRelationship_status());
    userFormLayout.addComponent(relationShipStatus);


    FormLayout companyInfoLayout = new FormLayout();

    companyInfoLayout.setSpacing(true);

    if (fbFriend.getWorks().size() > 0) {
      for (FbFriendWork work : fbFriend.getWorks()) {
        if (work.getEmployer_name() != null) {
          Label employeeName = WidgetFactory.createSocialLabel("Employee", work.getEmployer_name());
          companyInfoLayout.addComponent(employeeName);
        }
        //
        // if (work.getLocation_name() != null) {
        // Label companyLocation =
        // WidgetFactory.createCaptionLabel("Location", work.getLocation_name() == null ? ""
        // : work.getLocation_name());
        // companyInfoLayout.addComponent(companyLocation);
        // }
      }
    } else {
      companyInfoLayout.addComponent(new Label());
    }

    FormLayout edcucationLayout = new FormLayout();
    edcucationLayout.setCaption("Education Info");
    edcucationLayout.setSpacing(true);

    if (fbFriend.getEducations().size() > 0) {
      for (FbFriendEduction fbEd : fbFriend.getEducations()) {
        HorizontalLayout shcoolLayout = new HorizontalLayout();
        Label schoolName = WidgetFactory.createSocialLabel("School", fbEd.getShcool_name());
        shcoolLayout.addComponent(schoolName);

        Label yearName = WidgetFactory.createSocialLabel("Year", fbEd.getShcool_name());
        shcoolLayout.addComponent(schoolName);

        edcucationLayout.addComponent(shcoolLayout);
      }
    } else {
      edcucationLayout.addComponent(new Label());
    }

    FormLayout languageLayout = new FormLayout();
    languageLayout.setCaption("Language Info");
    languageLayout.setSpacing(true);

    if (fbFriend.getLanguages().size() > 0) {
      for (FbFriendLanguage fbLang : fbFriend.getLanguages()) {
        Label langName =
            WidgetFactory.createCaptionLabel("Language",
                fbLang.getName() == null ? "" : fbLang.getName());
        languageLayout.addComponent(langName);
      }
    } else {
      languageLayout.addComponent(new Label());
    }

    FormLayout currentLocationLayout = new FormLayout();
    currentLocationLayout.setCaption("Current Location");
    currentLocationLayout.setSpacing(true);

    if (fbFriend.getCurrentLocation() != null) {
      Label currentLocation =
          WidgetFactory.createCaptionLabel("Current Location", fbFriend.getCurrentLocation()
              .getName() == null ? "" : fbFriend.getCurrentLocation().getName());
      currentLocationLayout.addComponent(currentLocation);
    } else {
      currentLocationLayout.addComponent(new Label());
    }

    FormLayout currentAddressLayout = new FormLayout();
    currentAddressLayout.setCaption("Current Address");
    currentAddressLayout.setSpacing(true);

    if (fbFriend.getCurrentAddress() != null) {
      Label currentAddress =
          WidgetFactory.createCaptionLabel("Current Address", fbFriend.getCurrentAddress()
              .getName() == null ? "" : fbFriend.getCurrentAddress().getName());
      currentAddressLayout.addComponent(currentAddress);
    } else {
      currentAddressLayout.addComponent(new Label());
    }

    viewPanel.addComponent(userFormLayout);

    mainLayout.addComponent(viewPanel);
    settingContentPanel.addComponent(settingSection);
    mainLayout.addComponent(companyInfoLayout);
    mainLayout.addComponent(edcucationLayout);
    mainLayout.addComponent(languageLayout);
    mainLayout.addComponent(currentLocationLayout);
    mainLayout.addComponent(currentAddressLayout);
    buildUpdateMessageLayout();
  }

  private void buildUpdateMessageLayout() {
    // try {
    // StringBuilder sb = new StringBuilder();
    // sb.append(fbUserModel.getFbTimeline().getHeader());
    // for (String s : fbUserModel.getFbTimeline().getContent()) {
    // sb.append(s);
    // }
    // CustomLayout customLayout =
    // new CustomLayout(new ByteArrayInputStream(sb.toString().getBytes()));
    // this.addComponent(customLayout);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // VerticalLayout recentlyLayout = new VerticalLayout();
    // recentlyLayout.setCaption("Recent Updates Messages");
    // List<LinkedinConnectionNetworkUpdate> updates =
    // getLinkedConn().getLinkedinConnectionNetworkUpdate();
    // HashSet<String> updateMessages = new HashSet<String>();
    // Indexed messageConatiner = new IndexedContainer(updateMessages);
    // if (updates != null && updates.size() > 0) {
    // VerticalLayout recentlyLayoutMessage = new VerticalLayout();
    // recentlyLayoutMessage.setCaption("User Update Message");
    // recentlyLayoutMessage.addStyleName("linkedinConnectionsUpdateMessageLayout");
    //
    // PagedTableContainer ic = new PagedTableContainer(messageConatiner);
    // ic.setPageLength(messageConatiner.size());
    // ic.addContainerProperty("Update Message", String.class, null);
    // int i = 0;
    // for (LinkedinConnectionNetworkUpdate update : updates) {
    // if (update.getUpdateMessage() != null) {
    // updateMessages.add(update.getUpdateMessage());
    // Item item = messageConatiner.addItem(i++);
    // item.getItemProperty("Update Message").setValue(update.getUpdateMessage());
    // }
    // }
    // }
    // PagedTable pt = new PagedTable("");
    // pt.setWidth("800px");
    // pt.setContainerDataSource(messageConatiner);
    // settingContentPanel.addComponent(pt);
    // pt.getItemsPerPageSelect().setVisible(false);
    // recentlyLayout.addComponent(pt);
    // recentlyLayout.addComponent(pt.createControls());
    // this.addComponent(recentlyLayout);

    // VerticalLayout recentlyLayout = new VerticalLayout();
    // recentlyLayout.setCaption("Recent Updates Messages");
    // recentlyLayout.setStyleName("linkedinConnectionsUpdateMessageLayout");

    // this.addComponent(recentlyLayout);
  }

  public FacebookFriend getFbFriend() {
    return fbFriend;
  }

  public void setFbFriend(FacebookFriend fbFriend) {
    this.fbFriend = fbFriend;
  }

  public NativeButton getSyncStatusBtn() {
    return syncStatusBtn;
  }

  public void setSyncStatusBtn(NativeButton syncStatusBtn) {
    this.syncStatusBtn = syncStatusBtn;
  }

  public void buildSyncMessage() {

    // Create a notification with default settings for a warning.
    Window.Notification notif =
        new Window.Notification("Thanks for your request!",
            "We have successfully loaded your friends' social profile from Facebook",
            Window.Notification.TYPE_HUMANIZED_MESSAGE);

    // Set the position.
    notif.setPosition(Window.Notification.POSITION_CENTERED);

    // Let it stay there until the user clicks it
    notif.setDelayMsec(-1);

    // Show it in the main window.
    getWindow().showNotification(notif);

  }
}
