package restaurant;

/**
 * Defines the structure of a new item.
 *
 * @author zkac355, Tomor Voshki
 */
public class Item {

  private int itemId;
  private String itemName;
  private Float price;
  private String itemDescription;
  private String ingredients;
  private int calories;
  private String itemCategory;
  private boolean available;
  private String imagePath;

  /**
   * Constructs an instance of item.
   *
   * @param itemId unique item of an item
   * @param itemName name of an item
   * @param price the item's cost
   * @param itemDescription description of the item
   * @param ingredients ingredients of the item
   * @param calories item's calories
   * @param itemCategory type of the item
   * @param available whether the item is in stock
   * @param imagePath the image path of the item's picture
   */
  public Item(int itemId, String itemName, Float price, String itemDescription, String ingredients,
      int calories, String itemCategory, boolean available, String imagePath) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.price = price;
    this.itemDescription = itemDescription;
    this.ingredients = ingredients;
    this.calories = calories;
    this.itemCategory = itemCategory;
    this.available = available;
    this.imagePath = imagePath;
  }

  // Getters and setters

  /**
   * Returns the item's id.
   *
   * @return id.
   */
  public int getItemId() {
    return itemId;
  }

  /**
   * returns the item's name.
   *
   * @return name
   */
  public String getItemName() {
    return itemName;
  }


  /**
   * returns the item's price.
   *
   * @return price
   */
  public Float getPrice() {
    return price;
  }


  /**
   * returns the item's description.
   *
   * @return description
   */
  public String getItemDescription() {
    return itemDescription;
  }


  /**
   * returns the item's ingredients.
   *
   * @return ingredients
   */
  public String getIngredients() {
    return ingredients;
  }

  /**
   * returns the calories in an item.
   *
   * @return calories
   */
  public int getCalories() {
    return calories;
  }


  /**
   * returns the category of an item.
   *
   * @return category
   */
  public String getItemCategory() {
    return itemCategory;
  }

  /**
   * returns if the item is in stock or not.
   *
   * @return availabiltiy
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * returns the item's image path.
   *
   * @return image path
   */
  public String getImagePath() {
    return imagePath;
  }

}
