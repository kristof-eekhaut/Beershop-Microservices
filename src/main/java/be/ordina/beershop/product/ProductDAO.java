package be.ordina.beershop.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDAO extends JpaRepository<JPAProduct, UUID> {
}
