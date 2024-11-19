package order;

import order.model.Order;
import order.model.fee.DeliveryFeeCalculator;
import order.model.fee.FeeCalculator;
import order.util.OrderConverter;

import java.util.Map;

public class OrderService {

    private OrderConverter orderConverter;
    private FeeCalculator feeCalculator;
    private DeliveryFeeCalculator deliveryFeeCalculator;

    public Order convertToOrder(Map<String, String> parsedOrder) {
        return orderConverter.convert(parsedOrder);
    }

    public long calcOrderFee(Order order) {
        return feeCalculator.calcOrderFee(order);
    }

    public long calcDeliveryFee(long orderFee) {
        return deliveryFeeCalculator.calculateFee(orderFee);
    }
}
