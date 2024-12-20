package order.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OrderConverterTest {

    @Test
    void onlyDrinkTest() {
        OrderConverter orderConverter = new OrderConverter();
        Map<String, String> menuNameAndQuantity = Map.of("콜라", "1");
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderConverter.convert(menuNameAndQuantity));
    }

    @Test
    void emptyOrderTest() {
        OrderConverter orderConverter = new OrderConverter();
        Map<String, String> menuNameAndQuantity = Map.of("피자", "0");
        Assertions.assertEquals(0, orderConverter.convert(menuNameAndQuantity).getOrderMenuDto().getOrders().size());
    }
}
