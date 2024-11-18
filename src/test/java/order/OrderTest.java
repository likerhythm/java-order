package order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderTest {

    @Test
    void serviceTest() {
        Order order = new Order(new HashMap<>(Map.of(OrderMenu.PIZZA, 1L)));
        ReceiptOrderDto dto = order.getReceiptDto();
        Assertions.assertEquals(1, dto.getService().size());
    }
}
