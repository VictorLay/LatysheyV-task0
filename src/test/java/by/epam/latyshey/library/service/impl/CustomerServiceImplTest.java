package by.epam.latyshey.library.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import by.epam.latyshey.library.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  private String author;
  private String title;
  private String username;
  private String name;
  private String password;
  private int age;

  private CustomerServiceImpl customerService;

  @Mock
  private UserDAO userDAO;
  @Mock
  private BookDAO bookDAO;
  @Mock
  private HistoryDAO historyDAO;

  @BeforeEach
  public void setUp() {
    customerService = new CustomerServiceImpl(userDAO, bookDAO, historyDAO);
    author = "Николай Гоголь";
    title = "Тарас Бульба";
    username = "VicLay";
    name = "Витя";
    password = "123456";
    age = 20;


  }


  @Test
  void tryAddBookToTheCustomerExpectedRightResponse() throws ServiceException, DAOException {

    when(userDAO.signIn(username, password)).thenReturn(
        (IUser) new Customer(username, name, password, age));
    when(bookDAO.giveOutBook(author, title)).thenReturn(
        (IBook) new Book(author, title, Rarity.ORDINARY));
    ICustomer customer = (ICustomer) userDAO.signIn(username, password);
    when(historyDAO.findCustomerHistory(customer)).thenReturn(
        (ICustomerHistory) new CustomerHistory(customer));

    String response = customerService.addBookToCustomer(author, title, username, password);
    String expected = "Автор: 'Николай Гоголь', название: 'Тарас Бульба', редкость: 'ORDINARY'.";

    assertEquals(expected, response.split("\n")[0]);
  }

//  @Test
//  public void tryAddBookToTheCustomerExpectedRightResponse1() throws ServiceException, DAOException {
//
//    when(userDAO.signIn(username, password)).thenReturn(
//        (IUser) new Customer(username, name, password, age));
//    when(bookDAO.giveOutBook(author, title)).thenReturn(
//        (IBook) new Book(author, title, Rarity.ORDINARY));
//    ICustomer customer = (ICustomer) userDAO.signIn(username, password);
//    when(historyDAO.findCustomerHistory(customer)).thenReturn(
//        (ICustomerHistory) new CustomerHistory(customer));
//
//    String response = customerService.addBookToCustomer(author, title, username, password);
//    String expected = "Автор: 'Николай Гоголь', название: 'Тарас Бульба', редкость: 'ORDINARY'.";
//
//    assertEquals(expected, response.split("\n")[0]);
//  }


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