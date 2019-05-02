package be.ordina.beershop.customer;

import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class CustomerId {

    private UUID value;

    private CustomerId(UUID value) {
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

    public static CustomerId customerId(UUID value) {
        return new CustomerId(value);
    }
}
