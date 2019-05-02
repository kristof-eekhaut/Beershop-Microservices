package be.ordina.beershop.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// TODO:  make package private
public interface CustomerDAO extends JpaRepository<JPACustomer, UUID> {
}
