package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.*;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.CustomerService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.validation.Validation;
import by.epam.latyshey.library.view.menu.MenuName;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {

  private final Logger serviceLogger = LogManager.getLogger(CustomerServiceImpl.class);
  /**
   * {@link CustomerServiceImpl#historyDAO} the object which allows receiving access to librarian
   * history ({@link CustomerHistory}) obout customers.
   */
  private final BookDAO bookDAO;
  /**
   * {@link CustomerServiceImpl#bookDAO} the object which allows receiving access to books ({@link
   * Book}) that are placed in the library.
   */
  private final HistoryDAO historyDAO;
  /**
   * {@link CustomerServiceImpl#userDAO} the object which allows receiving access to customer
   * ({@link Customer}) and his taken books ({@link TakenBook})
   */
  private final UserDAO userDAO;

  /**
   * The constructor contained initialization Data Access Objects variable {@link
   * CustomerServiceImpl#userDAO}, {@link CustomerServiceImpl#bookDAO}, and {@link
   * CustomerServiceImpl#historyDAO}.
   */
  public CustomerServiceImpl() {
    DAOFactory daoFactory = DAOFactory.getInstance();
    userDAO = daoFactory.getUserDAO();
    bookDAO = daoFactory.getBookDAO();
    historyDAO = daoFactory.getHistoryDAO();
  }

  /**
   * The constructor of {@link CustomerServiceImpl} designed for using with {@link
   * org.mockito.Mockito} tests.
   *
   * @param userDAO    data access object class which provides access to user's data
   * @param bookDAO    data access object class which provides access to book data
   * @param historyDAO data access object class which provides access to history data
   */
  public CustomerServiceImpl(UserDAO userDAO, BookDAO bookDAO, HistoryDAO historyDAO) {
    this.userDAO = userDAO;
    this.bookDAO = bookDAO;
    this.historyDAO = historyDAO;
  }

  /**
   * The method {@link CustomerServiceImpl#addBookToCustomer} is used for searching in the {@link
   * CustomerServiceImpl#userDAO} required {@link Customer} username and pass parameters. Title and
   * author parameters need for searching in the {@link CustomerServiceImpl#bookDAO} required {@link
   * Book}. Found {@link Book} add to {@link Customer} and with helping {@link
   * CustomerServiceImpl#userDAO} happens update of Data Source. Using {@link
   * CustomerServiceImpl#historyDAO} with {@link Customer} happen search {@link CustomerHistory} and
   * following update Data Source.
   *
   * @param author   the full name of author.
   * @param title    the title of the book which needs to find.
   * @param username the nickname of the user who tries to find book.
   * @param password the user's password.
   * @return a string of information about the book which was taken.
   * @throws ServiceException
   */
  @Override
  public String addBookToCustomer(String author, String title, String username, String password)
      throws ServiceException {
    ICustomer customer;
    IBook book;
    ITakenBook takenBook;
    try {
      customer = (ICustomer) userDAO.signIn(username, password);
      book = bookDAO.giveOutBook(author, title);
      takenBook = new TakenBook(book, new Date());
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }

    customer.getTakenBooks().add(takenBook);
    userDAO.updateUser(username, password, customer);

    ICustomerHistory history = historyDAO.findCustomerHistory(customer);
    history.getTakenBooks().add(takenBook);
    historyDAO.updateCustomerHistory(history);

    return takenBook.toString();
  }

  /**
   * The method {@link CustomerServiceImpl#showCustomerBooks} is used for demonstrating a list of
   * the books which the {@link Customer} has in the order of date of taking. The first part of the
   * response before the delimiter "," has the highest list index of {@link Customer}'s books.
   *
   * @param username the nickname of the user who tries to see his taken books.
   * @param password the user's password.
   * @return string response on the format: "[highestListIndex] [,] [responseString]".
   * @throws ServiceException
   */
  @Override
  public String showCustomerBooks(String username, String password) throws ServiceException {
    ICustomer customer;
    try {
      customer = (Customer) userDAO.signIn(username, password);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }

    StringBuilder response = new StringBuilder("У вас хранятся следующие книги:\n");
    int i = 0;
    for (ITakenBook book : customer.getTakenBooks()) {
      response.append("[").append(++i).append("]").append(book).append("\n");
    }

    return i + "," + response;
  }

  /**
   * The method {@link CustomerServiceImpl#returnBook} is used for removing {@link Book} from {@link
   * Customer}, add this {@link Book} to librarian Data Source and do appropriate record in the
   * {@link CustomerHistory} with the date of the returning book.
   *
   * @param username        the nickname of the user who tries to return the book.
   * @param password        the user's password
   * @param returnedBookNum order number of the returning book in customer {@link TakenBook} list.
   * @return string with information about the returned book.
   * @throws ServiceException
   */
  @Override
  public String returnBook(String username, String password, int returnedBookNum)
      throws ServiceException {
    ICustomer customer;
    try {
      customer = (Customer) userDAO.signIn(username, password);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
    ITakenBook takenBook = customer.getTakenBooks().remove(returnedBookNum - 1);
    userDAO.updateUser(username, password, customer);
    bookDAO.addBook(new Book(takenBook.getAuthor(), takenBook.getTitle(), takenBook.getRarity()));

    ArrayList<ITakenBook> history = historyDAO.findCustomerHistory(customer).getTakenBooks();
    for (int i = history.size() - 1; i >= 0; i--) {
      if (history.get(i).equals(takenBook)) {
        takenBook.setReturnDate(new Date());
        history.get(i).setReturnDate(new Date());
        break;
      }
    }

    historyDAO.updateCustomerHistory(new CustomerHistory(history, customer));
    return takenBook.toString();
  }

  /**
   * The method create {@link Customer} and {@link CustomerHistory} objects. These objects write to
   * Data Source using {@link CustomerServiceImpl#historyDAO} and {@link
   * CustomerServiceImpl#userDAO}.
   *
   * @param username the user's nickname which will be required for authorization.
   * @param name     the user's name.
   * @param password the user's password which will be required for authorization.
   * @param age      the user's age.
   * @return string request to call menu with data about new customer or message about a mistake.
   */
  @Override
  public String registration(String username, String name, String password, String age) {

    if (isUserRegistrationDataCorrect(username, name, password, age)) {
      ICustomer customer = new Customer(username, name, password, Integer.parseInt(age));
      ICustomerHistory history = new CustomerHistory(customer);

      userDAO.registration(customer);
      historyDAO.createHistory(history);

      return MenuName.SUCCESS_REGISTRATION_VIEW + "," + customer;
    } else {
      return MenuName.SHOW_RESPONSE_VIEW + ","
          + "К сожалению введённые данные пользователя не являются валидными.";
    }
  }

  /**
   * Validation of the {@link Customer}'s fields.
   *
   * @return TRUE if all fields filled in correctly and FALSE in another way.
   */
  private boolean isUserRegistrationDataCorrect(String username, String name, String pass,
      String age) {
    return Validation.usersUserNameValidation(username) && Validation.usersNameValidation(name)
        && Validation.usersPasswordValidation(pass) && Validation.usersAgeValidation(age);
  }
}
