package visitorPackage;

import A2.TwitterEntry;
import A2.User;
import A2.UserGroup;

/**
 * A visitor that counts the number of users in the mini twitter application.
 * @author Payton Perchez
 */
public class CountUserVisitor implements EntryVisitor {

	private int count;
	
	/**
	 * Instantiates a new CountUserVisitor and initializes the count to zero.
	 */
	public CountUserVisitor() {
		
		count = 0;
		
	}//end default constructor
	
	/**
	 * Increments the user count.
	 * @param user The User being visited.
	 */
	@Override
	public void visitEntry(User user) {
		
		count++;
		
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
	 * Returns the number of users in the mini twitter application.
	 * @return The total number of users.
	 */
	public int getCount() {
		
		return count;
		
	}//end getCount

}//end CountUserVisitor
