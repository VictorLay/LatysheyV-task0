package by.epam.latyshey.library.service;

import by.epam.latyshey.library.bean.interfaces.IEmployee;


public interface EmployeeService {

  String registration(IEmployee employee);

  String showHistory();

  String showCustomers();
}
