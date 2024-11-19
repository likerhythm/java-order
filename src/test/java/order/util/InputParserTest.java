package order.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class InputParserTest {

    InputParser inputParser = new InputParser();

    @Test
    void parseToOrderMapTest() {
        String input = "콜라(2개), 햄버거(3개)";
        Assertions.assertEquals(Map.of("콜라", "2", "햄버거", "3"), inputParser.parseOrder(input));
    }

    @Test
    void invalidParseTest() {
        String input = "콜라 (1개)";
        Assertions.assertThrows(IllegalArgumentException.class, () -> inputParser.parseOrder(input));
    }
}
