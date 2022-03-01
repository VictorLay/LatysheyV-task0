package by.epam.latyshey.library.dao.impl;

import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.exception.DAOException;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookSQL implements BookDAO {
  final static Logger bookDAOLogger = LogManager.getLogger(BookSQL.class);
  private ArrayList<IBook> bookSQL;

  @Override
  public void setBooks(ArrayList<IBook> books) {
    bookSQL = books;
  }

  public BookSQL() {
    bookSQL = new ArrayList<>();
  }

  @Override
  public ArrayList<IBook> showAllBooks() {
    return this.bookSQL;
  }

  @Override
  public void addBook(IBook book) {
    this.bookSQL.add(book);
  }

  @Override
  public IBook readBook(String author, String title) {
    IBook book = findBook(this.bookSQL, author, title);
    return book;
  }

  @Override
  public IBook update(IBook book, IBook updateBook) {
    return null;
  }

  @Override
  public IBook giveOutBook(String author, String title) throws DAOException {
    for (int i = bookSQL.size() - 1; i >= 0; i--) {
      if (bookSQL.get(i).getAuthor().equals(author) &&
          bookSQL.get(i).getTitle().equals(title))
      {
        IBook book = bookSQL.get(i);
        bookSQL.remove(i);
        bookDAOLogger.debug("Из бд книг удалена: " + book);
        return book;
      }
    }
    throw new DAOException("\n Книги этого автора или с таким названием найдено не было!\n");
  }

  private IBook findBook(ArrayList<IBook> bookSQL, String author, String title) {
    for (IBook b : bookSQL) {
      if (b.getAuthor().equals(author) && b.getTitle().equals(title)) {
        return b;
      }
    }
    return null;
  }


}
