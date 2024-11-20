package order.view;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.dto.ReceiptDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class OutputViewTest {

    OutputView outputView;
    PrintStream originalOut;
    ByteArrayOutputStream outContent;

    @BeforeEach
    void beforeEach() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void printReceiptTest() {
        outputView = new OutputView();
        ReceiptDto receiptDto = makeReceiptDto();

        outputView.printReceipt(receiptDto);

        String capturedOutput = outContent.toString();
        Assertions.assertTrue(capturedOutput.contains(
                "[주문 내역]\n" +
                "피자(2개): 50,000원\n" +
                "콜라(5개): 10,000원\n" +
                "총 주문 금액: 60,000원\n" +
                "배달비: 1,000원\n" +
                "\n" +
                "[서비스]\n" +
                "서비스 만두(2개)\n" +
                "\n" +
                "[최종 결제 금액]\n" +
                "61,000원"));
    }

    private static ReceiptDto makeReceiptDto() {
        Map<String, OrderDetailDto> orders = new LinkedHashMap<>();
        orders.put("피자", new OrderDetailDto(2, 50000));
        orders.put("콜라", new OrderDetailDto(5, 10000));
        Map<String, OrderDetailDto> services = new LinkedHashMap<>();
        services.put("서비스 만두", new OrderDetailDto(2, 0));
        return new ReceiptDto(new OrderMenuDto(orders, services), 1000, 60000, 61000);
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOut);
    }
}
