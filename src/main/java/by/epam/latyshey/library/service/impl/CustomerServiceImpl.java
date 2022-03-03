package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.*;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;

import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.validation.Validation;
import by.epam.latyshey.library.view.menu.MenuName;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {

  private Logger serviceLogger = LogManager.getLogger(CustomerServiceImpl.class);

  private final DAOFactory daoFactory = DAOFactory.getInstance();
  /**
   * {@link CustomerServiceImpl#historyDAO} the object which allows receiving access to librarian history ({@link CustomerHistory}) obout customers.
   */
  private final BookDAO bookDAO;
  /**
   * {@link CustomerServiceImpl#bookDAO} the object which allows receiving access to books ({@link Book}) that are placed in the library.
   */
  private final HistoryDAO historyDAO;
  /**
   * {@link CustomerServiceImpl#userDAO} the object which allows receiving access to customer ({@link Customer}) and his taken books ({@link TakenBook})
   */
  private final UserDAO userDAO;

  public CustomerServiceImpl() {
    userDAO = daoFactory.getUserDAO();
    bookDAO = daoFactory.getBookDAO();
    historyDAO = daoFactory.getHistoryDAO();
  }

  public CustomerServiceImpl(UserDAO userDAO, BookDAO bookDAO,
      HistoryDAO historyDAO) {
    this.userDAO = userDAO;
    this.bookDAO = bookDAO;
    this.historyDAO = historyDAO;
  }

  /**
   *  The method {@link CustomerServiceImpl#addBookToCustomer} use for searching in the {@link CustomerServiceImpl#userDAO} required {@link Customer}
   *  username and pass parameters. Title and author parameters need for searching in the {@link CustomerServiceImpl#bookDAO} required {@link Book}.
   *  Found {@link Book}
   *
   * @param author
   * @param title
   * @param username
   * @param pass
   * @return
   * @throws ServiceException
   */
  @Override
  public String addBookToCustomer(String author, String title, String username, String pass)
      throws ServiceException {

    ICustomer customer;
    IBook book = null;
    try {
      customer = (ICustomer) userDAO.signIn(username, pass);
      book = bookDAO.giveOutBook(author, title);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }

    // Updating librarian history of customer
    ITakenBook takenBook = new TakenBook(book, new Date());
    ICustomerHistory history = historyDAO.findCustomerHistory(customer);
    history.getTakenBooks().add(takenBook);
    serviceLogger.debug(history.getTakenBooks().toString());
    historyDAO.updateCustomerHistory(history, customer);

    //updating user
    customer.getTakenBooks().add(takenBook);
    userDAO.updateUser(username, pass, customer);

//    ArrayList<IUser> users = userDAO.showSQLUser();
//    for (int i = 0; i < users.size(); i++) {
//      if (users.get(i).equals(customer)) {
//        users.set(i, customer);
//      }
//    }
//    userDAO.setUsers(users);
    return takenBook.toString();
  }


  @Override
  public String showCustomerBooks(String username, String pass) throws ServiceException {
    ICustomer customer;
    try {
      customer = (Customer) userDAO.signIn(username, pass);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
    ArrayList<ITakenBook> customerBooks = customer.getTakenBooks();
    String response = "У вас хранятся следующие книги:\n";
    int i = 0;
    for (ITakenBook book : customerBooks) {
      response += "[" + (++i) + "]" + book + "\n";

    }
    return i + "," + response;
  }

  @Override
  public String returnBook(String username, String pass, int bookIndex) throws ServiceException {

    ICustomer customer;
    try {
      customer = (Customer) userDAO.signIn(username, pass);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
    ArrayList<ITakenBook> customerBooks = customer.getTakenBooks();
    IBook book = customerBooks.remove(
        bookIndex - 1); //todo удаление происходит по ссылке, необходимо обновлять базу
    userDAO.updateUser(username, pass, customer);
    bookDAO.addBook(new Book(book.getAuthor(), book.getTitle(), book.getRarity()));

    ICustomerHistory customerHistory = historyDAO.findCustomerHistory(customer);
    ArrayList<ITakenBook> history = customerHistory.getTakenBooks();
    for (int i = history.size() - 1; i >= 0; i--) {
      if (history.get(i).equals(book)) {
        history.get(i).setReturnDate(new Date());
        break;
      }
    }
    return book.toString();
  }

  @Override
  public String registration(String username, String name, String pass, String age) {
    boolean isRegistrationDataCorrect =
        Validation.usersUserNameValidation(username) &&
            Validation.usersNameValidation(name) &&
            Validation.usersPasswordValidation(pass) &&
            Validation.usersAgeValidation(age);

    if (isRegistrationDataCorrect) {
      ICustomer customer = new Customer(username, name, pass, Integer.parseInt(age));
      userDAO.registration((Customer) customer);
      ICustomerHistory history = new CustomerHistory(customer);
      historyDAO.createHistory(history);
      return MenuName.SUCCESS_REGISTRATION_VIEW + "," + customer.toString();
    } else {
      return MenuName.SHOW_RESPONSE_VIEW + ","
          + "К сожалению введённые данные пользователя не являются валидными.";
    }

  }
}
