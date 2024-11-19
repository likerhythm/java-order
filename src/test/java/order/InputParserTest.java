package order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class InputParserTest {

    @Test
    void parseToOrderMapTest() {
        String input = "콜라(2개), 햄버거(3개)";
        InputParser inputParser = new InputParser();
        Assertions.assertEquals(Map.of("콜라", "2", "햄버거", "3"), inputParser.parseOrder(input));
    }
}
