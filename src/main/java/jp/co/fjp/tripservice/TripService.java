package jp.co.fjp.tripservice;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;

public class TripService {
	// get trip by user
	public List<Trip> getTripsByUser(User user, User loggedInUser)
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		// get user session then
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}

		return user.isFriendWith(loggedInUser) ? tripsBy(user)
				: noTrips();

	}

	protected ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user)
			throws DependentClassCallDuringUnitTestException {
		return TripDao.findTripByUser(user);
	}
}
