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
    String database = "teachdb.cs.rhul.ac.uk";

    userInput.close();

    System.out.println("Attempting Connection");
    Connection connection = null;
    try {
      String protocol = "jdbc:postgresql://";
      String databaseName = "/CS2855%2F";
      String fullUrl = protocol + database + databaseName + user;
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
    return connection;

  }


}
