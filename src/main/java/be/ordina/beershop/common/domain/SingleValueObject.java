package be.ordina.beershop.common.domain;

import static java.util.Objects.requireNonNull;

public abstract class SingleValueObject<T> extends AbstractValueObject {

    private T value;

    protected SingleValueObject(T value) {
        this.value = requireNonNull(value);
    }

    public T getValue() {
        return value;
    }
}
