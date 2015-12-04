package jp.co.fjp.tripservice;

import java.util.ArrayList;
import java.util.List;

import jp.co.fjp.exception.DependentClassCallDuringUnitTestException;
import jp.co.fjp.exception.UserNotLoggedInException;
import jp.co.fjp.user.User;
import jp.co.fjp.user.UserSession;

public class TripService {
	// get trip by user
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException, DependentClassCallDuringUnitTestException {
		List<Trip> tripList = new ArrayList<Trip>();
		// get user session then 
		User loggedUser = getLoggedInUser();
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

	protected User getLoggedInUser()
			throws DependentClassCallDuringUnitTestException {
		return UserSession.getInstance().getLoggedUser();
	}
}
