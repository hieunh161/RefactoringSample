package jp.co.fjp.user;

import jp.co.fjp.exception.UserNotLoggedInException;

public class UserSession {

	public static UserSession getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getLoggedUser() throws UserNotLoggedInException{
		throw new UserNotLoggedInException();
	}

}
