package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;
import by.epam.latyshey.library.view.menu.MenuName;

public class AddBookToCustomer implements Command {

  @Override
  public String execute(String query) {
    String author = query.split(",")[2];
    String title = query.split(",")[4];
    String username = query.split(",")[6];
    String pass = query.split(",")[8];
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CustomerService customerService = serviceFactory.getCustomerService();

    String response;
    try {
      response = MenuName.SUCCESS_ADDED_BOOK_VIEW + "," + customerService.addBookToCustomer(author, title, username, pass);
    } catch (ServiceException exception) {
      response = MenuName.ERROR_VIEW + ",\n" + exception.getMessage();
    }

    return response;
  }
}
