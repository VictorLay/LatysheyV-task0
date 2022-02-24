package by.epam.latyshey.library.service;

import by.epam.latyshey.library.service.exception.ServiceException;

public interface UserService {

  String signIn(String username, String password) throws ServiceException;

  String showSQLUser();
}
