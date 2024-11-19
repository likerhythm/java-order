package order.model;

import order.model.fee.FeeCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FeeCalculatorTest {

    FeeCalculator feeCalculator = new FeeCalculator();

    @Test
    void lessThanLowerLimitOrderFee() {
        OrderMenu pizza = OrderMenu.PIZZA;
        Map<OrderMenu, Long> orders = Map.of(pizza, 1L);
        Order order = new Order(orders);
        Assertions.assertThrows(IllegalArgumentException.class, () -> feeCalculator.calcOrderFee(order));
    }
}
