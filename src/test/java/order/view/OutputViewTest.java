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
        Assertions.assertTrue(capturedOutput.contains("피자(2개): 50,000원"), "[서비스 내역]\n서비스 만두(2개)");
    }

    private static ReceiptDto makeReceiptDto() {
        Map<String, OrderDetailDto> orders = Map.of(
                "피자", new OrderDetailDto(2, 50000),
                "콜라", new OrderDetailDto(5, 10000));
        Map<String, OrderDetailDto> services = Map.of("서비스 만두", new OrderDetailDto(2, 0));
        return new ReceiptDto(new OrderMenuDto(orders, services), 1000, 61000);
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOut);
    }
}
