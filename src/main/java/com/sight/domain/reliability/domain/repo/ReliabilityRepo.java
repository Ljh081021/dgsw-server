package com.sight.domain.reliability.domain.repo;

import com.sight.domain.reliability.domain.Reliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReliabilityRepo extends JpaRepository<Reliability, Long> {
    List<Reliability> findByTarget(Long targetId);
}
