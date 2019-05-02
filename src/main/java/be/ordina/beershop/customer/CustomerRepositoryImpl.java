package be.ordina.beershop.customer;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static be.ordina.beershop.customer.CustomerId.customerId;
import static java.util.Objects.requireNonNull;

@Repository
class CustomerRepositoryImpl implements CustomerRepository {

    private CustomerDAO customerDAO;

    CustomerRepositoryImpl(CustomerDAO customerDAO) {
        this.customerDAO = requireNonNull(customerDAO);
    }

    @Override
    public CustomerId nextId() {
        return customerId(UUID.randomUUID());
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerDAO.findById(customerId.getValue())
                .map(this::mapToDomain);
    }

    private Customer mapToDomain(JPACustomer jpaCustomer) {
        return Customer.builder()
                .id(jpaCustomer.getId())
                .name(jpaCustomer.getName())
                .birthDate(jpaCustomer.getBirthDate())
                .shoppingCart(jpaCustomer.getShoppingCart())
                .address(jpaCustomer.getAddress())
                .build();
    }
}
