package be.ordina.beershop.order;

import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class OrderId {

    private UUID value;

    private OrderId(UUID value) {
        this.value = requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    public static OrderId orderId(UUID value) {
        return new OrderId(value);
    }
}
