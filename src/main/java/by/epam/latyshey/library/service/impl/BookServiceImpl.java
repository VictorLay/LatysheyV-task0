package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.BooksService;

public class BookServiceImpl implements BooksService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  BookDAO daoBook = daoFactory.getBookDAO();

  @Override
  public String showAllBook() {
    StringBuilder response = new StringBuilder();
    for (IBook book : daoBook.showAllBooks()) {
      response.append(book.toString()).append("\n");
    }
    return response.toString();
  }

  @Override
  public void addBook(String author, String title, Rarity rarity) {
    this.daoBook.addBook(new Book(author, title, rarity));
  }

  @Override
  public IBook readBook(String author, String title) {
    return daoBook.readBook(author, title);
  }

  @Override
  public String update(IBook book, IBook updateBook) {
    return null;
  }

  @Override
  public IBook deleteBook(String author, String title) {
    return null;
  }
}
