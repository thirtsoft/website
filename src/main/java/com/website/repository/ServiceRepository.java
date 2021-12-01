package com.website.repository;

import com.website.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("select count(p) from Service p ")
    BigDecimal countNumberOfService();

    List<Service> findTop12ByOrderByCreatedDateDesc();

    List<Service> findByOrderByIdDesc();
}
