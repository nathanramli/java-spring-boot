package com.alterra.relationship.repository;

import com.alterra.relationship.domain.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductDao, Long> {
    public List<ProductDao> findAllByOrderByCategoryAsc();
    public List<ProductDao> findAllByOrderByCategoryDesc();
    public List<ProductDao> findAllByNameContaining(String name);
}
