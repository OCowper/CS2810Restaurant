package restaurant;

/**
 * Data storage class to collate all information about an order.
 *
 * @author zkac355
 */
public class Order {
  
  // fields representing information about an order. confirmed is the only mutable.
  private String[] items;
  private int tableNum;
  private float total;
  private Boolean confirmed = false;
  
  /**
   * Defines the empty constructor for Order.
   */
  public Order() {}
  
  /**
   * Defines a constructor for Order with all data.
   *
   * @param items a list of items orders
   * @param tableNum the number of the table ordered from
   * @param total total cost of the order
   */
  public Order(String[] items, int tableNum, float total) {
    this.items = items;
    this.tableNum = tableNum;
    this.total = total;
  }
  
  /**
   * Returns the list of items.
   *
   * @return list of items ordered by the customer.
   */
  public String[] getItems() {
    return items;
  }
  
  /**
   * Returns the table number.
   *
   * @return the table where the customer is sitting.
   */
  public int getTableNum() {
    return tableNum;
  }
  
  /**
   * Returns the total cost of the order.
   *
   * @return the total cost.
   */
  public float getTotal() {
    return total;
  }
  
  /**
   * Returns whether or not the order is currently confirmed.
   *
   * @return true if confirmed, false if not.
   */
  public boolean getConfirmed() {
    return confirmed;
  }
  
  /**
   * Sets the confirmation value of an order.
   *
   * @param confirmed true if confirmed, false if not.
   */
  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
  }

}
