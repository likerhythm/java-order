package order.view;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OutputViewTest {

    OutputView outputView;

    @Test
    void printReceiptTest() {
        outputView = new OutputView();
        Map<String, OrderDetailDto> orders = Map.of("피자", new OrderDetailDto(2, 50000),
                "콜라", new OrderDetailDto(5, 10000));
        Map<String, OrderDetailDto> services = Map.of("서비스 만두", new OrderDetailDto(2, 0));
        ReceiptDto receiptDto = new ReceiptDto(new OrderMenuDto(orders, services), 1000, 61000);

        outputView.printReceipt(receiptDto);
    }
}
