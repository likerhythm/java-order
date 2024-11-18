package order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OrderConverterTest {

    @Test
    void onlyDrinkTest() {
        OrderConverter orderConverter = new OrderConverter();
        Map<String, Long> menuNameAndQuantity = Map.of("콜라", 1L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderConverter.convert(menuNameAndQuantity));
    }
}
