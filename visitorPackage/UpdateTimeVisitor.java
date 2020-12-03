package visitorPackage;

import A2.User;
import A2.UserGroup;
import A2.TwitterEntry;

/**
 * A visitor that finds the User that was updated most recently.
 * @author Payton Perchez
 */
public class UpdateTimeVisitor implements EntryVisitor {

	private User lastUpdatedUser;
	private long latestUpdateTime;
	
	/**
	 * Instantiates a new UpdateTimeVisitor and initializes the last update time to zero.
	 */
	public UpdateTimeVisitor() {
		
		latestUpdateTime = 0;
		
	}//end UpdateTimeVisitor
	
	/**
	 * Compares the visited user's last update time to the current, most recent update time.
	 * @param user The user being visited.
	 */
	@Override
	public void visitEntry(User user) {
		
		if(user.getLastUpdateTime() > latestUpdateTime) {
			
			latestUpdateTime = user.getLastUpdateTime();
			lastUpdatedUser = user;
			
		}//end if
		
	}//end visitEntry
	
	/**
	 * Visits the entries of the specified UserGroup.
	 * @param userGroup the UserGroup being visited.
	 */
	@Override
	public void visitEntry(UserGroup userGroup) {
		
		for(TwitterEntry entry : userGroup.getEntries()) {
			
			entry.accept(this);
			
		}//end for
		
	}//end visitEntry
	
	/**
	 * Returns the User that was most recently updated.
	 * @return The user that was most recently updated, or null if no users exist.
	 */
	public User getLastUpdatedUser() {
		
		return lastUpdatedUser;
		
	}//end getLastUpdatedUser

}//end UpdateTimeVisitor
