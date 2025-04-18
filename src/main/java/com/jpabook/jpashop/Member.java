package com.jpabook.jpashop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue // 자동 생성해줌
    private long id;
    private String username;


}
