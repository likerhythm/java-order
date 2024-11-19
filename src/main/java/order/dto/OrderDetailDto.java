package order.dto;

public class OrderDetailDto {

    private final long quantity;
    private final long fee;

    public OrderDetailDto(long quantity, long fee) {
        this.quantity =quantity;
        this.fee = fee;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getFee() {
        return fee;
    }
}
