package restaurant;

/**
 * Interface defining subjects (objects watched by observers).
 *
 * @author zkac355
 */
public interface Subject {
  
  /**
   * Ads an observer to view it.
   *
   * @param obs the observer to be added
   */
  public void addObservers(Observer obs);
  
  /**
   * Notifies observers of any changes.
   *
   * @param obs the observer to be notified
   */
  public void notifyObservers(Observer obs);

}
