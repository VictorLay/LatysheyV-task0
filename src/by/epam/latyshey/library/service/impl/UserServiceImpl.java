package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.UserService;
import by.epam.latyshey.library.service.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  UserDAO daoUser = daoFactory.getUserDAO();

  @Override
  public String showSQLUser() {
    String display = "";
    ArrayList<IUser> users = daoUser.showSQLUser();
    for (IUser user : users) {
      String role;
        if (user instanceof Customer) {
            role = "Посетитель";
        } else {
            role = "Работник";
        }
      display += role + ": " + user.getUserName() + ", имя: " + user.getName() +
          ", пароль: " + user.getPass() + ", возраст: " + user.getAge() + ".\n";
    }
    return display;
  }

  @Override
  public String signIn(String username, String password) throws ServiceException {
    try {

      String response = daoUser.signIn(username, password).toString();
      return response;

    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
  }


}
