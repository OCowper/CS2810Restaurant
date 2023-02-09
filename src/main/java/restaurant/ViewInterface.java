package restaurant;

/**
 * Interface representing any view used to display to the user.
 *
 * @author zkac355
 */
public interface ViewInterface {
  
  /**
   * Adds an observer to the view.
   *
   * @param obs the observer to be added.
   */
  public void addObservers(Observer obs);
  
  /**
   * Notifies observers of any changes.
   *
   * @param obs the observer to be notified.
   */
  public void notifyObservers(Observer obs);
  
  /**
   * Accepts a boolean value.
   *
   * @param bool the value to be accepted
   */
  public void acceptBoolean(Boolean bool);

}
