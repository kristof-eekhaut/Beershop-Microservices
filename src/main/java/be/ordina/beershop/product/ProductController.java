package be.ordina.beershop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static be.ordina.beershop.product.ProductId.productId;
import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProduct createProduct) {

        Product product = Product.builder()
                .id(productRepository.nextId())
                .name(createProduct.getName())
                .alcoholPercentage(createProduct.getAlcoholPercentage())
                .weight(createProduct.getWeight())
                .build();

        productRepository.add(product);
        return ResponseEntity.created(URI.create("/products/" + product.getId().getValue())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") UUID productId, @RequestBody @Valid UpdateProduct updateProduct) {
        if (updateProduct.getQuantity() == 0) {
            return ResponseEntity.badRequest().body("Quantity may not be 0");
        }
        productRepository.findById(productId(productId))
                         .ifPresent(originalProduct -> {
                             originalProduct.setQuantity(updateProduct.getQuantity());
                             productRepository.update(originalProduct);
                         });
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResource>> getProducts(@PageableDefault(size = 20, sort = "id", direction = DESC) final Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);
        Page<ProductResource> productResources = products.map(this::mapToResource);

        return ResponseEntity.ok().body(productResources);
    }

    private ProductResource mapToResource(Product product) {
        return ProductResource.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .createdOn(product.getCreatedOn())
                .discounts(product.getDiscounts())
                .alcoholPercentage(product.getAlcoholPercentage())
                .weight(product.getWeight())
                .build();
    }
}
