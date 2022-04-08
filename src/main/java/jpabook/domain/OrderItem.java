package jpabook.domain;

import jpabook.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected OrderItem() {  } 생성자랑 같음.
@AllArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량


//    생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem; 
    }


//   비즈니스 로직.
    public void cancel() {
        getItem().addStock(count);
    }


    
//    조회 로직
    /*
    * 주문 상품 전체 가격 조회
    * */
    public int getTotalPrice(){
        return getOrderPrice() * getCount(); 
    }
}