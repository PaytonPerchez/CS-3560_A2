package A2;

import java.util.ArrayList;

/**
 * Represents a subject that can be observed and can notify its observers.
 * @author Payton Perchez
 */
public abstract class Subject {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * Adds the specified observer to the list of observers.
	 * @param observer The given observer.
	 */
	public void attach(Observer observer) {
		
		observers.add(observer);
		
	}//end attach
	
	/**
	 * Removes the specified observer from the list of observers.
	 * @param observer The given observer.
	 */
	public void detach(Observer observer) {
		
		observers.remove(observer);
		
	}//end detach
	
	/**
	 * Notifies all of the observers of changes that have been made.
	 */
	public void notifyObservers() {
		
		for(Observer observer : observers) {
			
			observer.update(this);
			
		}//end for
		
	}//end notifyObservers
	
}//end Subject
