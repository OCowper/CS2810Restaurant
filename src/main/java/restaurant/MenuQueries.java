package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuQueries
{
    /**
    * Returns the menu as an ArrayList.
    *
    * @param connection current database connection
    * @return Menu items as ArrayList.
    *
    */
    public static ArrayList<String> viewMenu(Connection connection)
    {

     ArrayList<String> itemList = new ArrayList<String>();
     String getItems = "SELECT * FROM menu;";
    
        try
        {
            ResultSet rs = Operations.executeQuery(connection, getItems);
            while(rs.next())
            {
               itemList.add(rs.getString("name"));
            }
        }
        catch (Exception e)
        {
         e.printStackTrace();
        } 
      return itemList;
    
    }
    
    public static ArrayList<String> filterMenu(Connection connection, int priceCap)
    {
      // find serial number of all items in menu_items with a lower or even price to price cap
      // from the serial number find the name of the item from the menu table
      // return all the names in an arrayList.
    }
}
