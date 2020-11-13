package A2;

import visitorPackage.Visitable;

/**
 * The interface for a twitter entry of the mini twitter application.
 * @author Payton Perchez
 */
public interface TwitterEntry extends Visitable {
	
	/**
	 * Returns the user's ID.
	 */
	@Override
	public abstract String toString();
	
}//end TwitterEntry
