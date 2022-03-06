package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class RegistrationCustomer implements Command {

  @Override
  public String execute(String query) {

    String username = query.split(",")[2];
    String name = query.split(",")[4];
    String pass = query.split(",")[6];
    String age = query.split(",")[8];

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CustomerService customerService = serviceFactory.getCustomerService();

    return customerService.registration(username, name, pass, age);
  }
}
