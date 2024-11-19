package order.model;

import order.dto.OrderDetailDto;
import order.dto.OrderMenuDto;
import order.error.ErrorMessage;

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
            throw new IllegalArgumentException(ErrorMessage.LESS_THAN_LOWER_ORDER_FEE_LIMIT);
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
                .mapToLong(orders::get)
                .sum();
        if (serviceCount > 0) {
            orders.merge(Menu.SERVICE_DUMPLING, serviceCount, Long::sum);
        }
    }

    private void validateAllDrink(Map<Menu, Long> orders) {
        boolean isAllDrink = orders.keySet().stream().allMatch(Menu::isDrink);
        if (isAllDrink && !orders.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_IN_ORDER);
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
