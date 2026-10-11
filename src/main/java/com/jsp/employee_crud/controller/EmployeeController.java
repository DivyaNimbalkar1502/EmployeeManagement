package com.jsp.employee_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.employee_crud.Entity.Employee;
import com.jsp.employee_crud.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@GetMapping("/")
	public String loadMain() {
		return "main.html";
	}
	@GetMapping("/add")
	public String add() {
		return "add.html";
	}
	@PostMapping("/add")
	public String loadadd(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		
		employeeRepository.save(employee);
		attributes.addFlashAttribute("msg", "data savesd success");
		return "redirect:/";
	}
	@GetMapping("/view")
	public String view(ModelMap map) {
		List<Employee> emp=employeeRepository.findAll();
		map.put("employee", emp);
		
		return "view.html";
		
	}
	@GetMapping("/edit")
	public String edit(@RequestParam("id") int id, ModelMap map) {
	    Employee emp = employeeRepository.findById(id).orElse(null);
	    map.put("employee", emp);  
	    return "edit.html";         
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Employee emp,RedirectAttributes ar) {
		employeeRepository.save(emp);
		ar.addFlashAttribute("msg","the records are updated");
		return "redirect:/view";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id, RedirectAttributes attributes) {
	    employeeRepository.deleteById(id);
	    attributes.addFlashAttribute("msg", "Are you sure you want to delete!");
	    return "redirect:/view";
	}

}
