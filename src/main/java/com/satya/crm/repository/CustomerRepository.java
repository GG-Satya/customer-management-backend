package com.satya.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.crm.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
