package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
//@Transactional // jpa의 모든 데이터 변경이나 어떤 로직들은 가급적이면 트랜잭션 안에서 실행되어야함
@Transactional(readOnly = true) // 조회하는 곳에서 성능이 더 좋아짐
//@AllArgsConstructor // 필드를 가지고 생성자를 만들어줌
@RequiredArgsConstructor // final 인 필드만 가지고 생성자를 만들어줌
public class MemberService {

    // 기본적으로 인젝션 하면서 생성자에서 세팅하고 끝날 것들은 파이널로 잡음.
    // 이외 필요한 필드가 생길 수 있으니 RequiredArgsConstructor 쓰는 것을 권장
    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional // 따로 설정한 건 이게 우선순위가 높음.
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    // 이렇게 하더라도 서버를 여러개 띄워놓고 동시에 추가되는 요청이 들어오면 중복될 수 있으므로
    // 멀티스레드와 같은 상황을 고려하여, 데이터베이스 자체에 이름에 대한 유니터 제약을 걸어두는 것이 더 좋음.
    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 한건
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
