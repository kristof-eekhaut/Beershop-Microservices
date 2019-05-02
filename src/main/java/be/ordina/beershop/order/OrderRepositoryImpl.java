package be.ordina.beershop.order;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static be.ordina.beershop.order.OrderId.orderId;
import static java.util.Objects.requireNonNull;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private OrderDAO orderDAO;

    OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = requireNonNull(orderDAO);
    }

    @Override
    public OrderId nextId() {
        return orderId(UUID.randomUUID());
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderDAO.findById(orderId.getValue())
                .map(this::mapToDomain);
    }

    private Order mapToDomain(JPAOrder jpaOrder) {
        return Order.builder()
                .id(orderId(jpaOrder.getId()))
                .customer(jpaOrder.getCustomer())
                .lineItems(jpaOrder.getLineItems())
                .state(jpaOrder.getState())
                .createdOn(jpaOrder.getCreatedOn())
                .address(jpaOrder.getAddress())
                .shipmentId(jpaOrder.getShipmentId())
                .build();
    }
}
