package visitorPackage;

import A2.TwitterEntry;
import A2.User;
import A2.UserGroup;

/**
 * A visitor that counts the number of groups there are on the mini twitter application.
 * @author Payton Perchez
 */
public class CountUserGroupVisitor implements EntryVisitor {

	private int count;
	
	/**
	 * Instantiates a new CountUserGroupVisitor and initializes the count to zero.
	 */
	public CountUserGroupVisitor() {
		
		count = 0;
		
	}//end default constructor
	
	/**
	 * Does nothing.
	 * @param user The User being visited.
	 */
	@Override
	public void visitEntry(User user) {
		
	}//end visitEntry

	/**
	 * Increments the group count and visits the entries of the specified UserGroup.
	 * @param userGroup the UserGroup being visited.
	 */
	@Override
	public void visitEntry(UserGroup userGroup) {
		
		count++;
		
		for(TwitterEntry entry : userGroup.getEntries()) {
			
			entry.accept(this);
			
		}//end for
		
	}//end visitEntry
	
	/**
	 * Returns the number of groups in the mini twitter application.
	 * @return The total number of groups.
	 */
	public int getCount() {
		
		return count;
		
	}//end getCount

}//end CountUserGroupVisitor
