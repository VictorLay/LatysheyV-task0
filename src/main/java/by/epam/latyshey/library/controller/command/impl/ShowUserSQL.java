package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.UserService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class ShowUserSQL implements Command {

  @Override
  public String execute(String query) {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();

    String display;
    display = userService.showSQLUser();
    return display;
  }
}
