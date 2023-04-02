import org.example.Main;
import org.example.Orders;
import org.example.OrdersAssignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;

public class MainTests {
    private static final String ORDERS_FILE = "orders.json";
    private static final String STORE_FILE = "store.json";

    @BeforeAll
    public static void setUp() throws FileNotFoundException {
        File ordersFile = new File(ORDERS_FILE);
        if (!ordersFile.exists()) {
            throw new FileNotFoundException("The orders file does not exist!");
        }
        File storeFile = new File(STORE_FILE);
        if (!storeFile.exists()) {
            throw new FileNotFoundException("The store file does not exist!");
        }
    }

    @Test
    public void testMain() {
        Main.main(new String[]{});
    }

    @Test
    public void testOrders() {
        Orders order1 = new Orders("1", "10", 5, "10:05");
        Orders order2 = new Orders("2", "20", 10, "10:15");
        Orders order3 = new Orders("3", "30", 15, "10:30");
        Assertions.assertEquals(order1.getOrderId(), "1");
        Assertions.assertEquals(order2.getOrderValue(), "20");
        Assertions.assertEquals(order3.getPickingTime(), 15);
        Assertions.assertEquals(order1.getCompleteBy(), "10:05");
    }

    @Test
    public void testOrdersAssignment() {
        OrdersAssignment oa = new OrdersAssignment("P1", "1", "10:05");
        Assertions.assertEquals(oa.getPicker(), "P1");
        Assertions.assertEquals(oa.getOrderId(), "1");
        Assertions.assertEquals(oa.getTime(), "10:05");
    }

}
