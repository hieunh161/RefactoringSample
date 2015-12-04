package jp.co.fjp.tripservice;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;
import jp.co.fjp.user.UserSession;

public class TripService {
	// get trip by user
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		// get user session then 
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if(loggedUser != null){
			for(User friend : user.getFriends()){
				if(friend.equals(loggedUser)){
					isFriend = true;
					break;
				}
			}
			if(isFriend){
				tripList = TripDao.findTripByUser(user);
			}
			return tripList;
		}else{
			throw new UserNotLoggedInException();
		}
	}
}
