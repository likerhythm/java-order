package order.view;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;

import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    private static final String ORDER_HISTORY_FORMAT = "%s(%d개): %s원\n";
    private static final String ORDER_FEE_FORMAT = "총 주문 금액: %s원\n";
    private static final String DELIVERY_FEE_FORMAT = "배달비: %s원\n";
    private static final String SERVICE_HISTORY_FORMAT = "%s(%s개)\n";
    private static final String TOTAL_FEE_FORMAT = "%s원";

    public void printReceipt(ReceiptDto receiptDto) {
        OrderMenuDto orderMenuDto = receiptDto.getOrderMenuDto();
        StringBuilder sb = new StringBuilder();
        buildReceipt(receiptDto, sb, orderMenuDto);
        System.out.println(sb);
    }

    private void buildReceipt(ReceiptDto receiptDto, StringBuilder sb, OrderMenuDto orderMenuDto) {
        buildOrders(sb, orderMenuDto);
        sb.append(String.format(ORDER_FEE_FORMAT, NumberFormat.getInstance().format(receiptDto.getOrderFee())));
        buildDeliveryFee(sb, receiptDto.getDeliveryFee());
        Map<String, OrderDetailDto> services = orderMenuDto.getServices();
        if (!services.isEmpty()) {
            buildServices(sb, services);
        }
        buildTotalFee(sb, receiptDto.getTotalFee());
    }

    private void buildTotalFee(StringBuilder sb, long totalFee) {
        sb.append("[최종 결제 금액]\n");
        sb.append(String.format(TOTAL_FEE_FORMAT, NumberFormat.getInstance().format(totalFee)));
    }

    private void buildServices(StringBuilder sb, Map<String, OrderDetailDto> services) {
        sb.append("[서비스]\n");
        for (String menuName : services.keySet()) {
            OrderDetailDto detail = services.get(menuName);
            long quantity = detail.getQuantity();
            sb.append(String.format(SERVICE_HISTORY_FORMAT, menuName, quantity));
        }
        sb.append("\n");
    }

    private void buildDeliveryFee(StringBuilder sb, long deliveryFee) {
        sb.append(String.format(DELIVERY_FEE_FORMAT, NumberFormat.getInstance().format(deliveryFee)));
        sb.append("\n");
    }

    private void buildOrders(StringBuilder sb, OrderMenuDto orderMenuDto) {
        sb.append("[주문 내역]\n");
        Map<String, OrderDetailDto> orders = orderMenuDto.getOrders();
        for (String menuName : orders.keySet()) {
            OrderDetailDto detail = orders.get(menuName);
            long quantity = detail.getQuantity();
            long fee = detail.getFee();
            sb.append(String.format(ORDER_HISTORY_FORMAT, menuName, quantity, NumberFormat.getInstance().format(fee)));
        }
    }
}
