package hello.movie.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class MovieCrew {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CREW_ID")
//    private Crew crew;

    private String name;

    private String character;

    private String profilePath;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
