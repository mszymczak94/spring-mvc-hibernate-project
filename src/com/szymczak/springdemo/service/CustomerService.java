package com.szymczak.springdemo.service;

import java.util.List;

import com.szymczak.springdemo.entity.Customer;

public interface CustomerService {
	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int customerId);

	void delete(int customerId);

	List<Customer> getCustomerByFirstOrLastName(String name);
}
