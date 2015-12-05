package jp.co.fjp.tripservice;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.user.User;

import org.junit.Test;

public class TripDaoTest {
	@Test(expected = DependentClassCallDuringUnitTestException.class)
	public void should_throw_exception_when_retrieving_user_trips()
			throws Exception {
		new TripDao().tripsBy(new User());

	}
}
