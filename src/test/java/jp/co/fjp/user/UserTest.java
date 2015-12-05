package jp.co.fjp.user;

import static org.junit.Assert.*;
import jp.co.fjp.tripservice.UserBuilder;
import org.hamcrest.Matchers;

import org.junit.Test;

public class UserTest {
	private static final User BOB = new User();
	private static final User PAUL = new User();

	@Test
	public void should_inform_when_users_are_not_friend() {
		User user = UserBuilder.aUser().friendsWith(BOB).build();
		assertThat(user.isFriendWith(PAUL), Matchers.is(false));
	}
	
	@Test
	public void should_inform_when_users_are_friend() throws Exception {
		User user = UserBuilder.aUser().friendsWith(BOB, PAUL).build();
		assertThat(user.isFriendWith(PAUL), Matchers.is(true));
	}
}
