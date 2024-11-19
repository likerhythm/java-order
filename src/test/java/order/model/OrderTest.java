package order.model;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OrderTest {

    @Test
    void serviceTest() {
        long quantity = 10L;
        Order order = new Order(new HashMap<>(Map.of(Menu.PIZZA, quantity)));
        OrderMenuDto dto = order.getOrderMenuDto();
        Map<String, OrderDetailDto> services = dto.getServices();
        long serviceCount = services.values().stream()
                .mapToLong(OrderDetailDto::getQuantity)
                .sum();
        Assertions.assertEquals(quantity, serviceCount);
    }

    @Test
    void lessThanLowerLimitOrderFee() {
        Menu pizza = Menu.PIZZA;
        Map<Menu, Long> orders = new HashMap<>(Map.of(pizza, 1L));
        Order order = new Order(orders);
        Assertions.assertThrows(IllegalArgumentException.class, order::calcFee);
    }

    @Test
    void noServiceTest() {
        Order order = new Order(new HashMap<>(Map.of(Menu.FRENCH_FRIES, 10L)));
        OrderMenuDto dto = order.getOrderMenuDto();
        Assertions.assertEquals(0, dto.getServices().size());
    }
}
