package fs.spring.vue.repository;

import fs.spring.vue.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre getGenreByName(String name);

    @Query(value = "SELECT * FROM genres WHERE id=?1", nativeQuery = true)
    Genre getById(Long id);

}
