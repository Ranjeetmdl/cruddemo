package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define field for entityManager
	private EntityManager entityManager;
	
	//perform construct injection for entityManager
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override public List<Employee> finaAll() {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//execute the query and get the result list
		List<Employee> theEmployees = theQuery.getResultList();
		
		//return the result
		return theEmployees;
	}

	
	@Override public Employee findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get the employee 
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		//return the employee
		return theEmployee;
	}

	
	@Override public void save(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save the employee -*** if id=0, it will perform save/insert else will perform update
		currentSession.saveOrUpdate(theEmployee);
		
	}

	
	@Override public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query & delete the employee with primary key
		Query deleteQuery = currentSession.createQuery("delete from Employee where id=:theEmployeeId");
		
		deleteQuery.setParameter("theEmployeeId", theId);
		
		deleteQuery.executeUpdate();
		
		
	}



}
