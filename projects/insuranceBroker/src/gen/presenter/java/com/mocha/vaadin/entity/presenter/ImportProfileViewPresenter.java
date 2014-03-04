package com.mocha.vaadin.entity.presenter;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.FacebookFriend;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.cooperate.service.pollService.PollService;
import com.mocha.cooperate.service.pollService.ThreadPoolManager;
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.pollService.FBStatusUpdatechedulerTask;
import com.mocha.ib.widget.FileUpload;
import com.mocha.soicalAPI.AppAuthenciationPrsenter;
import com.mocha.vaadin.entity.view.ImportProfileView;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import com.vaadin.ui.Upload.FinishedEvent;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

public class ImportProfileViewPresenter extends AppCommonPresenter implements Presenter {

  private List<InsuranceCustomer> list;

  InsuranceCustomerDao icDao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
  SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);

  public ImportProfileViewPresenter(MochaEventBus eventBus) {
    this.eventBus = eventBus;
    this.viewer = new ImportProfileView(eventBus.getUser());
  }

  @Override
  public void bind() {

    ((ImportProfileView) viewer).getImportIcBtn().addListener(new ClickListener() {
      /**
			 * 
			 */
      private static final long serialVersionUID = 1L;

      @Override
      public void buttonClick(ClickEvent event) {

      }
    });

    FileUpload fload = ((ImportProfileView) viewer).getUpload();
    fload.addListener(new Upload.FinishedListener() {
      /**
			 * 
			 */
      private static final long serialVersionUID = 1L;

      @Override
      public void uploadFinished(FinishedEvent event) {
        setList(((ImportProfileView) viewer).getUpload().getList());
        System.out.println("Import Profile is: " + getList());
        ((ImportProfileView) viewer).buildTableLayout(getList());
      }
    });

    ((ImportProfileView) viewer).getImportFbBtn().addListener(new ClickListener() {
      /**
			 * 
			 */
      private static final long serialVersionUID = 1L;
      private List<FacebookFriend> currentFbFriends;

      @Override
      public void buttonClick(ClickEvent event) {

        if (list != null) {
          SoicalApp sa = saDao.findSoicaAppByName(eventBus.getUser(), APIKeys.facebookAPIName);
          if (sa == null || sa.getAuthToken() == null) {
            ((ImportProfileView) viewer).buildFBAuthLayout();
            // AppContentEvent appContentEvent = new AppContentEvent();
            // appContentEvent.setCustomizeClass(AppAuthenciationPrsenter.class.getName());
            // eventBus.post(appContentEvent);
          } else {
            FBImpl fbImpl = new FBImpl(sa.getAuthToken());
            for (InsuranceCustomer ic : list) {

              ic.setAgent(eventBus.getUser());
              icDao.persist(ic);
              currentFbFriends = sa.getFacebookFriends();
              boolean foundNewFriends = checkIfExists(currentFbFriends, ic.getFbProfileUrl());

              if (foundNewFriends) {
                FacebookFriend fb = fbImpl.getFacebookUserByProfile(ic.getFbProfileUrl());
                if (fb != null) {
                  sa.getFacebookFriends().add(fb);
                }
              }
            }
            saDao.merge(sa);
            /* redirect to Customer Search */
            AppContentEvent appContentEvent = new AppContentEvent();
            appContentEvent
                .setCustomizeClass("com.mocha.vaadin.entity.presenter.InsCustomerSearchPresenter");
            eventBus.post(appContentEvent);
          }
        }
      }

      private boolean checkIfExists(List<FacebookFriend> currentFbFriends, String newFbProfileUrl) {
        for (FacebookFriend fb : currentFbFriends) {
          if (fb.getProfile_url() == newFbProfileUrl) {
            return false;
          }
        }
        return true;
      }
    });

    // ((ImportProfileView) viewer).getImportFbBtn().addListener(new ClickListener() {
    //
    // private List<FacebookFriend> currentFbFriends;
    //
    // @Override
    // public void buttonClick(ClickEvent event) {
    // if (sa == null || sa.getAuthToken() == null) {
    // // ((ImportProfileView) viewer).buildFBAuthLayout();
    // AppContentEvent appContentEvent = new AppContentEvent();
    // appContentEvent.setCustomizeClass(AppAuthenciationPrsenter.class.getName());
    // eventBus.post(appContentEvent);
    // } else {
    // FBImpl fbImpl = new FBImpl(sa.getAuthToken());
    // for (InsuranceCustomer ic : getList()) {
    // System.out.println(ic.getFbProfileUrl());
    // currentFbFriends = sa.getFacebookFriends();
    // boolean foundNewFriends = checkIfExists(currentFbFriends, ic.getFbProfileUrl());
    // if (foundNewFriends) {
    // FacebookFriend fb = fbImpl.getFacebookUserByProfile(ic.getFbProfileUrl());
    // if (fb != null) {
    // sa.getFacebookFriends().add(fb);
    // saDao.merge(sa);
    // }
    // }
    // }
    // }
    // }
    //
    // private boolean checkIfExists(List<FacebookFriend> currentFbFriends, String newFbProfileUrl)
    // {
    // for (FacebookFriend fb : currentFbFriends) {
    // if (fb.getProfile_url() == newFbProfileUrl) {
    // return false;
    // }
    // }
    // return true;
    // }
    // });

  }

  @Override
  public String getPresenterName() {
    return null;
  }

  public List<InsuranceCustomer> getList() {
    return list;
  }

  public void setList(List<InsuranceCustomer> list) {
    this.list = list;
  }

}
