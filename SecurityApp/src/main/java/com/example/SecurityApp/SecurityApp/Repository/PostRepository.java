package com.example.SecurityApp.SecurityApp.Repository;

import com.example.SecurityApp.SecurityApp.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long>{
}
