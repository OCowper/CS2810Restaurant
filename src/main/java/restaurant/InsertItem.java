package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Item {
    private String itemId;
    private String itemName;
    private double price;
    private String itemDescription;
    private String ingredients;
    private int calories;
    private String itemCategory;
    private boolean available;
    private String imagePath;

    public Item(String itemId, String itemName, double price, String itemDescription, String ingredients, int calories, String itemCategory, boolean available, String imagePath) {
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

public class InsertItem {

    public static void insert(Item item, Connection connection) {
        PreparedStatement stmt = null;
        String SQL = "INSERT INTO Items(item_id, item_name, price, item_description, ingredients, calories, item_category, available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            stmt = connection.prepareStatement(SQL);
            stmt.setString(1, item.getItemId());
            stmt.setString(2, item.getItemName());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getItemDescription());
            stmt.setString(5, item.getIngredients());
            stmt.setInt(6, item.getCalories());
            stmt.setString(7, item.getItemCategory());
            stmt.setBoolean(8, item.isAvailable());
            stmt.setString(9, item.getImagePath());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}