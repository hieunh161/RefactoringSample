package jp.co.fjp.tripservice;

import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.user.User;

public class TripDao {

	public static List<Trip> findTripByUser(User user)
			throws DependentClassCallDuringUnitTestException {
		// TODO Auto-generated method stub
		throw new DependentClassCallDuringUnitTestException("TripDao should not be invoked on an JUNIT test");
	}
}
