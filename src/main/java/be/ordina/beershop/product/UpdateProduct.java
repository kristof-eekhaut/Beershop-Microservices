package be.ordina.beershop.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UpdateProduct {

    private final int quantity;

    @JsonCreator
    UpdateProduct(@JsonProperty("customerId") @NotNull final int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int quality;

        private Builder() {
        }

        public Builder name(int quality) {
            this.quality = quality;
            return this;
        }

        public UpdateProduct build() {
            return new UpdateProduct(
                    quality
            );
        }
    }
}
