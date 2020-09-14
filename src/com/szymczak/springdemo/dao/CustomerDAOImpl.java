package com.szymczak.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szymczak.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, customerId);
		return theCustomer;
	}


	@Override
	public void delete(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", customerId);
		query.executeUpdate();
		
		
	}


	@Override
	public List<Customer> getCustomerByFirstOrLastName(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> createQuery = currentSession.createQuery("from Customer where firstName=:name OR lastName=:name order by lastName", Customer.class);
		createQuery.setParameter("name", name);
		return createQuery.getResultList();
	}

}
