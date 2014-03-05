package com.mocha.ib.pollService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.TimerTask;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.FacebookFriendDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.FacebookFriend;
import com.coral.foundation.security.model.FbFriendWork;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.mocha.cooperate.basic.dao.NotifyLineDao;
import com.mocha.cooperate.basic.dao.StatusDao;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.service.pollService.MochaTask;

public class FBStatusUpdatechedulerTask extends MochaTask {

  FacebookFriendDao fbDao = SpringContextUtils.getBean(FacebookFriendDao.class);
  BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
  SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);
  NotifyLineDao notifyLineDao = SpringContextUtils.getBean(NotifyLineDao.class);
  StatusDao statusDao = SpringContextUtils.getBean(StatusDao.class);
  FBImpl fbImpl;
  private BasicUser user;
  List<FbFriendWork> newUpdateFbFriendList = new ArrayList<FbFriendWork>();

  public FBStatusUpdatechedulerTask(BasicUser user) {
    super(user);
    this.setUser(user);
  }


  public void run() {
    System.out.println("Run the task now");
    if (getUser() != null) {
      SoicalApp soicalApp = saDao.findSoicaAppByName(getUser(), APIKeys.facebookAPIName);
      fbImpl = new FBImpl(soicalApp.getAuthToken());
      List<FacebookFriend> fbFriendsListFromDB = soicalApp.getFacebookFriends();
      List<FacebookFriend> newUpdateFbFriendls = new ArrayList<FacebookFriend>();
      boolean flg = false;

      for (ListIterator<FacebookFriend> iter = fbFriendsListFromDB.listIterator(); iter.hasNext();) {
        FacebookFriend fbFromDB = iter.next();
        FacebookFriend fbFromAPI = fbImpl.getFacebookUserByProfile(fbFromDB.getProfile_url());
        // need to update the status
        System.out.println("User " + fbFromAPI.getName() + "fbFromAPI time: " + fbFromAPI
            + " fbFromDB time: " + fbFromDB);
        if (!(fbFromAPI.getProfile_update_time().equals(fbFromDB.getProfile_update_time()))) {
          flg = true;

          System.out.println("Found Different");
          // add the notification lines
          List<FbFriendWork> mergeFbWorks =
              newAddEmployers(fbFromAPI.getWorks(), fbFromDB.getWorks());

          // find the new record
          if (mergeFbWorks.size() > 0) {
            // set notification
            addNotification(mergeFbWorks, fbFromDB);

            // add new fb updated object
            newUpdateFbFriendls.add(fbFromAPI);

            // remove the old work object
            iter.remove();

            // remove the old facebook object
            fbDao.remove(fbFromDB.getFacebookFriendId());

          }
        }
      }

      // find the different value and then update the social app object
      if (flg && newUpdateFbFriendls.size() > 0) {
        // soicalApp.getFacebookFriends().addAll(newUpdateFbFriendls);
        for (FacebookFriend fb : newUpdateFbFriendls) {
          soicalApp.getFacebookFriends().add(fb);
        }
        saDao.merge(soicalApp);
      }
    }
  }

  private void addNotification(List<FbFriendWork> mergeFbWorks, FacebookFriend fbFromDB) {

    StringBuilder statusMessage = new StringBuilder();
    statusMessage.append("<h2>Your Facebook Friend " + fbFromDB.getName()
        + " has updated his profile" + "</h2>" + "\n");
    StringBuilder newRecordTemplate = new StringBuilder();

    for (FbFriendWork merFbFriendWork : mergeFbWorks) {
      newRecordTemplate.append("Employer Name: " + merFbFriendWork.getEmployer_name() + " <br>");
      if (merFbFriendWork.getLocation_name() != null) {
        newRecordTemplate.append("Location: " + merFbFriendWork.getLocation_name() + " <br>");
      }
    }
    statusMessage.append(newRecordTemplate);

    NotifyLine notifyLine = new NotifyLine();
    Status status = new Status();
    status.setContent(statusMessage.toString());
    status.setCreator(getUser());
    notifyLine.setStatus(status);
    notifyLine.setType(1l);
    notifyLine.setNotifiedUser(getUser());
    status.getNotifyLines().add(notifyLine);
    statusDao.persist(status);

  }

  private List<FbFriendWork> newAddEmployers(List<FbFriendWork> fbFromAPI,
      List<FbFriendWork> fbFromDB) {
    List list = new ArrayList(Math.min(fbFromAPI.size(), fbFromDB.size()));
    for (Iterator<FbFriendWork> iter = fbFromAPI.iterator(); iter.hasNext();) {
      FbFriendWork obj = null;
      try {
        obj = (FbFriendWork) iter.next();
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      }
      boolean needAdd = true;

      for (Iterator<FbFriendWork> iter2 = fbFromDB.iterator(); iter2.hasNext();) {
        FbFriendWork obj2 = null;
        try {
          obj2 = (FbFriendWork) iter2.next();
        } catch (NoSuchElementException e) {
          e.printStackTrace();
        }
        if (obj2.getEmployer_name().contains(obj.getEmployer_name())) {
          needAdd = false;
        }
      }

      if (needAdd) {
        list.add(obj);
      }
    }
    return list;
  }

  public BasicUser getUser() {
    return user;
  }

  public void setUser(BasicUser user) {
    this.user = user;
  }

}
