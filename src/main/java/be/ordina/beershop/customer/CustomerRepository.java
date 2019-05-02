package be.ordina.beershop.customer;

import java.util.Optional;

public interface CustomerRepository {

    CustomerId nextId();

    Optional<Customer> findById(CustomerId customerId);
}
