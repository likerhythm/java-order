package order.model.fee;

import java.util.Map;

public class DeliveryFeeCalculator {

    private final Map<FeeRange, Long> rules;

    public DeliveryFeeCalculator(final Map<FeeRange, Long> rules) {
        this.rules = rules;
    }

    public long calculateFee(final long orderFee) {
        return rules.keySet().stream()
                .filter(feeRange -> feeRange.isInRange(orderFee))
                .map(rules::get)
                .findFirst()
                .orElseThrow();
    }
}
