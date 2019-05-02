package be.ordina.beershop.order;


import java.util.Optional;

public interface OrderRepository {

    OrderId nextId();

    Optional<Order> findById(OrderId orderId);
}
