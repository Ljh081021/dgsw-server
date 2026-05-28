package com.sight.domain.bestpost.domain.repo;

import com.sight.domain.bestpost.domain.BestPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestPostRepo extends JpaRepository<BestPost, Long> {
}
