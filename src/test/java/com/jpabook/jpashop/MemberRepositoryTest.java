package com.jpabook.jpashop;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    // 엔티티 매니저를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 함
    @Test
    @Transactional // 테스트 케이스에 있으면 테스트가 끝난 후 바로 롤백을 함
    @Rollback(false) // 롤백 안함
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("riku");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // true

        // 같은 트랜잭션 안에서 저장하고 조회하면 영속성 컨택스트가 똑같음 -> 같은  트랜잭션안에서만 해당하는 건지 확인 필요 (TODO)
        // 같은 영속성 컨텍스트 안에서 아이디값이 같으면 같은 엔티티로 식별함
        // insert하고 아 나 영속성 컨텍스트에 있네? 하고 1차 캐시에서 가져온 값임
        System.out.println("(findMember == member) = " + (findMember == member));


    }


}