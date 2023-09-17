package com.satya.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.satya.crm.exception.ResourceNotFoundException;
import com.satya.crm.model.Customer;
import com.satya.crm.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public ResponseEntity<List<Customer>> getAllCustomer() {
		// Create a Sort object to specify descending order by a specific property
        Sort descendingSort = Sort.by(Sort.Order.desc("id"));
        
		List<Customer> customerList = customerRepository.findAll(descendingSort);
		return ResponseEntity.ok(customerList);
	}

	public ResponseEntity<Customer> addCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		return new ResponseEntity<>(savedCustomer,HttpStatus.CREATED);
	}

	public ResponseEntity<Customer> getCustomer(long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not found with id : "+id));
		return ResponseEntity.ok(customer);
	}

	public ResponseEntity<Customer> updateCustomer(long id, Customer customer) {
		Customer oldCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not found with id : "+id));
		oldCustomer.setFirstName(customer.getFirstName());
		oldCustomer.setLastName(customer.getLastName());
		oldCustomer.setEmail(customer.getEmail());
		return ResponseEntity.ok(customerRepository.save(oldCustomer));
	}

	public ResponseEntity<String> deleteCustomer(long id) {
		customerRepository.deleteById(id);
		return ResponseEntity.ok("Deleted Customer with id : "+id);
	}
	
	
}
