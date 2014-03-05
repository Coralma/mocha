package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.facebook.FBUserModel;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.report.ProfileReport;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.FacebookFriendDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.FacebookFriend;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.pollService.FBStatusUpdatechedulerTask;
import com.mocha.soicalAPI.AppAuthenciateWindow;
import com.mocha.soicalAPI.AppAuthenciationPrsenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import facebook4j.FacebookException;

public class IbSocialNetworkProfilePresnter extends AppCommonPresenter implements Presenter {

  LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
  FacebookFriendDao fBDao = SpringContextUtils.getBean(FacebookFriendDao.class);
  SoicalAppDao soicalAppDo = SpringContextUtils.getBean(SoicalAppDao.class);

  BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
  List<LinkedinConnection> linkedinConnections;
  ProfileReport pr = new ProfileReport();

  private String fbProfileUrl;
  private String linkedinProfileUrl;
  private FBUserModel fbModel = new FBUserModel();
  private FacebookFriend fBFriend;
  private static String appName = "facebook";
  private boolean needAuth = false;

  public IbSocialNetworkProfilePresnter(MochaEventBus eventBus) {
    this.eventBus = eventBus;
    // start to parse the social data
    // SHould get the fb url from eventBus
    InsuranceCustomer ic = (InsuranceCustomer) eventBus.getContext().get("Entity");
    this.fbProfileUrl = ic.getFbProfileUrl();
    this.linkedinProfileUrl = ic.getLinkedinProfileUrl();
    // check facebook token first
    SoicalApp soicalApp = soicalAppDo.findSoicaAppByName(eventBus.getUser(), appName);
    if (soicalApp != null) {
      fBFriend = fBDao.findFacebookFriendByProfileUrl(fbProfileUrl);
      FBImpl fbImpl = new FBImpl(soicalApp.getAuthToken());
      if (fBFriend == null) {
        fBFriend = fbImpl.getFacebookUserByProfile(fbProfileUrl);
        if (fBFriend != null) {
          soicalApp.getFacebookFriends().add(fBFriend);
          soicalAppDo.merge(soicalApp);
        }
      } else {
        // try {
        // fbModel =
        // fbImpl.searchUserProfileByNameAndUrl(pr.getUserNamebyFBProfile(fbProfileUrl),
        // fbProfileUrl);
        // fbModel.setFbTimeline(FBImpl.getTimelineByProfileUrl(fbProfileUrl));
        // } catch (FacebookException e) {
        // e.printStackTrace();
        // }
        // String lastestProfileUpdateTime = fbImpl.getProfileUpdateTimeByUid(fBFriend.getUid());
        // System.out.println(fBFriend.getProfile_update_time() + " " + lastestProfileUpdateTime);
        // if (!lastestProfileUpdateTime.equals("0")
        // && !lastestProfileUpdateTime.equals(fBFriend.getProfile_update_time())) {
        // fBFriend = fbImpl.getFacebookUserByProfile(fbProfileUrl);
        // fBDao.merge(fBFriend);
        // }
      }
    } else {
      this.fBFriend = null;
      this.needAuth = true;
    }
    this.viewer = new IbSocialNetworkProfileViewer(fBFriend, getEventBus().getUser(), needAuth);
  }


  @Override
  public void bind() {
    final IbSocialNetworkProfileViewer ibViewer = (IbSocialNetworkProfileViewer) viewer;
    ibViewer.getSyncStatusBtn().addListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        ibViewer.buildSyncMessage();
        new FBStatusUpdatechedulerTask(eventBus.getUser()).run();
      }
    });
  }

  @Override
  public String getPresenterName() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getFbProfileUrl() {
    return fbProfileUrl;
  }

  public void setFbProfileUrl(String fbProfileUrl) {
    this.fbProfileUrl = fbProfileUrl;
  }

  public String getLinkedinProfileUrl() {
    return linkedinProfileUrl;
  }

  public void setLinkedinProfileUrl(String linkedinProfileUrl) {
    this.linkedinProfileUrl = linkedinProfileUrl;
  }

}
