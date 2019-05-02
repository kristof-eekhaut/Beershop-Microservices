package be.ordina.beershop.order;

import be.ordina.beershop.common.domain.SingleValueObject;

import java.util.UUID;

public class OrderId extends SingleValueObject<UUID> {

    private OrderId(UUID value) {
        super(value);
    }

    public static OrderId orderId(UUID value) {
        return new OrderId(value);
    }
}
