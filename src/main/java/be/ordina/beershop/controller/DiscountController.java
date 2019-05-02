package be.ordina.beershop.controller;

import be.ordina.beershop.product.Discount;
import be.ordina.beershop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static be.ordina.beershop.product.ProductId.productId;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Void> createDiscount(@RequestBody Discount discount) {
        productRepository.findById(productId(discount.getProductId()))
                         .ifPresent(product -> {
                             product.addDiscount(discount);
                             productRepository.update(product);
                         });
        return ResponseEntity.ok().build();
    }

}
