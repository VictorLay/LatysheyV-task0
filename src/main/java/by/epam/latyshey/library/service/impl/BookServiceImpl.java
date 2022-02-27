package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.BooksService;

import java.util.ArrayList;

public class BookServiceImpl implements BooksService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  BookDAO daoBook = daoFactory.getBookDAO();

  @Override
  public String showAllBook() {
    String response = "";
    ArrayList<IBook> books = daoBook.showAllBooks();

    for (IBook b :
        books) {
      response += b.toString() + "\n";
    }
    return response;
  }

  @Override
  public void addBook(String author, String title, Rarity rarity) {
    this.daoBook.addBook(new Book(author, title, rarity));
  }

  @Override
  public IBook readBook(String author, String title) {
    IBook book = daoBook.readBook(author, title);
    return book;
  }

  @Override
  public String update(IBook book, IBook updateBook) {
    return null;
  }

  @Override
  public IBook giveOutBook(String author, String title) {
    return null;
  }
}
