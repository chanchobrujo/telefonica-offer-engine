package com.telefonica.offerengine.Interface;

import java.util.Optional;

import com.telefonica.offerengine.Data.LineMobile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LineMobileRepository extends JpaRepository<LineMobile, Integer> {
    public Optional<LineMobile> findByIdlinemobile(int idlinemobile);

    public Optional<LineMobile> findByNumberphone(String numberphone);

    public boolean existsByIdlinemobile(int idlinemobile);

    public boolean existsByNumberphone(String numberphone);
}
