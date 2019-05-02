package be.ordina.beershop.customer;

import be.ordina.beershop.domain.ShoppingCart;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Customer {

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private ShoppingCart shoppingCart;
    private Address address;

    private Customer(Builder builder) {
        id = requireNonNull(builder.id);
        name = requireNonNull(builder.name);
        birthDate = requireNonNull(builder.birthDate);
        shoppingCart = requireNonNull(builder.shoppingCart);
        address = requireNonNull(builder.address);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private LocalDate birthDate;
        private ShoppingCart shoppingCart = new ShoppingCart();
        private Address address;

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder shoppingCart(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
