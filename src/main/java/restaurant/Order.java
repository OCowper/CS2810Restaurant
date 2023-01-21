package restaurant;

/**
 * Data storage class to collate all information about an order.
 *
 * @author zkac355
 */
public class Order {
  
  private String[] items;
  private int tableNum;
  private float total;
  
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

}
