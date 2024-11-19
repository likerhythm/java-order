package order.view;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;

import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    public void printReceipt(ReceiptDto receiptDto) {
        OrderMenuDto orderMenuDto = receiptDto.getOrderMenuDto();
        long deliveryFee = receiptDto.getDeliveryFee();
        long totalFee = receiptDto.getTotalFee();

        StringBuilder sb = new StringBuilder();
        sb.append("[주문 내역]\n");
        Map<String, OrderDetailDto> orders = orderMenuDto.getOrders();
        Map<String, OrderDetailDto> services = orderMenuDto.getServices();

        for (String menuName : orders.keySet()) {
            OrderDetailDto detail = orders.get(menuName);
            long quantity = detail.getQuantity();
            long fee = detail.getFee();
            // 햄버거(5개): 50,000원
            sb.append(menuName).append("(").append(quantity).append("개): ").append(NumberFormat.getInstance().format(fee)).append("원\n");
        }

        sb.append("배달비: ").append(NumberFormat.getInstance().format(deliveryFee)).append("원\n");
        sb.append("\n");

        sb.append("[서비스]\n");
        for (String menuName : services.keySet()) {
            OrderDetailDto detail = services.get(menuName);
            long quantity = detail.getQuantity();
            //서비스 만두(5개)
            sb.append(menuName).append("(").append(quantity).append("개)\n");
        }

        sb.append("\n");
        sb.append("[최종 결제 금액]\n");
        sb.append(NumberFormat.getInstance().format(totalFee)).append("원");
        System.out.println(sb);
    }
}
