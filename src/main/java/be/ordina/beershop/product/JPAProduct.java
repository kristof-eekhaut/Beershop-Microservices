package be.ordina.beershop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "PRODUCT")
public class JPAProduct {

    @Id
    @Column(name = "ID")
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discount> discounts = new ArrayList<>();
    @Column(name = "ALCOHOL_PERCENTAGE")
    private BigDecimal alcoholPercentage;
    @Embedded
    private Weight weight;

    JPAProduct() {
    }

    public JPAProduct(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public int getQuantity() {
        return quantity;
    }

    @JsonProperty
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(final Weight weight) {
        this.weight = weight;
    }

    public void setDiscounts(final List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Discount> getDiscounts() {
        return new ArrayList<>(discounts);
    }

    public BigDecimal getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(final BigDecimal alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }
}
