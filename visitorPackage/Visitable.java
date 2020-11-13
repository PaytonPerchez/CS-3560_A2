package visitorPackage;

/**
 * An interface for objects that can be visited.
 * @author Payton Perchez
 */
public interface Visitable {

	/**
	 * Allows the visitor to perform an operation on the node.
	 * @param visitor The visitor visiting the node.
	 */
	public abstract void accept(EntryVisitor visitor);
	
}//end Visitable
