package be.ordina.beershop.order;

import be.ordina.beershop.common.Address;
import be.ordina.beershop.customer.JPACustomer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "order", collectionRelation = "orders")
public class OrderResource extends ResourceSupport {

    private UUID orderId;

    private JPACustomer customer;

    private List<LineItem> lineItems;

    private OrderStatus state;

    private LocalDateTime createdOn;

    private Address address;

    private String shipmentId;

    private OrderResource(Builder builder) {
        orderId = builder.orderId;
        customer = builder.customer;
        lineItems = builder.lineItems;
        state = builder.state;
        createdOn = builder.createdOn;
        address = builder.address;
        shipmentId = builder.shipmentId;
    }

    @JsonProperty("id")
    public UUID getOrderId() {
        return orderId;
    }

    public JPACustomer getCustomer() {
        return customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public OrderStatus getState() {
        return state;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
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
        private UUID orderId;
        private JPACustomer customer;
        private List<LineItem> lineItems;
        private OrderStatus state;
        private LocalDateTime createdOn;
        private Address address;
        private String shipmentId;

        private Builder() {
        }

        public Builder orderId(UUID orderId) {
            this.orderId = orderId;
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

        public OrderResource build() {
            return new OrderResource(this);
        }
    }
}
