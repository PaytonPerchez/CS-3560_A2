package A2;

import java.util.ArrayList;
import visitorPackage.EntryVisitor;

/**
 * Represents a group of users in the mini twitter application.
 * @author Payton Perchez
 */
public class UserGroup implements TwitterEntry {

	private String ID;
	private ArrayList<TwitterEntry> users;
	private long creationTime;
	
	/**
	 * Instantiates a new UserGroup with the specified ID.
	 * @param groupID The group's ID.
	 */
	public UserGroup(String groupID) {
		
		ID = groupID;
		users = new ArrayList<>();
		creationTime = System.currentTimeMillis();
		
	}//end constructor
	
	/**
	 * Adds a new entry into the group.
	 * @param newEntry The entry being added.
	 */
	public void addEntry(TwitterEntry newEntry) {
		
		users.add(newEntry);
		
	}//end addEntry
	
	/**
	 * Returns the list of entries contained by the group.
	 * @return The group's entries.
	 */
	public ArrayList<TwitterEntry> getEntries() {
		
		return users;
		
	}//end getEntries
	
	/**
	 * Returns the time in milliseconds at which the group was created.
	 * @return The time the group was created (in milliseconds).
	 */
	public long getCreationTime() {
		
		return creationTime;
		
	}//end getCreationTime
	
	/**
	 * Allows the visitor to perform an operation on the group.
	 * @param visitor The visitor visiting the group.
	 */
	@Override
	public void accept(EntryVisitor visitor) {
		
		visitor.visitEntry(this);
		
	}//end accept
	
	/**
	 * Returns the UserGroup's ID.
	 */
	@Override
	public String toString() {
		
		return ID;
		
	}//end toString
	
}//end UserGroup
