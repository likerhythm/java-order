package order;

import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;
import order.model.Order;
import order.util.InputParser;
import order.view.InputView;
import order.view.OutputView;

import java.util.IllegalFormatPrecisionException;
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
        String inputOrder = inputView.getOrder();
        Map<String, String> parsedOrder = inputParser.parseOrder(inputOrder);
        Order order = orderService.convertToOrder(parsedOrder);
        long orderFee = orderService.calcOrderFee(order);
        long deliveryFee = orderService.calcDeliveryFee(orderFee);
        OrderMenuDto orderMenuDto = order.getOrderMenuDto();
        ReceiptDto receiptDto = new ReceiptDto(orderMenuDto, deliveryFee, orderFee + deliveryFee);
        outputView.printReceipt(receiptDto);
    }
}
