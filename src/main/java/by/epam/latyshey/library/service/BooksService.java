package by.epam.latyshey.library.service;

import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.interfaces.IBook;

public interface BooksService {

  void addBook(String author, String title, Rarity rarity);

  IBook readBook(String author, String title);

  String update(IBook book, IBook updateBook);

  IBook giveOutBook(String author, String title);

  String showAllBook();
}
