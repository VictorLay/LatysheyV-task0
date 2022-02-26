package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.view.menu.MenuName;

public class ErrorMessageReturn implements Command {

  @Override
  public String execute(String query) {
    String response = MenuName.ERROR_VIEW + query.substring(query.indexOf(","));
    return response;
  }
}
