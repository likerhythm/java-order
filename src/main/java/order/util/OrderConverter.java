package order.util;

import order.model.Order;
import order.model.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderConverter {

    public Order convert(Map<String, String> menuNameAndQuantity) {
        Map<Menu, Long> orders = new LinkedHashMap<>(
                menuNameAndQuantity.entrySet().stream()
                        .filter(entry -> Long.parseLong(entry.getValue()) > 0)
                        .collect(Collectors.toMap(
                                entry -> Menu.findByName(entry.getKey()),
                                entry -> Long.parseLong(entry.getValue())
                        )));
        return new Order(orders);
    }
}
