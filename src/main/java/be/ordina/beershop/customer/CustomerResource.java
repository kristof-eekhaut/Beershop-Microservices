package be.ordina.beershop.customer;

import be.ordina.beershop.common.Address;
import be.ordina.beershop.domain.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "customer", collectionRelation = "customers")
public class CustomerResource extends ResourceSupport {

    private UUID customerId;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDate;

    private ShoppingCart shoppingCart;

    @Valid
    private Address address;

    private CustomerResource(Builder builder) {
        customerId = builder.customerId;
        name = builder.name;
        birthDate = builder.birthDate;
        shoppingCart = builder.shoppingCart;
        address = builder.address;
    }

    @JsonProperty("id")
    public UUID getCustomerId() {
        return customerId;
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

        private UUID customerId;
        private String name;
        private LocalDate birthDate;
        private ShoppingCart shoppingCart;
        private Address address;

        private Builder() {
        }

        public Builder customerId(UUID customerId) {
            this.customerId = customerId;
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

        public CustomerResource build() {
            return new CustomerResource(this);
        }
    }
}
