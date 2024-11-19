package order.view;

import java.util.Scanner;

public class InputView {

    private static final String ORDER_INPUT_UNIT_FORMAT = "[가-힣a-zA-Z0-9]+\\(\\d개\\)";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getOrder() {
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("주문 형식이 잘못되었습니다.");
        }
        String[] splitInput = input.split(", ");
        for (String split : splitInput) {
            System.out.println(split);
            if (!split.matches(ORDER_INPUT_UNIT_FORMAT)) {
                throw new IllegalArgumentException("주문 형식이 잘못되었습니다.");
            }
        }
//        if (!input.matches(ORDER_INPUT_UNIT_FORMAT)) {
//            throw new IllegalArgumentException("주문 형식이 잘못되었습니다.");
//        }
        return input;
    }
}
