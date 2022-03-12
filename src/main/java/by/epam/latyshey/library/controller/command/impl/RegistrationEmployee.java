package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.EmployeeService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class RegistrationEmployee implements Command {

  @Override
  public String execute(String request) {

    String username = request.split(",")[2];
    String name = request.split(",")[4];
    String pass = request.split(",")[6];
    int age = Integer.parseInt(request.split(",")[8]);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    EmployeeService employeeService = serviceFactory.getEmployeeService();

    return employeeService.registration(new Employee(username, name, pass, age));
  }
}
