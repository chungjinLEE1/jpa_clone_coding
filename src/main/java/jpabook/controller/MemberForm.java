package jpabook.controller;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class MemberForm {
    @Id
    @Column(name = "member_id", nullable = false)
    private Long id;

    @NotEmpty(message = "회원이름은 필수입니다.")
    private String name;

    private String city;

    private String street;

    private String zipcode;

}
