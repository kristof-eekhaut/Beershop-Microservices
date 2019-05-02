package be.ordina.beershop.controller;

import be.ordina.beershop.product.JPAProduct;
import be.ordina.beershop.product.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ProductDAO repository;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JPAProduct item) {
        item.setId(UUID.randomUUID());
        item.setCreatedOn(LocalDateTime.now());
        JPAProduct savedItem = repository.save(item);
        return ResponseEntity.created(URI.create("/items/" + savedItem.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JPAProduct> getItem(@PathVariable("id") UUID id) {
        Optional<JPAProduct> item = repository.findById(id);
        return item.map(it -> ResponseEntity.ok().body(it)).get();
    }
}
