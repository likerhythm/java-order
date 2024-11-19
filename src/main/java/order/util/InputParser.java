package order.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputParser {

    public Map<String, String> parseOrder(String input) {
        Map<String, String> result = new LinkedHashMap<>();
        String[] splitInput = input.split(", ");
        for (String splitByDelimiter : splitInput) {
            makeUpParseOrder(splitByDelimiter, result);
        }
        return result;
    }

    private void makeUpParseOrder(String splitByDelimiter, Map<String, String> result) {
        String[] nameAndQuantity = splitByDelimiter.split("\\(");
        String menuName = nameAndQuantity[0];
        if (menuName.startsWith(" ") || menuName.endsWith(" ")) {
            throw new IllegalArgumentException("주문 형식이 잘못되었습니다.");
        }
        String rawQuantity = nameAndQuantity[1];
        String quantity = rawQuantity.substring(0, rawQuantity.length() - 2);
        result.put(menuName, quantity);
    }
}
