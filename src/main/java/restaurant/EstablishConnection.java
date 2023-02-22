package restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class containing methods to establish a database connection.
 *
 * @author zkac355
 */
public class EstablishConnection {
  /**
   * Establishes a connection to the database based on user id and password. Static.
   */
  public static Connection establishConnection() {

    Scanner userInput = new Scanner(System.in);
    System.out.println("Enter your username: ");
    String user = userInput.nextLine();
    System.out.println("Enter your password: ");
    String password = userInput.nextLine();
    String database = "localhost:5432/";

    userInput.close();

    System.out.println("Attempting Connection");
    Connection connection = null;
    try {
      String protocol = "jdbc:postgresql://";
      String fullUrl = protocol + database + "postgres";
      connection = DriverManager.getConnection(fullUrl, user, password);
      System.out.println(fullUrl);
    } catch (SQLException e) {
      String errorMsg = e.getMessage();
      if (errorMsg.contains("authentication failed")) {
        System.out.println("ERROR: Incorrect password");
      } else {
        System.out.println("Connection failure");
        e.printStackTrace();
      }
    }
    if (connection != null) {
      System.out.println("connection successful");
    } else {
      System.out.println("connection failed");
      System.exit(1);
    }
    //temp(connection);
    return connection;
  }

//  private static void temp(Connection conn) {
//    String[] inserts = {
//        "INSERT INTO Menu VALUES ('30', 'Latte', 'Blahblahblah' ,'peanuts', '50', 'Drinks');",
//        "INSERT INTO Menu VALUES ('31', 'Tea', 'Blahblahblah' ,'na', '50', 'Drinks');",
//        "INSERT INTO Menu VALUES ('32', 'Fries', 'Blahblahblah' ,'peanuts', '50', 'Starters');",
//        "INSERT INTO Menu VALUES ('33', 'Hallumi', 'Blahblahblah' ,'milk', '50', 'Starters');",
//        "INSERT INTO Menu VALUES ('34', 'Mozarella', 'Blahblahblah' ,'milk', '50', 'Starters');",
//        "INSERT INTO Menu VALUES ('35', 'Steak', 'Blahblahblah' ,'peanuts', '50', 'Mains');",
//        "INSERT INTO Menu VALUES ('36', 'Pasta', 'Blahblahblah' ,'peanuts', '50', 'Mains');",
//        "INSERT INTO Menu VALUES ('37', 'Risotto', 'Blahblahblah' ,'peanuts', '50', 'Mains');",
//        "INSERT INTO Menu VALUES ('38', 'Paella', 'Blahblahblah' ,'peanuts', '50', 'Mains');"};
//
//    String menuTable = "CREATE TABLE IF NOT EXISTS Menu(" + "    item_Num varchar(15),"
//        + "    item_Name varchar(30)," + "    item_Description varchar(1000),"
//        + "    alligens varchar(1000)," + "    preparation_Time varchar(100),"
//        + "    item_Type varchar(100), /* drink / starters / mains ect */"
//        + "    primary key (item_Num)" + ");";
//    Operations.executeProcedure(conn, menuTable);
//    for (String str : inserts) {
//      Operations.executeProcedure(conn, str);
//    }
//  }
}

