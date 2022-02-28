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

public class CustomerServiceImpl implements CustomerService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  UserDAO userDAO = daoFactory.getUserDAO();
  BookDAO bookDAO = daoFactory.getBookDAO();
  HistoryDAO historyDAO = daoFactory.getHistoryDAO();

  @Override
  public String addBookToCustomer(String author, String title, String username, String pass) throws ServiceException {

    ICustomer customer;
    IBook book = null;
    try {
      customer = (ICustomer) userDAO.signIn(username, pass);
      book = bookDAO.giveOutBook(author, title);
    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
    ITakenBook takenBook;
    String response;

    takenBook = new TakenBook(book, new Date());
    ICustomerHistory history = historyDAO.findCustomerHistory(customer);

    ArrayList<ITakenBook> historyBooks = history.getTakenBooks();
    historyBooks.add(takenBook);
    history.setTakenBooks(historyBooks);

    //history.addBookInHistory(takenBook);
    historyDAO.updateCustomerHistory(history, customer);

    ArrayList<ITakenBook> customerBooks = customer.getTakenBooks();
    customerBooks.add(takenBook);
    customer.setTakenBooks(customerBooks);

    //todo слабость кода (данные меняются по ссылке, а не передаются в дао)
    response = takenBook.toString();
    return response;
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
    IBook book = customerBooks.remove(bookIndex - 1);
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
