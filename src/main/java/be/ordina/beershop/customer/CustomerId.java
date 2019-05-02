package be.ordina.beershop.customer;

import be.ordina.beershop.common.domain.SingleValueObject;

import java.util.UUID;

public class CustomerId extends SingleValueObject<UUID> {

    private CustomerId(UUID value) {
        super(value);
    }

    public static CustomerId customerId(UUID value) {
        return new CustomerId(value);
    }
}
