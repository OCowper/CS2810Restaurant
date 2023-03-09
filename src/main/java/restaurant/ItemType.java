package restaurant;

/**
 * An enum defining types of items.
 *
 * @author zkac355
 */
public enum ItemType {
  
  STARTER("Starters"), MAIN("Mains"), DRINK("Drinks"), DESSERT("Desserts");
  
  
  private String name;

  private ItemType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
