package com.soap.soapListener;

import javax.jws.WebService;

@WebService
public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployeeById(String id) {
		System.out.println("1 값이 제대로 들어오는지 : " + id);
		return new Employee(id, "Hong Gill Dong");
	}

}
