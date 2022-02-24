package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.BooksService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class ShowAllBook implements Command {

  @Override
  public String execute(String query) {
    String response;
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    BooksService booksService = serviceFactory.getBooksService();

    response = booksService.showAllBook();
    return response;
  }
}
