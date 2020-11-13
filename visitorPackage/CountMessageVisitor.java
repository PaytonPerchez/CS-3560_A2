package visitorPackage;

import A2.TwitterEntry;
import A2.User;
import A2.UserGroup;
import javafx.collections.ObservableList;

/**
 * A visitor that counts the number of tweets users have posted.
 * @author Payton Perchez
 */
public class CountMessageVisitor implements EntryVisitor {

	private int count;
	
	/**
	 * Instantiates a new CountMessageVisitor and initializes the count to zero.
	 */
	public CountMessageVisitor() {
		
		count = 0;
		
	}//end default constructor
	
	/**
	 * Counts the number of tweets made by the specified User.
	 * @param user The User being visited.
	 */
	@Override
	public void visitEntry(User user) {
		
		ObservableList<String> messages = user.getFeed().getItems();
		
		for(int i = 0; i < messages.size(); i++) {
			
			//Only count the messages sent by the user
			if((messages.get(i)).startsWith(" - You")) {
				
				count++;
				
			}//end if
			
		}//end for
		
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
	 * Returns the number of tweets sent by all mini twitter users.
	 * @return The total number of tweets.
	 */
	public int getCount() {
		
		return count;
		
	}//end getCount
	
}//end CountMessageVisitor
