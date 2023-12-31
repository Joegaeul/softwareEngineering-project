package hello.movie.repository;

import hello.movie.model.Member;
import hello.movie.model.MemberPreferredGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreferredGenreRepository extends JpaRepository<MemberPreferredGenre, Long> {
    Optional<List<MemberPreferredGenre>> findByMember(Member member);
    void deleteByMember(Member member);
}
