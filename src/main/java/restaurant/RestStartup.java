package restaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import org.apache.ibatis.jdbc.ScriptRunner;

public class RestStartup {
  public static void startup(Connection connection) {
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
  }

}
