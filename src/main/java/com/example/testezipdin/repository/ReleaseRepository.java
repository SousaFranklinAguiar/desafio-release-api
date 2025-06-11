package com.example.testezipdin.repository;

import com.example.testezipdin.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Optional<Release> findByIdAndDeletedAtIsNull(Long id);
}
