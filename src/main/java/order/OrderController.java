package order;

import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;
import order.error.Error;
import order.model.Order;
import order.util.InputParser;
import order.view.InputView;
import order.view.OutputView;

import java.util.Map;

public class OrderController {

    private final InputView inputView;
    private final InputParser inputParser;
    private final OutputView outputView;
    private final OrderService orderService;

    public OrderController(InputView inputView,
                           InputParser inputParser,
                           OutputView outputView,
                           OrderService orderService) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void start() {
        try {
            Order order = getOrderFromUser();
            ReceiptDto receiptDto = makeReceiptDto(order);
            outputView.printReceipt(receiptDto);
        } catch (IllegalArgumentException e) {
            System.out.println(Error.PREFIX + e.getMessage());
        }
    }

    private ReceiptDto makeReceiptDto(Order order) {
        long orderFee = order.getFee();
        long deliveryFee = orderService.calcDeliveryFee(orderFee);
        OrderMenuDto orderMenuDto = order.getOrderMenuDto();
        return new ReceiptDto(orderMenuDto, deliveryFee, orderFee, orderFee + deliveryFee);
    }

    private Order getOrderFromUser() {
        String inputOrder = inputView.getOrder();
        Map<String, String> parsedOrder = inputParser.parseOrder(inputOrder);
        return orderService.convertToOrder(parsedOrder);
    }
}
