package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.BooksService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class ReadBook implements Command {

  @Override
  public String execute(String query) {
    String author = query.split(",")[2],
        title = query.split(",")[4];
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    BooksService booksService = serviceFactory.getBooksService();

    booksService.readBook(author, title);

    return "Book,author=," + author + ",title=," + title;
  }
}
