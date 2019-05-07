package be.ordina.beershop.integrationTests.order;

import be.ordina.beershop.customer.CustomerTestData;
import be.ordina.beershop.integrationTests.IntegrationTest;
import be.ordina.beershop.order.OrderMatcher;
import be.ordina.beershop.order.OrderTestData;
import be.ordina.beershop.product.JPAProductTestData;
import be.ordina.beershop.repository.entities.*;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static be.ordina.beershop.shoppingcart.JPAShoppingCartTestData.cartWithItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PayOrderITest extends IntegrationTest {

    @Test
    public void givenOrder_whenPayingOrder_thenOrderIsPaid() throws Exception {

        JPAProduct karmeliet = persistProduct(JPAProductTestData.karmeliet().build());

        Customer customer = persistCustomer(CustomerTestData.manVanMelle().build());
        JPAShoppingCart shoppingCart = persistShoppingCart(cartWithItems(customer.getId(), karmeliet).build());

        Order order = persistOrder(OrderTestData.unpaidOrder(customer, shoppingCart.getId(), karmeliet).build());

        mockMvc.perform(
                patch("/orders/" + order.getId() + "/pay"))
                .andDo(print())
                .andExpect(status().isOk());

        runInTransaction(() -> {
            Order updatedOrder = orderRepository.findById(order.getId()).get();
            MatcherAssert.assertThat(updatedOrder, OrderMatcher.matchesOrder(OrderTestData.unpaidOrder(customer, shoppingCart.getId(), karmeliet)
                    .state(OrderStatus.PAID)
                    .build()));
        });
    }
}
