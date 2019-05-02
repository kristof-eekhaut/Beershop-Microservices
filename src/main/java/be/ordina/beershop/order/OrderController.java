package be.ordina.beershop.order;

import be.ordina.beershop.customer.*;
import be.ordina.beershop.service.BeerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static be.ordina.beershop.order.OrderId.orderId;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BeerShopService beerShopService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateOrder createOrder) {

        final Optional<Customer> maybeCustomer = customerRepository.findById(CustomerId.customerId(createOrder.getCustomerId()));
        if(!maybeCustomer.isPresent()) {
            return ResponseEntity.badRequest().body("Unknown customer");
        }

        final JPAOrder order = beerShopService.createOrder(createOrder);
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResource> getOrder(@PathVariable("id") UUID id) {
        return orderRepository.findById(orderId(id))
                .map(this::mapToResource)
                .map(orderResource -> ResponseEntity.ok().body(orderResource))
                .orElse(ResponseEntity.notFound().build());
    }

    private OrderResource mapToResource(Order order) {
        return OrderResource.builder()
                .orderId(order.getId().getValue())
                .customer(order.getCustomer())
                .lineItems(order.getLineItems())
                .state(order.getState())
                .createdOn(order.getCreatedOn())
                .address(order.getAddress())
                .shipmentId(order.getShipmentId())
                .build();
    }
}