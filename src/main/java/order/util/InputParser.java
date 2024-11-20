package order.util;

import order.error.ErrorMessage;

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
        if (isStartOrEndWithBlank(menuName)) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ORDER_FORMAT);
        }
        String rawQuantity = nameAndQuantity[1];
        String quantity = rawQuantity.substring(0, rawQuantity.length() - 2);
        if (result.containsKey(menuName)) {
            result.merge(menuName, quantity, (oldValue, newValue) ->
                    String.valueOf(Integer.parseInt(oldValue) + Integer.parseInt(newValue)));
            return;
        }
        result.put(menuName, quantity);
    }

    private boolean isStartOrEndWithBlank(String menuName) {
        return menuName.startsWith(" ") || menuName.endsWith(" ");
    }
}
