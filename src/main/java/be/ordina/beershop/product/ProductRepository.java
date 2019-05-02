package be.ordina.beershop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {

    ProductId nextId();

    void add(Product product);

    void update(Product product);

    Optional<Product> findById(ProductId productId);

    Page<Product> findAll(Pageable pageable);
}
