package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the LoginSubmit class.
 *
 * @author zkac355
 */
public class LoginSubmitTest {
  
  
  LoginSubmit testLogin;
  static Connection connection;
  Boolean flag;
  
  @BeforeAll
  static void connectionSetup() {
    connection = EstablishConnection.establishConnection();
  }
  
  @BeforeEach
  void setup() {
    testLogin = new LoginSubmit();
  }
  
  @Test // test 1
  void testClass() {
    testLogin = new LoginSubmit();
  } // test for class creation
  
  @Test // test 2
  void testMatch() {
    flag = LoginSubmit.submitLogin(connection, "12345", "pass1");
    assertEquals(flag, true, "flag should be true");
  }

}
