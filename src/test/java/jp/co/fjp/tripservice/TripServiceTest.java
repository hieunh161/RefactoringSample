package jp.co.fjp.tripservice;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;

import org.junit.Test;

public class TripServiceTest {
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		TripService tripService = new TestableTripService();
		tripService.getTripsByUser(null);
	}
	
	private class TestableTripService extends TripService{
		@Override
		protected User getLoggedInUser(){
			return null;
			
		}
	}

}
