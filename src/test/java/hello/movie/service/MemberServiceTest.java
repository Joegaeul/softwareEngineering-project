package hello.movie.service;

import hello.movie.dto.MemberDto.CreateMemberDto;
import hello.movie.model.Member;
import hello.movie.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private static MemberService memberService;

    @Test
    void join() {
        // given
        CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .userId("testA")
                .password("0000")
                .build();

        // when
        Optional<Member> member = memberService.join(createMemberDto);

        // then
        assertThat(member.get().getUserId()).isEqualTo(createMemberDto.getUserId());
    }

    @Test
    void update() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByNickname() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void createMemberDtoToMember() {
    }

    @Test
    void createMemberInfoResponseDto() {
    }

    @Test
    void createSearchMemberResponseDto() {
    }
}