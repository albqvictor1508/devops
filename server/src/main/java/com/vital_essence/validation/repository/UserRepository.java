package com.vital_essence.validation.repository;

import com.vital_essence.validation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUsername(String username);
}
