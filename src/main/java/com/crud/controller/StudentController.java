package com.crud.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crud.model.Student;
import com.crud.service.StudentService;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;


@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/studentReport")
	public String studentReport() {
		
		return "studentReport";
	}
	
	//get the date to display through Ajax
	@GetMapping("/date")
	@ResponseBody
	public String getDateTime() {
	
		 // Get the current date and time
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Use the format() method to convert Date to String
        String dateString = dateFormat.format(currentDate);

        // Return the formatted date string
        return dateString;
	}
	
	//process form insert
	@PostMapping("/insertStudent")
	@ResponseBody
	public String saveStudent(@ModelAttribute Student student) {
		this.studentService.saveStudent(student);
		return "save";
	}
	
	//get all student
	@GetMapping("/getAllStudent")
	public @ResponseBody List<Student> getAllStudent() {
		List<Student> allStudents = this.studentService.getAllStudents();
		return allStudents;
	}
	
	//get the single record
	@GetMapping("/getSingleStudent/{id}")
	@ResponseBody
	public Student getById(@PathVariable Long id) {
		 return this.studentService.getStudentById(id);
	}
	
	//update student
	@PostMapping("/updateStudent")
	@ResponseBody
	public String updateStudent(@ModelAttribute Student student ) {
		this.studentService.updateStudent(student);
		return "update";
	}
	
	//delete student
	@GetMapping("/deleteStudent/{id}")
	@ResponseBody
	public String deleteStudent(@PathVariable Long id) {
		this.studentService.deleteStudent(id);
		return "delete";
	}
	

}
