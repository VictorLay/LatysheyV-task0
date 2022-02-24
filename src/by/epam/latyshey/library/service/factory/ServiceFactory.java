package by.epam.latyshey.library.service.factory;

import by.epam.latyshey.library.service.*;
import by.epam.latyshey.library.service.impl.*;

public final class ServiceFactory {

  private static final ServiceFactory instance = new ServiceFactory();

  private ServiceFactory() {
  }

  private final UserService userService = new UserServiceImpl();
  private final BooksService booksService = new BookServiceImpl();
  private final CustomerService customerService = new CustomerServiceImpl();
  private final EmployeeService employeeService = new EmployeeServiceImpl();
  private final SerializeService serializeService = new SerializeServiceImpl();


  public static ServiceFactory getInstance() {
    return instance;
  }

  public BooksService getBooksService() {
    return this.booksService;
  }

  public UserService getUserService() {
    return this.userService;
  }

  public CustomerService getCustomerService() {
    return this.customerService;
  }

  public EmployeeService getEmployeeService() {
    return this.employeeService;
  }

  public SerializeService getSerializeService() {
    return this.serializeService;
  }
}
