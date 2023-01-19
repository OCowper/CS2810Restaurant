package restaurant;

/**
 * Data storage class to collate all information about an order.
 *
 * @author zkac355
 */
public class Order {
  
  public String[] items;
  public int tableNum;
  public float total;
  
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

}
