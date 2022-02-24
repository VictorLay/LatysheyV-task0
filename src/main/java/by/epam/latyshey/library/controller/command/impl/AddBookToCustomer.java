package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.controller.command.ErrorName;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class AddBookToCustomer implements Command {

  @Override
  public String execute(String query) {
    String author = query.split(",")[2],
        title = query.split(",")[4],
        username = query.split(",")[6],
        pass = query.split(",")[8];
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CustomerService customerService = serviceFactory.getCustomerService();

    String response;
    try {
      response = customerService.addBookToCustomer(author, title, username, pass);
    } catch (ServiceException exception) {
      response = ErrorName.SIGN_IN_ERROR + "\n" + exception.getMessage();
    }

    return response;
  }
}
