package be.ordina.beershop.order;

import be.ordina.beershop.common.Address;
import be.ordina.beershop.customer.JPACustomer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class JPAOrder {

    @Id
    @Column(name = "ID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private JPACustomer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus state;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Embedded
    private Address address;

    @Column(name = "SHIPMENT_ID")
    private String shipmentId;

    public JPAOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public JPACustomer getCustomer() {
        return customer;
    }

    public void setCustomer(JPACustomer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OrderStatus getState() {
        return state;
    }

    public void setState(OrderStatus state) {
        this.state = state;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }
}
