package order;

import java.util.Map;
import java.util.stream.Collectors;

public class OrderConverter {

    public Order convert(Map<String, String> menuNameAndQuantity) {
        Map<OrderMenu, Long> orders = menuNameAndQuantity.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> OrderMenu.findByName(entry.getKey()),
                        entry -> Long.parseLong(entry.getValue())
                ));
        return new Order(orders);
    }
}
