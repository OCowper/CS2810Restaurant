package restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class defining SQL operations - one with and one without return values.
 *
 * @author zkac355
 */
public class Operations {

  /**
   * Executes SQL statements without return values.
   *
   * @param connection the current database connection
   * @param operation the current SQL statement to be executed
   */
  public static void executeProcedure(Connection connection, String operation) {
    try {
      Statement statement = connection.createStatement();
      statement.execute(operation);
      statement.close();
    } catch (SQLException e) {
      System.out.println("statement error");
    }
  }

  /**
   * Executes SQL statements with a return value.
   *
   * @param connection the current database connection
   * @param query the SQL statement to be executed
   * @return the result of that statement
   */
  public static ResultSet executeQuery(Connection connection, String query) {
    ResultSet results = null;
    try {
      Statement statement = connection.createStatement();
      results = statement.executeQuery(query);
    } catch (SQLException e) {
      System.out.println("statement error");
    }
    return results;
  }

}
