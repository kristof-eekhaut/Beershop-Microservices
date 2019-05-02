package be.ordina.beershop.order;

import be.ordina.beershop.common.Address;
import be.ordina.beershop.common.domain.AbstractEntity;
import be.ordina.beershop.customer.JPACustomer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Order extends AbstractEntity<OrderId> {

    private JPACustomer customer;

    private List<LineItem> lineItems;

    private OrderStatus state;

    private LocalDateTime createdOn;

    private Address address;

    private String shipmentId;

    private Order(Builder builder) {
        super(builder.id);
        customer = requireNonNull(builder.customer);
        lineItems = requireNonNull(builder.lineItems);
        state = requireNonNull(builder.state);
        createdOn = builder.createdOn;
        address = builder.address;
        shipmentId = builder.shipmentId;
    }

    public JPACustomer getCustomer() {
        return customer;
    }

    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderStatus getState() {
        return state;
    }

    public Address getAddress() {
        return address;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId id;
        private JPACustomer customer;
        private List<LineItem> lineItems;
        private OrderStatus state;
        private LocalDateTime createdOn;
        private Address address;
        private String shipmentId;

        private Builder() {
        }

        public Builder id(OrderId id) {
            this.id = id;
            return this;
        }

        public Builder customer(JPACustomer customer) {
            this.customer = customer;
            return this;
        }

        public Builder lineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Builder state(OrderStatus state) {
            this.state = state;
            return this;
        }

        public Builder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder shipmentId(String shipmentId) {
            this.shipmentId = shipmentId;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
