package jpabook.repository;

import jpabook.domain.Order;
import jpabook.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }


    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public OrderStatus findAll(OrderStatus orderSearch){return em.find(OrderStatus.class, orderSearch ); }

}
