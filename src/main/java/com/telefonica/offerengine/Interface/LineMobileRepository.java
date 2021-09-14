package com.telefonica.offerengine.Interface;

import com.telefonica.offerengine.Data.LineMobile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineMobileRepository extends JpaRepository<LineMobile, Integer> {
    public Optional<LineMobile> findByIdlinemobile(int idlinemobile);

    public Optional<LineMobile> findByNumberphone(String numberphone);

    public boolean existsByIdlinemobile(int idlinemobile);

    public boolean existsByNumberphone(String numberphone);
}
