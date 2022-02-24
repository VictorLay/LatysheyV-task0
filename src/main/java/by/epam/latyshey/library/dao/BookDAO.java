package by.epam.latyshey.library.dao;

import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.dao.exception.DAOException;

import java.util.ArrayList;

public interface BookDAO {

  void addBook(IBook book);

  IBook readBook(String author, String title);

  IBook update(IBook book, IBook updateBook);

  IBook giveOutBook(String author, String title) throws DAOException;

  ArrayList<IBook> showAllBooks();

  void setBooks(ArrayList<IBook> books);
}
