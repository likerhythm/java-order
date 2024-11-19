package order.view;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;

import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    public void printReceipt(ReceiptDto receiptDto) {
        OrderMenuDto orderMenuDto = receiptDto.getOrderMenuDto();
        StringBuilder sb = new StringBuilder();
        buildReceipt(receiptDto, sb, orderMenuDto);
        System.out.println(sb);
    }

    private void buildReceipt(ReceiptDto receiptDto, StringBuilder sb, OrderMenuDto orderMenuDto) {
        buildDeliveryFee(sb, receiptDto.getDeliveryFee());
        buildOrders(sb, orderMenuDto);
        Map<String, OrderDetailDto> services = orderMenuDto.getServices();
        if (!services.isEmpty()) {
            buildServices(sb, services);
        }
        buildTotalFee(sb, receiptDto.getTotalFee());
    }

    private void buildTotalFee(StringBuilder sb, long totalFee) {
        sb.append("[최종 결제 금액]\n");
        sb.append(NumberFormat.getInstance().format(totalFee)).append("원");
    }

    private void buildServices(StringBuilder sb, Map<String, OrderDetailDto> services) {
        sb.append("[서비스]\n");
        for (String menuName : services.keySet()) {
            OrderDetailDto detail = services.get(menuName);
            long quantity = detail.getQuantity();
            sb.append(menuName).append("(").append(quantity).append("개)\n");
        }
        sb.append("\n");
    }

    private void buildDeliveryFee(StringBuilder sb, long deliveryFee) {
        sb.append("배달비: ").append(NumberFormat.getInstance().format(deliveryFee)).append("원\n");
        sb.append("\n");
    }

    private void buildOrders(StringBuilder sb, OrderMenuDto orderMenuDto) {
        sb.append("[주문 내역]\n");
        Map<String, OrderDetailDto> orders = orderMenuDto.getOrders();

        for (String menuName : orders.keySet()) {
            OrderDetailDto detail = orders.get(menuName);
            long quantity = detail.getQuantity();
            long fee = detail.getFee();
            sb.append(menuName).append("(").append(quantity).append("개): ").append(NumberFormat.getInstance().format(fee)).append("원\n");
        }
    }
}
