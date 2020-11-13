package visitorPackage;

import A2.TwitterEntry;
import A2.User;
import A2.UserGroup;
import javafx.collections.ObservableList;

/**
 * A visitor that determines the percentage of positive messages sent by users of the mini twitter application.
 * @author Payton Perchez
 */
public class PositiveMessageVisitor implements EntryVisitor {

	private int positiveCount;
	private int messageCount;
	
	/**
	 * Instantiates a new PositiveMessageVisitor and initializes the positive message count and the message count to zero.
	 */
	public PositiveMessageVisitor() {
		
		positiveCount = 0;
		messageCount = 0;
		
	}//end default constructor
	
	/**
	 * Counts the number of positive messages sent by the specified User.
	 * @param user The User being visited.
	 */
	@Override
	public void visitEntry(User user) {
		
		ObservableList<String> messages = user.getFeed().getItems();
		String currentMessage;
		
		for(int i = 0; i < messages.size(); i++) {
			
			currentMessage = messages.get(i);
			
			//Only count the messages sent by the user
			if(currentMessage.startsWith(" - You")) {
				
				messageCount++;
				
				//Criteria for a positive message
				if(currentMessage.contains("cool") || currentMessage.contains("awesome") || currentMessage.contains("happy") || 
				   currentMessage.contains("fun")  || currentMessage.contains("exciting"))
				{
					positiveCount++;
					
				}//end if
				
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
	 * Returns the percentage of positive messages to total messages sent by users of the mini twitter application.
	 * @return The percentage of positive messages to the total number of messages.
	 */
	public float getPositivePercentage() {
		
		//Cannot divide by zero
		if(messageCount == 0) {
			
			return 0;
			
		}else {
			
			return positiveCount/(float)messageCount*100;
			
		}//end if
		
	}//end getCount

}//end PositiveMessageVisitor
