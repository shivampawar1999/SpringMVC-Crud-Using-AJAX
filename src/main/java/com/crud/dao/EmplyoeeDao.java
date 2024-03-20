package com.crud.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.crud.model.Employee;

@Repository
public class EmplyoeeDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// add employee
	@Transactional
	public void addEmployee(Employee employee) {
		this.hibernateTemplate.save(employee);
	}

	// allEmployee
	public List<Employee> getAllEmployee() {
		return this.hibernateTemplate.loadAll(Employee.class);
	}

	// get employee by id
	public Employee getEmployById(long id) {
		return this.hibernateTemplate.get(Employee.class, id);
	}

	// update Employee
	@Transactional
	public void updateEmployee(Employee employee) {
		this.hibernateTemplate.update(employee);
	}

	// Delete Employee
	@Transactional
	public void deleteEmployee(long id) {
		this.hibernateTemplate.delete(hibernateTemplate.load(Employee.class, id));
	}


}
