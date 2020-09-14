package com.szymczak.springdemo.dao;

import java.util.List;

import com.szymczak.springdemo.entity.Customer;

public interface CustomerDAO {
	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int customerId);

	void delete(int customerId);
}
