package by.epam.latyshey.library.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.view.menu.MenuName;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

  private String author, title, username, password;
  private int bookIndex;
  private CustomerServiceImpl customerService;

  DAOFactory daoFactory = DAOFactory.getInstance();
;

  @Mock
  private UserDAO userDAO;
  @Mock
  private BookDAO bookDAO;
  @Mock
  private HistoryDAO historyDAO;
//  @Mock
//  private UserDAO userDAO = daoFactory.getUserDAO();
//  @Mock
//  private BookDAO bookDAO = daoFactory.getBookDAO();
//  @Mock
//  private HistoryDAO historyDAO = daoFactory.getHistoryDAO();


  @BeforeEach
  public void setUp() {
//    MockitoAnnotations.initMocks(this);
//    Initialization.init();
    customerService = new CustomerServiceImpl( userDAO,  bookDAO,
         historyDAO);
    author = "Николай Гоголь";
    title = "Тарас Бульба";
    username = "VicLay";
    password = "123456";
    bookIndex = 1;
  }


  @Test
  public void tryAddBookToTheCustomerExpectedRightResponse() throws ServiceException, DAOException {

    when(userDAO.signIn(username,password))
        .thenReturn((IUser) new Customer(username, "Витя", password, 18));
    when(bookDAO.giveOutBook(author, title))
        .thenReturn((IBook) new Book(author, title, Rarity.ORDINARY));

    ICustomer customer = (ICustomer) userDAO.signIn(username,password);
    when(historyDAO.findCustomerHistory(customer))
        .thenReturn((ICustomerHistory) new CustomerHistory(customer));

    String response = customerService.addBookToCustomer(author, title, username, password);
    String expected = "Автор: 'Николай Гоголь', название: 'Тарас Бульба', редкость: 'ORDINARY'.";

    assertEquals(expected, response.split("\n")[0]);
  }
/*
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

  @Test
  public void registrationTest() {
    String userName = "Pollack", name = "Абдула", password = "ComplexPassword@19.-_/*", age = "30";
    String response = customerService.registration(userName, name, password, age);
    String expected =
        MenuName.SUCCESS_REGISTRATION_VIEW + "," + "CUSTOMER,userName=," + userName + ',' + "name=,"
            + name + ',' + "pass=," + password + ',' + "age=," + age + ",";
    assertEquals(expected, response);

    userName = "1Pollack";
    name = "Абдула";
    password = "ComplexPassword@19.-_/*";
    age = "30";

    response = customerService.registration(userName, name, password, age);
    expected = MenuName.SHOW_RESPONSE_VIEW + ","
        + "К сожалению введённые данные пользователя не являются валидными.";
    assertEquals(expected, response);

    userName = "Pollack";
    name = "Абдула";
    password = "mypas";
    age = "30";

    response = customerService.registration(userName, name, password, age);
    expected = MenuName.SHOW_RESPONSE_VIEW + ","
        + "К сожалению введённые данные пользователя не являются валидными.";
    assertEquals(expected, response);

    userName = "Pollack";
    name = "Абдула";
    password = "ComplexPassword@19.-_/*";
    age = "3d0";

    response = customerService.registration(userName, name, password, age);
    expected = MenuName.SHOW_RESPONSE_VIEW + ","
        + "К сожалению введённые данные пользователя не являются валидными.";
    assertEquals(expected, response);

    userName = "Pollack";
    name = "Абду@ла";
    password = "ComplexPassword@19.-_/*";
    age = "30";

    response = customerService.registration(userName, name, password, age);
    expected = MenuName.SHOW_RESPONSE_VIEW + ","
        + "К сожалению введённые данные пользователя не являются валидными.";
    assertEquals(expected, response);

    userName = "Pollack";
    name = "Абдула";
    password = "Comp exPassword@19.-_/*";
    age = "3d0";

    response = customerService.registration(userName, name, password, age);
    expected = MenuName.SHOW_RESPONSE_VIEW + ","
        + "К сожалению введённые данные пользователя не являются валидными.";
    assertEquals(expected, response);
  }
  */

}