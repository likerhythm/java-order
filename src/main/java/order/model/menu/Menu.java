package order.model.menu;

import order.error.ErrorMessage;

import java.util.Arrays;

public enum Menu {

    PIZZA           ("피자", 25000, MenuType.MAIN),
    HAMBURGER       ("햄버거", 10000, MenuType.MAIN),
    CHICKEN         ("치킨", 23000, MenuType.MAIN),

    FRENCH_FRIES    ("감자튀김", 5000, MenuType.SIDE),
    CHEESE_STICK    ("치즈스틱", 7000, MenuType.SIDE),
    SALAD           ("샐러드", 8000, MenuType.SIDE),

    COLA            ("콜라", 2000, MenuType.DRINK),
    ZERO_COLA       ("제로 콜라", 2500, MenuType.DRINK),
    ORANGE_JUICE    ("오렌지 주스", 3000, MenuType.DRINK),

    SERVICE_DUMPLING("서비스 만두", 0, MenuType.SERVICE);

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
        return type.equals(MenuType.DRINK);
    }

    public boolean isMain() {
        return type.equals(MenuType.MAIN);
    }

    public boolean isService() {
        return type.equals(MenuType.SERVICE);
    }

    public String getName() {
        return name;
    }
}
