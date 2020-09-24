package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> finaAll() {
		//create a query
		Query theQuery = entityManager.createQuery("from Employee");
		
		//execute the query & get the result
		List<Employee> results = theQuery.getResultList();
		
		//return result
		return results;
	}

	@Override
	public Employee findById(int theId) {
		// get the employee by id
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//return the employe
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		//update with id from db...so that we can get new id for inserted/saved employee
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		// delete employee with primary key
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
		

	}

}
