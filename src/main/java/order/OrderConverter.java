package order;

import java.util.Map;
import java.util.stream.Collectors;

public class OrderConverter {

    public Order convert(Map<String, Long> menuNameAndQuantity) {
        Map<OrderMenu, Long> orders = menuNameAndQuantity.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> OrderMenu.findByName(entry.getKey()),
                        Map.Entry::getValue
                ));
        return new Order(orders);
    }
}
