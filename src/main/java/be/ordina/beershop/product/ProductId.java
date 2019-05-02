package be.ordina.beershop.product;

import be.ordina.beershop.common.domain.SingleValueObject;

import java.util.UUID;

public class ProductId extends SingleValueObject<UUID> {

    private ProductId(UUID value) {
        super(value);
    }

    public static ProductId productId(UUID value) {
        return new ProductId(value);
    }
}
