package A2;

/**
 * The interface for observers of subjects.
 * @author Payton Perchez
 */
public interface Observer {

	/**
	 * Update the observer's information regarding the specified subject.
	 * @param subject The updated subject.
	 */
	public abstract void update(Subject subject);
	
}//end Observer
