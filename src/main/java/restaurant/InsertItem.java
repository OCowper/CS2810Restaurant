package restaurant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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