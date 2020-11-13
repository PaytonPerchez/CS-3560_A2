package A2;

import javafx.scene.control.ListView;
import visitorPackage.EntryVisitor;

/**
 * Represents a user of the mini twitter application.
 * @author Payton Perchez
 */
public class User extends Subject implements Observer, TwitterEntry  {
	
	private String ID;
	private ListView<String> following;
	private ListView<String> feed;
	
	/**
	 * Instantiates a new user with the given ID.
	 * @param userID The user's ID.
	 */
	public User(String userID) {
		
		ID = userID;
		following = new ListView<>();
		following.getItems().add("Current Following");
		feed = new ListView<>();
		feed.getItems().add("News Feed");
		
	}//end constructor
	
	/**
	 * Returns the user's feed as a ListView<String> object.
	 * @return The user's feed.
	 */
	public ListView<String> getFeed() {
		
		return feed;
		
	}//end getFeed
	
	/**
	 * Returns the list of ID's of the users that the user follows.
	 * @return The user ID's that the user follows.
	 */
	public ListView<String> getFollowing() {
		
		return following;
		
	}//end getFollowing
	
	/**
	 * Returns the latest tweet the user made.
	 * @return The latest tweet from the user.
	 */
	public String getLatestTweet() {
		
		return feed.getItems().get(feed.getItems().size() - 1);
		
	}//end getLatestTweet
	
	/**
	 * Posts a tweet to the user's feed as well as the feeds of its followers.
	 * @param message The tweet being posted.
	 */
	public void tweet(String message) {
		
		feed.getItems().add(" - You: " + message);
		notifyObservers();
		
	}//end tweet
	
	/**
	 * Attaches the user to the specified subject.
	 * @param subject The subject the user is observing.
	 */
	public void follow(Subject subject) {
		
		if(subject instanceof User) {
			
			subject.attach(this);
			following.getItems().add(subject.toString());
			
		}//end if
		
	}//end follow
	
	/**
	 * Updates the user's feed after a tweet has been posted from a subject they are observing.
	 * @param subject The subject they are observing.
	 */
	@Override
	public void update(Subject subject) {
		
		if(subject instanceof User) {
			
			feed.getItems().add(" - " + subject.toString() + ": " + ((User)subject).getLatestTweet().substring(7));
			
		}//end if
		
	}//end update
	
	/**
	 * Allows the visitor to perform an operation on the user.
	 * @param visitor The visitor visiting the user.
	 */
	@Override
	public void accept(EntryVisitor visitor) {
		
		visitor.visitEntry(this);
		
	}//end accept
	
	/**
	 * Returns the User's ID.
	 */
	@Override
	public String toString() {
		
		return ID;
		
	}//end toString
	
}//end User
