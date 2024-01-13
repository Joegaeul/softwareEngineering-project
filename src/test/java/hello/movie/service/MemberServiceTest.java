package hello.movie.service;

import hello.movie.dto.MemberDto.CreateMemberDto;
import hello.movie.model.Member;
import hello.movie.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private static MemberService memberService;

    @Test
    @DisplayName("회원가입 성공")
    void join() {
        // given
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .userId("testA")
                .password("0000")
                .build();
        String encodePw = encoder.encode(createMemberDto.getPassword());

        doReturn(Member.builder().userId(createMemberDto.getUserId()).password(encodePw).build())
                .when(memberRepository).save(any(Member.class));

        // when
        Optional<Member> member = memberService.join(createMemberDto);

        // then
        assertThat(member.get().getUserId()).isEqualTo(createMemberDto.getUserId());
        assertThat(encoder.matches(createMemberDto.getPassword(), member.get().getPassword())).isTrue();

        // verify
        verify(memberRepository, times(1)).save(any(Member.class));
        verify(bCryptPasswordEncoder, times(1)).encode(any(String.class));
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