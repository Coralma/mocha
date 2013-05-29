package com.homepage.security;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.UUID;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.homepage.model.Account;
import com.homepage.model.AccountDao;
import com.homepage.model.User;
import com.homepage.model.UserDao;

public class SecuritySession extends AuthenticatedWebSession {

	private User user;
	private Account account;

	public static ThreadLocal<User> authenciatedUserStors = new ThreadLocal<User>();

	private HashMap<String, String> simpleMap = new HashMap<String, String>();
	/**
	 * Constructor
	 * 
	 * @param request
	 *            The current request object
	 */
	public SecuritySession(Request request) {
		super(request);

	}

	/**
	 * Checks the given username and password, returning a User object if if the
	 * username and password identify a valid user.
	 * 
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @return True if the user was authenticated
	 */
	@Override
	public final boolean authenticate(final String emailAddress, final String pw) {
		UserDao userDao = new UserDao();
		User user = userDao.findUserAndPw(emailAddress, pw);
		AccountDao accountDao = new AccountDao();
		Account account = accountDao.getAccountByUser(user);
		getAuthenciatedUserStors().remove();

		if (user != null && account != null) {
			setUser(user);
			setAccount(account);
			signIn(true);
			getAuthenciatedUserStors().set(user);
			return true;
		} else {
			// throw new EmptyStackException();
			return false;
		}
	}

	/**
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#getRoles()
	 */
	@Override
	public Roles getRoles() {
		if (isSignedIn()) {
			// If the user is signed in, they have these roles
			return new Roles(Roles.USER);
		}
		return null;
	}

	/**
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public static ThreadLocal<User> getAuthenciatedUserStors() {
		return authenciatedUserStors;
	}

	public static void setAuthenciatedUserStors(
			ThreadLocal<User> authenciatedUserStors) {
		SecuritySession.authenciatedUserStors = authenciatedUserStors;
	}

	public static User getAuthenciateUserByUserEmail(String userEmail) {

		// find bugs here.. can't get the userstor threadlocal value here
		if (getAuthenciatedUserStors().get() == null) {
			UserDao userDao = new UserDao();
			User user = userDao.findUserByEmail(userEmail);
			getAuthenciatedUserStors().set(user);
			return user;
		} else if (getAuthenciatedUserStors().get().getEmailAddress()
				.equals(userEmail)) {
			return getAuthenciatedUserStors().get();
		}
		return null;
	}

	public static SecuritySession get() {
		return (SecuritySession) Session.get();
	}

	public HashMap<String, String> getSimpleMap() {
		return simpleMap;
	}

	public void setSimpleMap(HashMap<String, String> simpleMap) {
		this.simpleMap = simpleMap;
	}

}