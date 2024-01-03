package hello.movie.repository;

import hello.movie.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 생성")
    void createMember(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").build();

        //when
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        //then
        assertThat(result1.getUserId()).isEqualTo(member1.getUserId());
        assertThat(result2.getUserId()).isEqualTo(member2.getUserId());
    }

    @Test
    @DisplayName("자신을 제외한 멤버 리스트 반환")
    void findByIdNot(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").build();
        Member member3 = Member.builder().userId("testC").password("testC").build();
        Member save1 = memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> result = memberRepository.findByIdNot(save1.getId());

        //then
        assertThat(result.size()).isEqualTo(2);

        for (Member member : result){
            assertThat(member.getId()).isNotEqualTo(save1.getId());
        }
    }

    @Test
    @DisplayName("아이디로 멤버 조회")
    void findById(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").build();
        Member save1 = memberRepository.save(member1);
        Member save2 = memberRepository.save(member2);

        //when
        Optional<Member> result1 = memberRepository.findById(save1.getId());
        Optional<Member> result2 = memberRepository.findById(save2.getId());

        //then
        assertThat(result1.get().getId()).isEqualTo(save1.getId());
        assertThat(result2.get().getId()).isEqualTo(save2.getId());
    }

    @Test
    @DisplayName("사용자 아이디로 멤버 조회")
    void findByUserId(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").build();
        Member save1 = memberRepository.save(member1);
        Member save2 = memberRepository.save(member2);

        //when
        Optional<Member> result1 = memberRepository.findByUserId(save1.getUserId());
        Optional<Member> result2 = memberRepository.findByUserId(save2.getUserId());

        //then
        assertThat(result1.get().getUserId()).isEqualTo(save1.getUserId());
        assertThat(result2.get().getUserId()).isEqualTo(save2.getUserId());
    }

    @Test
    @DisplayName("자신을 제외한 사용자 아이디로 멤버 조회")
    void findByUserIdAndIdNot(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").build();
        Member save1 = memberRepository.save(member1);
        Member save2 = memberRepository.save(member2);

        //when
        Optional<Member> result1 = memberRepository.findByUserIdAndIdNot(save1.getUserId(), save1.getId());
        Optional<Member> result2 = memberRepository.findByUserIdAndIdNot(save2.getUserId(), save1.getId());

        //then
        assertThat(result1).isEmpty();
        assertThat(result2.get().getUserId()).isEqualTo(save2.getUserId());
    }

    @Test
    @DisplayName("자신을 제외한 사용자 닉네임으로 멤버 리스트 조회")
    void findByNicknameAndIdNot(){
        //given
        Member member1 = Member.builder().userId("testA").password("testA").nickname("testA").build();
        Member member2 = Member.builder().userId("testB").password("testB").nickname("testA").build();
        Member member3 = Member.builder().userId("testC").password("testC").nickname("testA").build();
        Member save1 = memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> result1 = memberRepository.findByNicknameAndIdNot(save1.getNickname(), save1.getId());

        //then
        assertThat(result1.size()).isEqualTo(2);

        for (Member member : result1){
            assertThat(member.getId()).isNotEqualTo(save1.getId());
        }
    }
}