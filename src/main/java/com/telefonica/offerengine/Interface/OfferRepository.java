package com.telefonica.offerengine.Interface;

import java.util.Optional;

import com.telefonica.offerengine.Data.Offer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    public Optional<Offer> findByIdoffer(int idoffer);

    public Optional<Offer> findByCode(String code);

    public boolean existsByIdoffer(int idoffer);

    public boolean existsByCode(String code);
}
