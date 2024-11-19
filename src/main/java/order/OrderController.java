package order;

import order.dto.ReceiptOrderDto;
import order.model.Order;
import order.model.fee.DeliveryFeeCalculator;
import order.model.fee.FeeCalculator;
import order.util.InputParser;
import order.util.OrderConverter;
import order.view.InputView;

import java.util.Map;

public class OrderController {

    private final InputView inputView;
    private final InputParser inputParser;
    private final OrderService orderService;

    public OrderController(InputView inputView, InputParser inputParser, OrderService orderService) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.orderService = orderService;
    }

    public void start() {
        String inputOrder = inputView.getOrder();
        Map<String, String> parsedOrder = inputParser.parseOrder(inputOrder);
        Order order = orderService.convertToOrder(parsedOrder);
        long orderFee = orderService.calcOrderFee(order);
        long deliveryFee = orderService.calcDeliveryFee(orderFee);
        ReceiptOrderDto receiptOrderDto = order.getReceiptDto();
    }
}
