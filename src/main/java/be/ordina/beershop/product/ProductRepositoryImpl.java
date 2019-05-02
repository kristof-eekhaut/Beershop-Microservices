package be.ordina.beershop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static be.ordina.beershop.product.ProductId.productId;
import static java.util.Objects.requireNonNull;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductDAO productDAO;

    ProductRepositoryImpl(ProductDAO productDAO) {
        this.productDAO = requireNonNull(productDAO);
    }

    @Override
    public ProductId nextId() {
        return productId(UUID.randomUUID());
    }

    @Override
    public void add(Product product) {
        productDAO.save(createJPAProduct(product));
    }

    @Override
    public void update(Product product) {
        JPAProduct jpaProduct = productDAO.getOne(product.getId().getValue());
        productDAO.save(mapToJPAProduct(product, jpaProduct));
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productDAO.findById(productId.getValue())
                .map(this::mapToDomain);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDAO.findAll(pageable)
                .map(this::mapToDomain);
    }

    private Product mapToDomain(JPAProduct jpaProduct) {
        return Product.builder()
                .id(productId(jpaProduct.getId()))
                .name(jpaProduct.getName())
                .quantity(jpaProduct.getQuantity())
                .price(jpaProduct.getPrice())
                .createdOn(jpaProduct.getCreatedOn())
                .discounts(jpaProduct.getDiscounts())
                .alcoholPercentage(jpaProduct.getAlcoholPercentage())
                .weight(jpaProduct.getWeight())
                .build();
    }

    private JPAProduct createJPAProduct(Product from) {
        return mapToJPAProduct(from, new JPAProduct(from.getId().getValue()));
    }

    private JPAProduct mapToJPAProduct(Product from, JPAProduct to) {
        to.setName(from.getName());
        to.setQuantity(from.getQuantity());
        to.setPrice(from.getPrice());
        to.setCreatedOn(from.getCreatedOn());
        to.setDiscounts(from.getDiscounts());
        to.setAlcoholPercentage(from.getAlcoholPercentage());
        to.setWeight(from.getWeight());

        return to;
    }
}
