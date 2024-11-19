package order.model;

import order.error.ErrorMessage;

import java.util.Arrays;

public enum Menu {

    PIZZA("피자", 25000, "main"),
    HAMBURGER("햄버거", 10000, "main"),
    CHICKEN("치킨", 23000, "main"),

    FRENCH_FRIES("감자튀김", 5000, "side"),
    CHEESE_STICK("치즈스틱", 7000, "side"),
    SALAD("샐러드", 8000, "side"),

    COLA("콜라", 2000, "drink"),
    ZERO_COLA("제로 콜라", 2500, "drink"),
    ORANGE_JUICE("오렌지 주스", 3000, "drink"),

    SERVICE_DUMPLING("서비스 만두", 0, "service");

    private final String name;
    private final long price;
    private final String type;

    Menu(String name, long price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Long calcFee(Long quantity) {
        return price * quantity;
    }

    public static Menu findByName(String targetName) {
        return Arrays.stream(Menu.values())
                .filter(orderMenu -> orderMenu.name.equals(targetName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.WRONG_ORDER_FORMAT));
    }

    public boolean isDrink() {
        return type.equals("drink");
    }

    public boolean isMain() {
        return type.equals("main");
    }

    public boolean isService() {
        return type.equals("service");
    }

    public String getName() {
        return name;
    }
}
