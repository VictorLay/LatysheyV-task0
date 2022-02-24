package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.EmployeeService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class ShowCustomers implements Command {

  @Override
  public String execute(String query) {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    EmployeeService employeeService = serviceFactory.getEmployeeService();

    String response = employeeService.showCustomers();
    return response;
  }
}
