package jp.co.fjp.tripservice;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;
import jp.co.fjp.user.UserSession;

public class TripService {
	// get trip by user
	public List<Trip> getTripsByUser(User user)
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		// get user session then
		if (getLoggedInUser() == null) {
			throw new UserNotLoggedInException();
		}

		return user.isFriendWith(getLoggedInUser()) ? tripsBy(user)
				: noTrips();

	}

	protected ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user)
			throws DependentClassCallDuringUnitTestException {
		return TripDao.findTripByUser(user);
	}

	protected User getLoggedInUser()
			throws DependentClassCallDuringUnitTestException {
		return UserSession.getInstance().getLoggedUser();
	}
}
