package jp.co.fjp.user;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.tripservice.Trip;

public class User {
	List<User> friends = new ArrayList<User>();
	
	List<Trip> trips = new ArrayList<Trip>();
	
	

	public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User anotherUser) {
		friends.add(anotherUser);
		
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> trips() {
		return trips;
	}

	public boolean isFriendWith(User anotherUser) {
		return friends.contains(anotherUser);
	}
	

}
