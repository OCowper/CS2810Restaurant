package restaurant;

/**
 * Represents the different types of requests possible.
 *
 * @author zkac355
 */
public enum Request {
  TABLE("table"), MENU("menu"), PAYMENT("payment"), EMERGENCY("emergency");
  
  private String name;

  private Request(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
