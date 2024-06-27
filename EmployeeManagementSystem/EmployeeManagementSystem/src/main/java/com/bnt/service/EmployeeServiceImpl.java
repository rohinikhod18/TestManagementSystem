package com.bnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bnt.entity.Employee;
import com.bnt.exception.DuplicateEmployeeException;
import com.bnt.exception.EmployeeNotFoundException;
import com.bnt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee register(Employee employee) {
		if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
			throw new DuplicateEmployeeException("Employee with email '" + employee.getEmail() + "' already exists");
		}
		return employeeRepository.save(employee);
	}

	@Override
	public Employee login(String email, String password) {

		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + employeeId));

		Employee getEmployee = new Employee();
		getEmployee.setEmployeeId(employee.getEmployeeId());
		getEmployee.setFirstName(employee.getFirstName());
		getEmployee.setLastName(employee.getLastName());
		getEmployee.setEmail(employee.getEmail());
		getEmployee.setPassword(employee.getPassword());
		return getEmployee;
	}

	@Override
	public Employee updateEmployee(Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeDetails.getEmployeeId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeDetails.getEmployeeId()));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setPassword(employeeDetails.getPassword());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee deleteEmployee(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
		employeeRepository.delete(employee);
		return employee;
	}
	
}
