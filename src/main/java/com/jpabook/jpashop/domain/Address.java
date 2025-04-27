package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;


// 값타임. 변경이 되며 안된다.
@Embeddable // = 어딘가에 내장이 될 수 있다
@Getter
public class Address {

    private String city;
    private String street;
    private  String zipcode;

    // JPA에서는 protected. jpa 스펙상으로 만들어놓은 것임
    // reflection 이나 뭐 이런 기술들을 써야 되는데 reflection 이나 proxy를 해야하는데 기본 생성자가 없으면 불가능함.
    // 그래서 기본 생성자를 만들어 놓는다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
