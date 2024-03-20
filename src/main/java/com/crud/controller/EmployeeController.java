package com.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.crud.model.Employee;
import com.crud.service.EmployeeService;



@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/addEmployee")
	public String showAddEmployee(Model model) {
		
		model.addAttribute("title","addEmployeeForm");
		
		return "addEmplyeeForm";
	}
	
	@PostMapping("/processAddEmployee")
	public RedirectView process(@ModelAttribute Employee employee, HttpServletRequest req) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath() + "/");
		this.employeeService.addEmployee(employee);
		return redirectView;
	}
	
	
	@GetMapping("/getAllEmployee")
	public String getAllEmployee(Model model) {
		
		model.addAttribute("title","getAllEmployee");
		
		List<Employee> allEmployee = this.employeeService.getAllEmployee();
		
		model.addAttribute("allEmployee", allEmployee);
		
		return "showAllEmployee";
	}
	
	
	@GetMapping("/deleteEmployee/{id}")
	public RedirectView delete(@PathVariable("id") long id,  HttpServletRequest req) {
		RedirectView  redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath() + "/");
		this.employeeService.deleteEmployee(id);
		return redirectView;
	}
	
	
	
	@GetMapping("/editEmployee/{id}")
	public String editEmployee(@PathVariable(value = "id") long id,Model model) {
		
		Employee employee = this.employeeService.getEmployById(id);
		System.out.println(employee);
		model.addAttribute("title","editEmployeeForm");
		model.addAttribute("employee", employee);
		return "editEmployee";
	}
	
	
	@PostMapping("/updateEmployee")
	public RedirectView processUpdate(@ModelAttribute Employee employee, HttpServletRequest req) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath() + "/");
		this.employeeService.updateEmployee(employee);
		return redirectView;
	}
	

}