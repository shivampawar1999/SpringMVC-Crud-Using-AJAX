package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crud.dao.EmplyoeeDao;
import com.crud.model.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	private EmplyoeeDao emplyoeeDao;
	
	// add employee
		public void addEmployee(Employee employee) {
			this.emplyoeeDao.addEmployee(employee);
		}

		// allEmployee
		public List<Employee> getAllEmployee() {
			return this.emplyoeeDao.getAllEmployee();
		}

		// get employee by id
		public Employee getEmployById(long id) {
			return this.emplyoeeDao.getEmployById(id);
		}

		// update Employee
		public void updateEmployee(Employee employee) {
			this.emplyoeeDao.updateEmployee(employee);
		}

		// Delete Employee
		public void deleteEmployee(long id) {
			this.emplyoeeDao.deleteEmployee(id);
		}

	

}
