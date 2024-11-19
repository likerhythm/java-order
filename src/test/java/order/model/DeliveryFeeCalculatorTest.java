package order.model;

import order.model.fee.DeliveryFeeCalculator;
import order.model.fee.FeeRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

public class DeliveryFeeCalculatorTest {

    DeliveryFeeCalculator deliveryFeeCalculator;

    @BeforeEach
    void initRules() {
        Map<FeeRange, Long> rules = Map.of(
                new FeeRange(0, 49999), 2000L,
                new FeeRange(50000, 99999), 1000L,
                new FeeRange(100000, Long.MAX_VALUE), 0L
        );
        deliveryFeeCalculator = new DeliveryFeeCalculator(rules);
    }

    @ParameterizedTest
    @ValueSource(longs = {
            0,
            49999
    })
    void rule2000Test(long orderCost) {
        Assertions.assertEquals(2000, deliveryFeeCalculator.calculateFee(orderCost));
    }

    @ParameterizedTest
    @ValueSource(longs = {
            50000,
            99999
    })
    void rule1000Test(long orderCost) {
        Assertions.assertEquals(1000, deliveryFeeCalculator.calculateFee(orderCost));
    }

    @ParameterizedTest
    @ValueSource(longs = {
            100000
    })
    void ruleFreeTest(long orderCost) {
        Assertions.assertEquals(0, deliveryFeeCalculator.calculateFee(orderCost));
    }
}
