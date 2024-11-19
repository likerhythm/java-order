package order.dto;

import order.model.OrderMenu;

import java.util.Map;

public class ReceiptOrderDto {

    private final Map<OrderMenu, Long> service;

    public ReceiptOrderDto(Map<OrderMenu, Long> service) {
        this.service = service;
    }

    public Map<OrderMenu, Long> getService() {
        return service;
    }
}
