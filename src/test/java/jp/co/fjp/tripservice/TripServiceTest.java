package jp.co.fjp.tripservice;

import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TripServiceTest {
	private static final User GUESS = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private User loggedInUser;

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		TripService tripService = new TestableTripService();
		loggedInUser = GUESS;
		tripService.getTripsByUser(UNUSED_USER);
	}

	@Test
	public void should_not_return_any_trip_when_users_are_not_friends()
			throws Exception {
		TripService tripService = new TestableTripService();
		loggedInUser = REGISTERED_USER;
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(TO_BRAZIL);
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		Assert.assertThat(friendTrips.size(), Matchers.is(0));
	}

	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;

		}
	}

}
