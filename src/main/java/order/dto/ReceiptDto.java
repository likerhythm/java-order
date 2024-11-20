package order.dto;

public class ReceiptDto {

    private final OrderMenuDto orderMenuDto;
    private final long deliveryFee;
    private final long orderFee;
    private final long totalFee;

    public ReceiptDto(OrderMenuDto orderMenuDto, long deliveryFee, long orderFee, long totalFee) {
        this.orderMenuDto = orderMenuDto;
        this.deliveryFee = deliveryFee;
        this.orderFee = orderFee;
        this.totalFee = totalFee;
    }

    public OrderMenuDto getOrderMenuDto() {
        return orderMenuDto;
    }

    public long getDeliveryFee() {
        return deliveryFee;
    }

    public long getOrderFee() {
        return orderFee;
    }

    public long getTotalFee() {
        return totalFee;
    }
}
