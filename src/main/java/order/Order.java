package order;

import java.util.Map;

public class Order {

    private final Map<OrderMenu, Long> orders;

    public Order(final Map<OrderMenu, Long> orders) {
        validateAllDrink(orders);
        this.orders = orders;
    }

    private void validateAllDrink(Map<OrderMenu, Long> orders) {
        boolean isAllDrink = orders.keySet().stream().allMatch(OrderMenu::isDrink);
        if (isAllDrink) {
            throw new IllegalArgumentException("음료만으로는 주문할 수 없습니다.");
        }
    }

    public long calcOrderFee() {
        return orders.entrySet().stream()
                .mapToLong(entry -> {
                    OrderMenu key = entry.getKey();
                    Long value = entry.getValue();
                    return key.calcFee(value);
                })
                .sum();
    }
}
