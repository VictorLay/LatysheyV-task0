package by.epam.latyshey.library.service;

import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.service.exception.ServiceException;

public interface CustomerService {

  String addBookToCustomer(String author, String title, String username, String pass)
      throws ServiceException;

  String registration(String username, String name, String pass, String age);

  String showCustomerBooks(String username, String pass) throws ServiceException;

  String returnBook(String username, String pass, int bookIndex) throws ServiceException;
}
