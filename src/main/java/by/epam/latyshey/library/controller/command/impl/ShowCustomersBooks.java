package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.controller.command.ErrorName;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class ShowCustomersBooks implements Command {

  @Override
  public String execute(String query) {
    String username = query.split(",")[2];
    String pass = query.split(",")[4];

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CustomerService customerService = serviceFactory.getCustomerService();

    String response = null;
    try {
      response = customerService.showCustomerBooks(username, pass);
    } catch (ServiceException exception) {
      response = ErrorName.SIGN_IN_ERROR + "\n" + exception.getMessage();
    }
    return response;
  }
}
