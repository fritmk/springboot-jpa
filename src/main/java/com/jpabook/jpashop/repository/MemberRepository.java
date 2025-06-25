package com.jpabook.jpashop.repository;


import com.jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// 스프링 빈으로 등록
// 컴포넌트 스캐의 대상이 돼서 스프링부트애플리케이션 어노테이션이 있으면 이 패키지와 패키지 하위의 있는 것들을 모두 스프링에 컴포넌트 스캔을 함.
// 컴포넌트 스캔을 통해서 스프링 빈에 자동 등롣된다.
@Repository
@RequiredArgsConstructor
public class MemberRepository {


    // JAP가 제공하는 표준 어노테이션
//    @PersistenceContext // jpa 엔티티 매니저를 스프링이 생성한 엔티티 매니저에 주입해준다.
//    private EntityManager em; // 스프링이 entity manager 를 만들어서 주입해줌

    private final EntityManager em; // spring data jpa 가 이렇게 사용하는 것을 지원해줌


    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // jpql - 엔티티 객체를 대상으로 조회를 한다고 봐야 함
        return em.createQuery("select m from Member m", Member.class)
              .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
