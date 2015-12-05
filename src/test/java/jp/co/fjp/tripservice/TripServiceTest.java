package jp.co.fjp.tripservice;

import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	private static final User GUESS = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERED_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_LONDON = new Trip();
	private User loggedInUser;
	private TripService tripService;

	@Before
	public void initialize() {
		tripService = new TestableTripService();
		loggedInUser = REGISTERED_USER;
	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		loggedInUser = GUESS;
		tripService.getTripsByUser(UNUSED_USER);
	}

	@Test
	public void should_not_return_any_trip_when_users_are_not_friends()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(TO_BRAZIL);
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		Assert.assertThat(friendTrips.size(), Matchers.is(0));
	}

	@Test
	public void should_return_trips_when_users_are_friends()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addFriend(loggedInUser);
		friend.addTrip(TO_BRAZIL);
		friend.addTrip(TO_LONDON);
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		Assert.assertThat(friendTrips.size(), Matchers.is(2));

	}

	private class TestableTripService extends TripService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
		
		@Override
		protected List<Trip> tripsBy(User user)
				throws DependentClassCallDuringUnitTestException {
			return user.trips();
		}
	}

}
