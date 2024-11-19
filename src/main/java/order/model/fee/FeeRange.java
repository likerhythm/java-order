package order.model.fee;

public class FeeRange {

    private final long min;
    private final long max;

    public FeeRange(final long min, final long max) {
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(final long orderCost) {
        return min <= orderCost && orderCost <= max;
    }
}
