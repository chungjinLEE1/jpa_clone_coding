package jpabook.service;

import jpabook.domain.Address;
import jpabook.domain.Member;
import jpabook.domain.Order;
import jpabook.domain.OrderStatus;
import jpabook.domain.item.Book;
import jpabook.domain.item.Item;
import jpabook.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
           // given
            Member member = new Member();
            member.setName("회원1");
            member.setAddress(new Address("서울", "강가", "123-123"));
            em.persist(member);

            Book book = new Book();
            book.setName("시골 JPA");
            book.setPrice(10000);
            book.setStockQuantity(10);
            em.persist(book);

            int orderCount = 2;

           // when
            Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        System.out.println("#####orderId" + orderId);

           // then
            Order getOrder = orderRepository.findOne(orderId);

//            assertEquals("상품 주문시 상태는 ORDER", OrderStatus.Order, getOrder.getStatus());
            assertEquals("주문한 상품 종류 수가 정확해야 한다. ", 1, getOrder.getOrderItems().size());
            assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());

    }

    @Test
    public void 주문취소() throws Exception {
        // given


        // when

        // then

    }


    @Test
    public void 상품주문_재고수량초과() throws Exception {
        // given


        // when

        // then

    }


}