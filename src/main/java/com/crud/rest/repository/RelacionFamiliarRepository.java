package com.crud.rest.repository;

import com.crud.rest.entity.RelacionFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionFamiliarRepository extends JpaRepository<RelacionFamiliar, Long> {
}
