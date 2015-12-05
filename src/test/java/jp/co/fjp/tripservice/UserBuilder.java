package jp.co.fjp.tripservice;

import jp.co.fjp.user.User;

public class UserBuilder {
	private User[] friends = new User[] {};
	private Trip[] trips = new Trip[] {};

	public static UserBuilder aUser() {
		return new UserBuilder();
	}

	public UserBuilder withTrips(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public UserBuilder friendsWith(User... friends) {
		this.friends = friends;
		return this;
	}

	public User build() {
		User user = new User();
		addTripsTo(user);
		addFriendTo(user);
		return user;
	}

	private void addTripsTo(User user) {
		for (Trip trip : trips) {
			user.addTrip(trip);
		}
	}

	private void addFriendTo(User user) {
		for (User friend : friends) {
			user.addFriend(friend);
		}
	}

}