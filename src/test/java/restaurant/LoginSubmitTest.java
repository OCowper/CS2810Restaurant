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

  @Test // test 3
  void testDb() {
    flag = LoginSubmit.submitLogin(connection, "12345", "pass1");
    assertEquals(flag, true, "flag should be true");
  }

  @Test // test 3
  void testIncorrect() {
    flag = LoginSubmit.submitLogin(connection, "falseUsrn", "falsePswd");
    assertEquals(flag, false, "program should return false for an incorrect user and password");
  }

  @Test // test 4
  void testIncorrectPswd() {
    flag = LoginSubmit.submitLogin(connection, "12345", "falsePswd");
    assertEquals(flag, false,
        "program should return false for the right user but the wrong password");
  }

  @Test // test 5
  void testIncorrectUsr() {
    flag = LoginSubmit.submitLogin(connection, "falseUsrn", "pass1");
    assertEquals(flag, false,
        "program should return false for the wrong user but a password that exists");
  }

}
