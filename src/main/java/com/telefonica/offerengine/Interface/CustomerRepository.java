package com.telefonica.offerengine.Interface;

import com.telefonica.offerengine.Data.Customer; 
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByIdcustomer(int idcustomer);

    public Optional<Customer> findByNumberdocument(String numberdocument);

    public boolean existsByIdcustomer(int idcustomer);

    public boolean existsByNumberdocument(String numberdocument);
}
