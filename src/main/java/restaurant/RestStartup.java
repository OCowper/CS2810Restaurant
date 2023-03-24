package restaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.Scanner;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Handles the necessary database setup for a first time run of the system.
 *
 * @author zkac355
 */
public class RestStartup {
  /**
   * Static method that initialises tables and fills them with data.
   *
   * @param connection current database connection
   */
  public static void startup(Connection connection, Scanner scanner) {
    String firstTime;
    System.out.println("run first time startup? (Y/N)");
    firstTime = scanner.nextLine();
    if (firstTime.equals("Y")) {
      System.out.println("running");
      String path = new File("").getAbsolutePath();
      String tablesPath = path + "/sql/ResturantTables.sql";
      File createsFile = new File(tablesPath);
      String insertsPath = path + "/sql/tempDbInserts.sql";
      File insertsFile = new File(insertsPath);
      ScriptRunner sr = new ScriptRunner(connection);
      Reader br;
      try {
        br = new BufferedReader(new FileReader(createsFile));
        sr.runScript(br);
        br.close();
        br = new BufferedReader(new FileReader(insertsFile));
        sr.runScript(br);
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("first time setup ran, please relaunch");
      System.exit(0);
    }
  }

}
