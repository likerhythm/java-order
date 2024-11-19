package order.view;

import java.util.Scanner;

public class InputView {

    private static final String ORDER_INPUT_UNIT_FORMAT = "[가-힣a-zA-Z0-9\\s]+\\([\\d]+개\\)";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getOrder() {
        System.out.println("주문하실 메뉴와 수량을 입력해주세요. ex) 피자(2개), 감자튀김(1개), 콜라(3개)");
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
