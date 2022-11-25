package com.example.sweater.demo.repository;

import com.example.sweater.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);
    User getById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password =?2 WHERE id=?1", nativeQuery = true)
    void  updateUserPassword(Long id, String password);

    @Transactional
    @Modifying
    User save(User user);

}
