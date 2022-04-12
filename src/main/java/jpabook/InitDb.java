package jpabook;

import jpabook.domain.*;
import jpabook.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


/*
* 총 주문 2개
* * userA
*   * JPA1 BOOK
*   * JPA2 BOOK
* * userB
*   * SPRING BOOK1
*   * SPRING BOOK2
* */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit1(){

            Member member = createMember("서울", "1", "1111");
            em.persist(member);



            Book book1 = createBook("JPA1 BOOK", 10000, 500);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 300);
            em.persist(book2);

            Book book3 = createBook("JPA3 BOOK", 15000, 200);
            em.persist(book3);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 1);

            Order order = createDelivery(member, orderItem1, orderItem2);
            em.persist(order);
        }

        @NotNull
        private Order createDelivery(Member member, OrderItem orderItem1, OrderItem orderItem2) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            return order;
        }

        @NotNull
        private Member createMember(String name, String street, String zipcode) {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address(name, street, zipcode));
            return member;
        }


        @NotNull
        private Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity); // ctrl + atrl + p : 변수 뽑기
            return book1;
        }


        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = createMember(city, street, zipcode);
            return member;
        }
        public void dbInit2(){
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SRPING1 BOOK", 5000, 500);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 100000, 500);
            em.persist(book2);

            Book book3 = createBook("SPRING3 BOOK", 20000, 300);
            em.persist(book3);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 1);

            Order order = createDelivery(member, orderItem1, orderItem2);
            em.persist(order);
        }


    }

}

