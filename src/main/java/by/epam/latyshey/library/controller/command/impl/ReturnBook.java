package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.controller.command.ErrorName;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;
import by.epam.latyshey.library.view.menu.MenuName;

public class ReturnBook implements Command {

  @Override
  public String execute(String query) {
    String username = query.split(",")[2];
    String pass = query.split(",")[4];
    String index = query.split(",")[6];

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    CustomerService customerService = serviceFactory.getCustomerService();
    String response = null;
    try {
      response = MenuName.SUCCESS_RETURN_BOOK_VIEW + "," + customerService.returnBook(username, pass, Integer.parseInt(index));
    } catch (ServiceException e) {
      response = ErrorName.SIGN_IN_ERROR;
    }
    return response;
  }
}
