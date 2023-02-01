package restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing for the model class.
 *
 * @author zkac355
 */
public class RestModelTest {

  private RestModel testModel;
  private RestController testController;
  List<String> itemList = new ArrayList<String>();
  private Order testOrder;

  /**
   * setup for the test class.
   */
  @BeforeEach
  public void setup() {
    testController = new RestController();
    testModel = new RestModel(testController);
    itemList.add("cake");
    itemList.add("fanta");
    itemList.add("sweets");
    testOrder = new Order(itemList, 54, 27.60f);

  }

  @Test // test 1
  void testClass() {
    testModel = new RestModel();
  }

  @Test // test 2
  void testConst() {
    testModel = new RestModel(testController);
  }

  @Test // test 3
  void testGetOrder() {
    testController.update(testOrder);
    assertEquals(testController.returnModel().getOrder().getItems().get(0), "cake",
        "first item model should be cake");
  } // refactored to work with new order getters

}
