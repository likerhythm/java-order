package order.model;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Order {

    private final Map<Menu, Long> orders;

    public Order(final Map<Menu, Long> orders) {
        validateAllDrink(orders);
        this.orders = orders;
        addServiceDumpling(orders);
    }

    public long calcFee() {
        long fee = orders.entrySet().stream()
                .mapToLong(entry -> {
                    Menu key = entry.getKey();
                    Long value = entry.getValue();
                    return key.calcFee(value);
                })
                .sum();
        if (fee < 30000) {
            throw new IllegalArgumentException("최소 주문 금액을 만족하지 못했습니다.");
        }

        return fee;
    }

    public OrderMenuDto getOrderMenuDto() {
        Map<String, OrderDetailDto> nonServiceMenus = filterOrdersBy(entry -> !entry.getKey().isService());
        Map<String, OrderDetailDto> serviceMenus = filterOrdersBy(entry -> entry.getKey().isService());
        return new OrderMenuDto(nonServiceMenus, serviceMenus);
    }

    private void addServiceDumpling(Map<Menu, Long> orders) {
        long serviceCount = orders.keySet().stream()
                .filter(Menu::isMain)
                .count();
        if (serviceCount > 0) {
            orders.merge(Menu.SERVICE_DUMPLING, serviceCount, Long::sum);
        }
    }

    private void validateAllDrink(Map<Menu, Long> orders) {
        boolean isAllDrink = orders.keySet().stream().allMatch(Menu::isDrink);
        if (isAllDrink && !orders.isEmpty()) {
            throw new IllegalArgumentException("음료만으로는 주문할 수 없습니다.");
        }
    }

    private Map<String, OrderDetailDto> filterOrdersBy(Predicate<Map.Entry<Menu, Long>> predicate) {
        return orders.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> {
                            long quantity = entry.getValue();
                            long fee = entry.getKey().calcFee(quantity);
                            return new OrderDetailDto(entry.getValue(), fee);
                        }
                ));
    }
}
