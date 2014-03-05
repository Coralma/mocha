/**
 * 
 */
package com.mocha.ib.presenter;

import java.util.HashSet;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.service.pollService.PollService;
import com.mocha.cooperate.service.pollService.ThreadPoolManager;
import com.mocha.ib.pollService.FBStatusUpdatechedulerTask;

/**
 * @author Coral
 * 
 */
public class IBDashboardPresenter extends CommonPresenter implements Presenter {

  private static HashSet<BasicUser> userHistory = new HashSet<BasicUser>();
  private static ThreadPoolManager threadPoolManager = ThreadPoolManager
      .getInstance("threadPoolManager ");
  private SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);

  public IBDashboardPresenter(MochaEventBus eventBus) {
    this.eventBus = eventBus;
    this.viewer = new IBDashboardView(eventBus.getUser());
  }

  @Override
  public void bind() {
    // init poll sevice
    PollService.getPollServiceInstance()
        .addTask(new FBStatusUpdatechedulerTask(eventBus.getUser()));
  }
//  private synchronized void startServiceAndTask() {
//
//    PollService pollService = PollService.getPollServiceInstance();
//    FBStatusUpdatechedulerTask mochaTask = new FBStatusUpdatechedulerTask(eventBus.getUser());
//    boolean needToStartTask = true;
//
//    for (BasicUser bu : userHistory) {
//
//      if (bu.getUserName().equals(eventBus.getUser().getUserName())) {
//        needToStartTask = false;
//        break;
//      }
//    }
//
//    if (needToStartTask) {
//      SoicalApp soicalApp = saDao.findSoicaAppByName(mochaTask.getUser(), APIKeys.facebookAPIName);
//      if (soicalApp != null && soicalApp.getFacebookFriends().size() > 0) {
//        userHistory.add(eventBus.getUser());
//        System.out.println("Start new thread for facebook polling service ");
//        threadPoolManager.getMsgQueue().add(mochaTask);
//      }
//
//    }
//
//  }
  @Override
  public String getPresenterName() {
    // TODO Auto-generated method stub
    return "Home";
  }

  @Override
  public boolean isFullSize() {
    return false;
  }


}
