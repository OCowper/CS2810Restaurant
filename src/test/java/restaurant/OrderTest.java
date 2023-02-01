package restaurant;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Testing for the author class.
 *
 * @author zkac355
 */
public class OrderTest {
  
  private Order testOrder;
  
  @Test // test 1
  void testClass() {
    testOrder = new Order();
  }
  
  @Test // test 2
  void testConstructor() {
    List<String> itemList = new ArrayList<String>();
    itemList.add("cake");
    itemList.add("fanta");
    itemList.add("sweets");
    testOrder = new Order(itemList, 54, 27.60f);
  }

}
