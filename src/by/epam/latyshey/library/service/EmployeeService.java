package by.epam.latyshey.library.service;

import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.interfaces.IEmployee;


public interface EmployeeService {

  void registration(IEmployee employee);

  String showHistory();

  String showCustomers();
}
