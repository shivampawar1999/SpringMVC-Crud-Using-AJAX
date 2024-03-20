package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.StudentDao;
import com.crud.model.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	// save
	public void saveStudent(Student student) {
		this.studentDao.saveStudent(student);
	}

	// get all student
	public List<Student> getAllStudents() {
		return this.studentDao.getAllStudent();
	}

	// get single student
	public Student getStudentById(Long id) {
		return studentDao.getStudentById(id);
	}

	// update student
	public void updateStudent(Student student) {
		this.studentDao.updateStudent(student);
	}

	// delete student
	public void deleteStudent(Long id) {
		this.studentDao.deleteStudent(id);
	}

}
