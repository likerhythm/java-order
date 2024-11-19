package order.model;

import order.dto.OrderMenuDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderTest {

    @Test
    void serviceTest() {
        Order order = new Order(new HashMap<>(Map.of(Menu.PIZZA, 1L)));
        OrderMenuDto dto = order.getOrderMenuDto();
        Assertions.assertEquals(1, dto.getServices().size());
    }
}
