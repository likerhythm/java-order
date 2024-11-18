package order;

import java.util.Map;

public class DeliveryFeeCalculator {

    private final Map<FeeRange, Long> rules;

    public DeliveryFeeCalculator(final Map<FeeRange, Long> rules) {
        this.rules = rules;
    }

    public long calculateFee(final long orderCost) {
        return rules.keySet().stream()
                .filter(feeRange -> feeRange.isInRange(orderCost))
                .map(rules::get)
                .findFirst()
                .orElseThrow();
    }
}
