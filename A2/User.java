package A2;

import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import visitorPackage.EntryVisitor;

/**
 * Represents a user of the mini twitter application.
 * @author Payton Perchez
 */
public class User extends Subject implements Observer, TwitterEntry  {
	
	private String ID;
	private ListView<String> following;
	private ListView<String> feed;
	private long creationTime;
	private long lastUpdateTime;
	private Label updateTimeLabel;
	
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
		creationTime = System.currentTimeMillis();
		lastUpdateTime = creationTime;
		updateTimeLabel = new Label("Update time: " + lastUpdateTime + "ms");
		
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
	 * Returns the time in milliseconds at which the user was created.
	 * @return The time the user was created (in milliseconds).
	 */
	public long getCreationTime() {
		
		return creationTime;
		
	}//end getCreationTime
	
	/**
	 * Returns the time in milliseconds at which the user's feed was updated.
	 * @return The time the user's feed was updated (in milliseconds).
	 */
	public long getLastUpdateTime() {
		
		return lastUpdateTime;
		
	}//end getLastUpdateTime
	
	/**
	 * Returns a label containing the update time of the User.
	 * @return Label containing User's update time.
	 */
	public Label getUpdateTimeLabel() {
		
		return updateTimeLabel;
		
	}//end getUpdateTimeLabel
	
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
		lastUpdateTime = System.currentTimeMillis();
		updateTimeLabel.setText("Update time: " + lastUpdateTime + "ms");
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
			lastUpdateTime = System.currentTimeMillis();
			updateTimeLabel.setText("Update time: " + lastUpdateTime + "ms");
			
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
