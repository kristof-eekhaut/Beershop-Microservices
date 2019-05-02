package be.ordina.beershop.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateProduct {

    private final String name;
    private final BigDecimal price;
    private final BigDecimal alcoholPercentage;
    private final Weight weight;

    @JsonCreator
    CreateProduct(@JsonProperty("customerId") @NotNull final String name,
                  @JsonProperty("price") @NotNull @DecimalMin(value = "0.01") BigDecimal price,
                  @JsonProperty("alcoholPercentage") @NotNull BigDecimal alcoholPercentage,
                  @JsonProperty("weight") @NotNull Weight weight) {
        this.name = name;
        this.price = price;
        this.alcoholPercentage = alcoholPercentage;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
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
        private String name;
        private BigDecimal price;
        private BigDecimal alcoholPercentage;
        private Weight weight;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
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

        public CreateProduct build() {
            return new CreateProduct(
                    name,
                    price,
                    alcoholPercentage,
                    weight
            );
        }
    }
}
