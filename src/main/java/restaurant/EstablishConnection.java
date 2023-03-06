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
    String staff = "CREATE TABLE IF NOT EXISTS Staff(\r\n"
        + "    staff_ID varchar(15) NOT NULL,\r\n"
        + "    password varchar(15) NOT NULL,\r\n"
        + "    first_Name varchar(15) NOT NULL,\r\n"
        + "    last_Name varchar(15) NOT NULL, \r\n"
        + "    job_Title varchar(15) NOT NULL,\r\n"
        + "    email varchar(15) NOT NULL,\r\n"
        + "    phone_Number varchar(15),\r\n"
        + "    address varchar(15),\r\n"
        + "    primary key (staff_ID)\r\n"
        + ");";
    Operations.executeProcedure(connection, staff);
    String insert1 = "insert into staff values('100575405', 'pass1', 'Oscar', 'Cowper', 'Waiter', 'oscar.cowper', '977856', 'new road');";
    Operations.executeProcedure(connection, insert1);
    return connection;
  }
}

