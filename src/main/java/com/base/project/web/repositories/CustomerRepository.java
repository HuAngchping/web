package com.base.project.web.repositories;

import com.base.project.web.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by huang on 4/23/15.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.username = ?1")
    Customer findByUsername(String username);

    Page<Customer> findAll(Pageable pageable);
}
