package jpabook.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member  {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;


    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();


}
