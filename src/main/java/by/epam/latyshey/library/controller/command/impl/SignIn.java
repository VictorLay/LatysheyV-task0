package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.controller.command.ErrorName;
import by.epam.latyshey.library.service.UserService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;
import by.epam.latyshey.library.view.menu.MenuName;


public class SignIn implements Command {

  @Override
  public String execute(String request) {
    String username = request.split(",")[2],
        pass = request.split(",")[4];

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    String response;
    try {
      response = userService.signIn(username, pass);
    } catch (ServiceException exception) {

      response = MenuName.ERROR_VIEW + ",\n" + exception.getMessage();
    }

    return response;
  }
}
