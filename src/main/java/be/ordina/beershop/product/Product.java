package be.ordina.beershop.product;

import be.ordina.beershop.common.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Product extends AbstractEntity<ProductId> {

    private String name;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime createdOn;
    private List<Discount> discounts;
    private BigDecimal alcoholPercentage;
    private Weight weight;

    private Product(Builder builder) {
        super(builder.id);
        name = requireNonNull(builder.name);
        quantity = builder.quantity;
        price = requireNonNull(builder.price);
        createdOn = builder.createdOn;
        discounts = builder.discounts;
        alcoholPercentage = requireNonNull(builder.alcoholPercentage);
        weight = builder.weight;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Weight getWeight() {
        return weight;
    }

    public void addDiscount(final Discount discount) {
        discounts.add(discount);
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

    @JsonProperty
    public QuantityIndicator getQuantityIndicator() {
        if(quantity == 0) {
            return QuantityIndicator.SOLD_OUT;
        } else if(quantity < 5) {
            return QuantityIndicator.ALMOST_SOLD_OUT;
        } else {
            return QuantityIndicator.PLENTY_AVAILABLE;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ProductId id;
        private String name;
        private int quantity;
        private BigDecimal price;
        private LocalDateTime createdOn;
        private List<Discount> discounts;
        private BigDecimal alcoholPercentage;
        private Weight weight;

        private Builder() {
        }

        public Builder id(ProductId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public Builder discounts(List<Discount> discounts) {
            this.discounts = discounts;
            return this;
        }

        public Builder alcoholPercentage(BigDecimal alcoholPercentage) {
            this.alcoholPercentage = alcoholPercentage;
            return this;
        }

        public Builder weight(Weight weight) {
            this.weight = weight;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
