package com.vital_essence.validation.repository;

import com.vital_essence.validation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.username = :username", nativeQuery = true)
    public User findByUsername(@Param("username") String username);
    public boolean existsByUsername(String username);
}
