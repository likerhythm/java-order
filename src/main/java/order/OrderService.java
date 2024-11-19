package order;

import order.model.Order;
import order.model.fee.DeliveryFeeCalculator;
import order.util.OrderConverter;

import java.util.Map;

public class OrderService {

    private final OrderConverter orderConverter;
    private final DeliveryFeeCalculator deliveryFeeCalculator;

    public OrderService(OrderConverter orderConverter,
                        DeliveryFeeCalculator deliveryFeeCalculator) {
        this.orderConverter = orderConverter;
        this.deliveryFeeCalculator = deliveryFeeCalculator;
    }

    public Order convertToOrder(Map<String, String> parsedOrder) {
        return orderConverter.convert(parsedOrder);
    }

    public long calcDeliveryFee(long orderFee) {
        return deliveryFeeCalculator.calculateFee(orderFee);
    }
}
