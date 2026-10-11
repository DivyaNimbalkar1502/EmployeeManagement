package com.jsp.employee_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.employee_crud.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {

}
