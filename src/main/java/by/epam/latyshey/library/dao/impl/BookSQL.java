package by.epam.latyshey.library.dao.impl;

import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.exception.DAOException;

import by.epam.latyshey.library.data.source.DataSourceCollection;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookSQL implements BookDAO {

  static final Logger bookDAOLogger = LogManager.getLogger(BookSQL.class);
  private ArrayList<IBook> booksInLibrary;

  public BookSQL() {
    DataSourceCollection dataSource = DataSourceCollection.getInstance();
    booksInLibrary = dataSource.getBooks();
  }

  @Override
  public void setBooks(ArrayList<IBook> books) {
    booksInLibrary = books;
  }



  @Override
  public ArrayList<IBook> showAllBooks() {
    return this.booksInLibrary;
  }

  @Override
  public void addBook(IBook book) {
    this.booksInLibrary.add(book);
  }

  @Override
  public IBook readBook(String author, String title) {
    return findBook(this.booksInLibrary, author, title);
  }

  @Override
  public IBook update(IBook book, IBook updateBook) {
    return null;
  }

  @Override
  public IBook giveOutBook(String author, String title) throws DAOException {
    for (int i = booksInLibrary.size() - 1; i >= 0; i--) {
      if (booksInLibrary.get(i).getAuthor().equals(author) &&
          booksInLibrary.get(i).getTitle().equals(title))
      {
        IBook book = booksInLibrary.get(i);
        booksInLibrary.remove(i);
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
