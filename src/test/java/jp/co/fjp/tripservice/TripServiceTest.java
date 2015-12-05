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
	private TripService tripService;

	@Before
	public void initialize() {
		tripService = new TestableTripService();
	}

	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_user_is_not_logged_in()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		tripService.getTripsByUser(UNUSED_USER, GUESS);
	}

	@Test
	public void should_not_return_any_trip_when_users_are_not_friends()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		User friend = UserBuilder.aUser().friendsWith(ANOTHER_USER)
				.withTrips(TO_BRAZIL).build();
		List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);
		Assert.assertThat(friendTrips.size(), Matchers.is(0));
	}

	@Test
	public void should_return_trips_when_users_are_friends()
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		User friend = UserBuilder.aUser()
				.friendsWith(ANOTHER_USER, REGISTERED_USER)
				.withTrips(TO_BRAZIL, TO_LONDON).build();
		List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERED_USER);
		Assert.assertThat(friendTrips.size(), Matchers.is(2));

	}

	private class TestableTripService extends TripService {

		@Override
		protected List<Trip> tripsBy(User user)
				throws DependentClassCallDuringUnitTestException {
			return user.trips();
		}
	}

}
