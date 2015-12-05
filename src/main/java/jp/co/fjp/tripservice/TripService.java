package jp.co.fjp.tripservice;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;

import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	@Autowired private TripDao tripDao;
	// get trip by user
	public List<Trip> getFriendTrips(User friend, User loggedInUser)
			throws UserNotLoggedInException,
			DependentClassCallDuringUnitTestException {
		// get user session then
		validate(loggedInUser);

		return friend.isFriendWith(loggedInUser) ? tripsBy(friend)
				: noTrips();

	}

	protected void validate(User loggedInUser)
			throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private List<Trip> tripsBy(User user)
			throws DependentClassCallDuringUnitTestException {
		return tripDao.tripsBy(user);
	}
}
