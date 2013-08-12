package com.coral.foundation.security;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.coral.foundation.security.basic.dao.BasicRoleDao;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;

/*
 * Singleton SecurityManager instance
 *  @author:vance
 * 
 */
public class CommonSecurityManager extends AuthorizingRealm {

	private static BasicUserDao userDao = SpringContextUtils.getBean(BasicUserDao.class);
	private static BasicRoleDao roleDao = SpringContextUtils.getBean(BasicRoleDao.class);
	private static DefaultSecurityManager securityManager = new DefaultSecurityManager();
	private static DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
	private static String authenticationQuery = "select password from vw_searchPassword where user_name=?";
	private static String userRolesQuery = "select role_name from vw_searchrole where user_name=?";
	private static String permissionsQuery = "select permission from vw_searchpermission where role_name=?";

	private Logger log = LoggerFactory.getLogger(CommonSecurityManager.class);

	public static DefaultSecurityManager initDefaultSecurityManager() {
		if (securityManager == null) {
			securityManager = new DefaultSecurityManager();
		}
		return securityManager;
	}

	private static SessionManager initSessionManager() {
		if (getSessionManager() == null) {
			sessionManager = new DefaultWebSessionManager();
		}
		return sessionManager;

	}

	public CommonSecurityManager build() {
		CommonSecurityManager commonSecurityManager = new CommonSecurityManager();
		SessionManager commonSessionManager = new CommonWebSessionManager();
		commonSecurityManager.initSessionManager();
		boolean needUseLocalRealms = false;
		if (!needUseLocalRealms) {
			initConnection();
		}
		SecurityUtils.setSecurityManager(securityManager);
		return commonSecurityManager;
	}

	private static void initConnection() {
		// use jdbcRealm
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setAuthenticationQuery(authenticationQuery);
		jdbcRealm.setPermissionsQuery(permissionsQuery);
		jdbcRealm.setUserRolesQuery(userRolesQuery);
		ApplicationContext context = SpringContextUtils.getApplicationContext();
		DataSource ds = (DataSource) context.getBean("dataSource");
		jdbcRealm.setDataSource(ds);
		securityManager.setRealm(jdbcRealm);
	}

	// public AuthenticationToken login(BasicUser basicUser) throws Exception {
	// return this.login(basicUser.getUserName(), basicUser.getPassword());
	// }

	private static AuthenticationToken validateUser(String userName, String pw) {
		UsernamePasswordToken token = new UsernamePasswordToken(userName, pw);
		return token;
	}

	public static DefaultWebSessionManager getSessionManager() {
		return sessionManager;
	}

	public static void setSessionManager(SessionManager sessionManager) {
		initSessionManager();
	}

	public void logout() {

		Subject currentSubject = SecurityUtils.getSubject();
		currentSubject.logout();
	}

	public class DataBean {
		private DataSource dataSource;

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		public DataSource getDateSource() {
			try {
				Connection connection = dataSource.getConnection();
				if (connection != null)
					return dataSource;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public BasicUser login(String inputUserName, String inputPassword) {
		try {
			BasicUser user = userDao.findUserByUserName(inputUserName.trim());
			boolean loginFlg = verifyPassword(user.getPassword(), inputPassword);
			if (loginFlg) {
				AuthenticationToken authToken = getAuthToken(inputUserName, inputPassword);
				return user;
			}
			return null;

		}
		catch (Exception e) {
			log.info("The user " + inputUserName + " login fail with the password " + inputPassword);
		}
		return null;
	}

	private boolean verifyPassword(String password, String inputPassword) {
		if (password.equals(inputPassword)) {
			return true;
		}
		return false;
	}

	private AuthenticationToken getAuthToken(String inputUserName, String inputPassword) throws Exception {
		String userName = inputUserName;
		String pw = inputPassword;
		Subject currentUser = SecurityUtils.getSubject();
		// implement the session later
		Session sessionId = currentUser.getSession();
		System.out.println("currentUser.isAuthenticated(): " + currentUser.isAuthenticated());
		UsernamePasswordToken userNamePwtoken = new UsernamePasswordToken(userName, pw);
		if (!currentUser.isAuthenticated()) {
			currentUser.login(userNamePwtoken);
		}
		return userNamePwtoken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
}