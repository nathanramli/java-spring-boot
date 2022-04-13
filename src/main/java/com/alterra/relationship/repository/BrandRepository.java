package com.alterra.relationship.repository;

import com.alterra.relationship.domain.dao.BrandDao;
import com.alterra.relationship.domain.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandDao, Long> {
}
