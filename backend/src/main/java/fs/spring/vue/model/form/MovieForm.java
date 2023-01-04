package fs.spring.vue.model.form;

import fs.spring.vue.model.Genre;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class MovieForm {

    private Long id;
    private String title;
    private String tagline;
    private double vote_average;
    private int vote_count;
    private String release_date;
    private String poster_path;
    private String overview;
    private int budget;
    private int revenue;
    private int runtime;

    @Builder.Default
    private Set<Genre> genres = new HashSet<>();

}
