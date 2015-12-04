package jp.co.fjp.user;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;

public class UserSession {

	private static final UserSession userSession = new UserSession();

	public static UserSession getInstance() {
		return userSession;
	}

	public User getLoggedUser() throws DependentClassCallDuringUnitTestException {
		throw new DependentClassCallDuringUnitTestException(
				"UserSession getLoggedUser should not called in unit test");
	}

}
