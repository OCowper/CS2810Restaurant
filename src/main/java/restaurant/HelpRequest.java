package restaurant;

/**
 * Stores information about a submitted request for help by a customer.
 *
 * @author zkac355
 */
public class HelpRequest {

  // private fields containing information about the request. immutable.
  private int tableNum;
  private Request requestType;

  /**
   * Constructs an instance of HelpRequest.
   *
   * @param tableNum the table number it came from
   * @param requestType type of the request
   */
  public HelpRequest(int tableNum, Request requestType) {
    this.tableNum = tableNum;
    this.requestType = requestType;
  }

  /**
   * Returns the table number of the request.
   *
   * @return the current table num
   */
  public int getTableNum() {
    return tableNum;
  }

  /**
   * Returns the request type.
   *
   * @return the current request type
   */
  public Request requestType() {
    return requestType;
  }
}
