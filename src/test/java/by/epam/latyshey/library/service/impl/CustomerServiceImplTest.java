package by.epam.latyshey.library.service.impl;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import by.epam.latyshey.library.Initialization;
import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.CustomerHistory;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.dao.impl.UserSQL;
import by.epam.latyshey.library.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceImplTest {

  private String author, title, username, password;
  private int bookIndex;
  private CustomerServiceImpl customerService;
  DAOFactory daoFactory = DAOFactory.getInstance();
  //@Mock

  private UserDAO userDAO = daoFactory.getUserDAO();

  private BookDAO bookDAO = daoFactory.getBookDAO();

  private HistoryDAO historyDAO = daoFactory.getHistoryDAO();


  @Before
  public void setUp() {
    //MockitoAnnotations.initMocks(this);
    Initialization.init();
    customerService = new CustomerServiceImpl();
    author = "Николай Гоголь";
    title = "Тарас Бульба";
    username = "VicLay";
    password = "123456";
    bookIndex = 1;
  }


  @Test
  public void tryAddBookToTheCustomerExpectedRightResponse() throws ServiceException {

    /*given(userDAO.signIn(username,password))
        .willReturn((IUser) new Customer(username, "Витя", password, 18));
    given(bookDAO.giveOutBook(author, title))
        .willReturn((IBook) new Book(author, title, Rarity.ORDINARY));

    ICustomer customer = (ICustomer) userDAO.signIn(username,password);
    given(historyDAO.findCustomerHistory(customer))
        .willReturn((ICustomerHistory) new CustomerHistory(customer));*/

    String response = customerService.addBookToCustomer(author, title, username, password);
    String expected =
        "Автор: 'Николай Гоголь', название: 'Тарас Бульба', редкость: 'ORDINARY'.";

    assertEquals(expected, response.split("\n")[0]);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void tryAddNonExistingBookToTheCustomerExpectedServiceException() throws ServiceException {
    thrown.expect(ServiceException.class);
    thrown.expectMessage("\n Книги этого автора или с таким названием найдено не было!\n");
    customerService.addBookToCustomer(author, "NON-EXISTING TITLE", username, password);
    thrown = ExpectedException.none();
  }

  @Test
  public void tryAddBookToTheNonExistingCustomerExpectedServiceException() throws ServiceException {
    thrown.expect(ServiceException.class);
    thrown.expectMessage("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n");
    customerService.addBookToCustomer(author, title, "NON-EXISTING USERNAME", password);
    thrown = ExpectedException.none();
  }
}