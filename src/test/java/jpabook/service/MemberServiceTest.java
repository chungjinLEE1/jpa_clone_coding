package jpabook.service;

import jpabook.domain.Member;
import jpabook.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberSerivce;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) // transactional은 기본적으로 rollback 해버린다.
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim3");

        // when
        Long saveId = memberSerivce.join(member);

        // then
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim2");

        // when
        memberSerivce.join(member1);

        memberSerivce.join(member2); // 예외가 발생한다.
        // then
        fail("예외가 발생해야 한다.");
    }

}