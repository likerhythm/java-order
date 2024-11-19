package order.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class InputViewTest {

    InputView inputView;

    @ParameterizedTest
    @ValueSource(strings = {
            "콜라(2개)",
            "콜라(2개), 햄버거(1개)"
    })
    void validOrderInputTest(String input) {
        inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));
        Assertions.assertDoesNotThrow(() -> inputView.getOrder());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " ",
            "콜라(2)",
            "콜라(2개)/ 햄버거(1개)",
            "콜라(2개",
            "(2개)",
            "(2개), 햄버거(1개)",
            "콜라 (1개)",
            "콜라(1 개)",
            "콜라(한개)",
            ", 햄버거(1개)",
            "콜라(1개),\n햄버거(1개)"
    })
    void invalidOrderInputTest(String input) {
        inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputView.getOrder());
    }
}
