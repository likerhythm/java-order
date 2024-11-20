package order.view;

import order.error.ErrorMessage;

import java.util.Scanner;

public class InputView {

    public static final String ORDER_INPUT_DELIMITER = ", ";
    public static final String MENU_NAME_AND_QUANTITY_DELIMITER = "\\(";
    private static final String ORDER_INPUT_UNIT_FORMAT = "[가-힣a-zA-Z0-9\\s]+\\([\\d]+개\\)";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getOrder() {
        System.out.println("주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)");
        String input = scanner.nextLine();
        validateBlank(input);
        validateOrderInputFormat(input);
        return input;
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ORDER_FORMAT);
        }
    }

    private void validateOrderInputFormat(String input) {
        String[] splitInput = input.split(ORDER_INPUT_DELIMITER);
        for (String split : splitInput) {
            if (!split.matches(ORDER_INPUT_UNIT_FORMAT)) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_ORDER_FORMAT);
            }
        }
    }
}
