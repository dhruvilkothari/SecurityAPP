package com.example.SecurityApp.SecurityApp.Repository;

import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
