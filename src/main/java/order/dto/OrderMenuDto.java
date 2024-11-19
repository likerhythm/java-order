package order.dto;

import java.util.Map;

public class OrderMenuDto {

    private final Map<String, OrderDetailDto> orders;
    private final Map<String, OrderDetailDto> services;

    public OrderMenuDto(Map<String, OrderDetailDto> orders, Map<String, OrderDetailDto> services) {
        this.orders = orders;
        this.services = services;
    }

    public Map<String, OrderDetailDto> getOrders() {
        return orders;
    }

    public Map<String, OrderDetailDto> getServices() {
        return services;
    }
}
