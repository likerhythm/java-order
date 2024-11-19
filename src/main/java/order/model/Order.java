package order.model;

import order.dto.ReceiptOrderDto;

import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private final Map<OrderMenu, Long> orders;

    public Order(final Map<OrderMenu, Long> orders) {
        validateAllDrink(orders);
        this.orders = orders;
        addServiceDumpling(orders);
    }

    private void addServiceDumpling(Map<OrderMenu, Long> orders) {
        long serviceCount = orders.keySet().stream()
                .filter(OrderMenu::isMain)
                .count();
        orders.merge(OrderMenu.SERVICE_DUMPLING, serviceCount, Long::sum);
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

    public ReceiptOrderDto getReceiptDto() {
        Map<OrderMenu, Long> services = orders.entrySet().stream()
                .filter(entry -> entry.getKey().isService())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        return new ReceiptOrderDto(services);
    }
}
