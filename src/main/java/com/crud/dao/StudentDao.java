package com.crud.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.crud.model.Student;



@Repository
public class StudentDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// save student
	@Transactional
	public void saveStudent(Student student) {
		this.hibernateTemplate.save(student);
	}

	// get all student
	public List<Student> getAllStudent() {
		return this.hibernateTemplate.loadAll(Student.class);
	}

	// get single student
	public Student getStudentById(Long id) {
		return this.hibernateTemplate.get(Student.class, id);
	}

	// update student
	@Transactional
	public void updateStudent(Student student) {
		this.hibernateTemplate.update(student);
	}

	// delete Student
	@Transactional
	public void deleteStudent(Long id) {
		this.hibernateTemplate.delete(hibernateTemplate.get(Student.class, id));
	}

}
