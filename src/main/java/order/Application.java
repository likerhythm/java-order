package order;

import order.model.fee.DeliveryFeeCalculator;
import order.model.fee.FeeRange;
import order.util.InputParser;
import order.util.OrderConverter;
import order.view.InputView;
import order.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        OrderController orderController = createOrderController();

        orderController.start();
    }

    private static OrderController createOrderController() {
        InputView inputView = new InputView(new Scanner(System.in));
        InputParser inputParser = new InputParser();
        OutputView outputView = new OutputView();

        OrderService orderService = createOrderService();
        return new OrderController(inputView, inputParser, outputView, orderService);
    }

    private static OrderService createOrderService() {
        OrderConverter orderConverter = new OrderConverter();
        Map<FeeRange, Long> deliveryFeeRule = makeDeliveryFeeRule();
        DeliveryFeeCalculator deliveryFeeCalculator = new DeliveryFeeCalculator(deliveryFeeRule);
        return new OrderService(orderConverter, deliveryFeeCalculator);
    }

    private static Map<FeeRange, Long> makeDeliveryFeeRule() {
        FeeRange standardFeeRange = new FeeRange(0, 49999);
        FeeRange discountFeeRange = new FeeRange(50000, 99999);
        FeeRange freeFeeRange = new FeeRange(100000, Long.MAX_VALUE);
        return new HashMap<>(Map.of(standardFeeRange, 2000L, discountFeeRange, 1000L, freeFeeRange, 0L));
    }
}
