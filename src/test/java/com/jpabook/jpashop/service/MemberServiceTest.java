package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 스프링 부트를 띄운 상태에서 테스트하려면 이 어노테이션이 필요하다
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given - 이런 것이 주어졌을 때
        Member member = new Member();
        member.setName("fear");

        // when - 이렇게 하면
        Long savedId = memberService.join(member);
        
        // then - 이렇게 된다..

        em.flush();
        // jpa에서 같은 트랜잭션 안에서 같은 엔티티, 즉 아이디 값이 같으면 얘는 같은 영속성 컨텍스트에서 관리가 된다.
        assertEquals(member, memberRepository.findOne(savedId));

    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("riku");

        Member member2 = new Member();
        member2.setName("riku");

        // when
        memberService.join(member1);

        // then
        // IllegalStateException 예외가 발생하지 않으면 테스트 실패
        assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
//        fail("에외가 발생해야 한다."); // 예외가 터지면 여기까지 오면 안됨. 예외 발생하고 나가야 함.
    }
}