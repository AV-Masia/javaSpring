package fs.spring.vue.repository;

import fs.spring.vue.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User getById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password =?2 WHERE id=?1", nativeQuery = true)
    void  updateUserPassword(Long id, String password);
}
