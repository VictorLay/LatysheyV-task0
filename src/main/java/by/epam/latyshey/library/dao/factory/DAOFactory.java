package by.epam.latyshey.library.dao.factory;

import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.impl.BookSQL;
import by.epam.latyshey.library.dao.impl.HistorySQL;
import by.epam.latyshey.library.dao.impl.UserSQL;

public final class DAOFactory {

  private static final DAOFactory instance = new DAOFactory();

  private DAOFactory() {
  }

  private static final UserDAO sqlUserImpl = new UserSQL();
  private static final BookDAO sqlBookImpl = new BookSQL();
  private static final HistoryDAO historyDAO = new HistorySQL();

  public static DAOFactory getInstance() {
    return instance;
  }

  public BookDAO getBookDAO() {
    return sqlBookImpl;
  }

  public UserDAO getUserDAO() {
    return sqlUserImpl;
  }

  public HistoryDAO getHistoryDAO() {
    return historyDAO;
  }


}
