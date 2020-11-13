package visitorPackage;

import A2.User;
import A2.UserGroup;

/**
 * The interface for visitors that visit twitter entries in the mini twitter application.
 * @author Payton Perchez
 */
public interface EntryVisitor {
	
	/**
	 * Visits the specified User.
	 * @param user The User being visited.
	 */
	public abstract void visitEntry(User user);
	
	/**
	 * Visits the specified UserGroup.
	 * @param userGroup the UserGroup being visited.
	 */
	public abstract void visitEntry(UserGroup userGroup);

}//end EntryVisitor
