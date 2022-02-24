package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.BooksService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class AddBook implements Command {

  @Override
  public String execute(String query) {
    String author = query.split(",")[2],
        title = query.split(",")[4];
    Rarity rarity = Rarity.valueOf(query.split(",")[6]);
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    BooksService booksService = serviceFactory.getBooksService();
    booksService.addBook(author, title, rarity);
    return null;
  }
}
