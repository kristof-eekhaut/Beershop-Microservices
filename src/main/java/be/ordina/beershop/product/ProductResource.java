package be.ordina.beershop.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "product", collectionRelation = "products")
public class ProductResource extends ResourceSupport {

    private UUID productId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime createdOn;
    private List<Discount> discounts;
    private BigDecimal alcoholPercentage;
    private Weight weight;

    private ProductResource(Builder builder) {
        productId = builder.productId;
        name = builder.name;
        quantity = builder.quantity;
        price = builder.price;
        createdOn = builder.createdOn;
        discounts = builder.discounts;
        alcoholPercentage = builder.alcoholPercentage;
        weight = builder.weight;
    }

    @JsonProperty("id")
    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public BigDecimal getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public Weight getWeight() {
        return weight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID productId;
        private String name;
        private int quantity;
        private BigDecimal price;
        private LocalDateTime createdOn;
        private List<Discount> discounts = new ArrayList<>();
        private BigDecimal alcoholPercentage;
        private Weight weight;

        private Builder() {
        }

        public Builder id(UUID productId) {
            this.productId = productId;
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

        public ProductResource build() {
            return new ProductResource(this);
        }
    }
}
