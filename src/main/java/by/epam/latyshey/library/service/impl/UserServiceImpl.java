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
    StringBuilder display = new StringBuilder();
    ArrayList<IUser> users = daoUser.showSQLUser();
    for (IUser user : users) {
      String role;
        if (user instanceof Customer) {
            role = "Посетитель";
        } else {
            role = "Работник";
        }
      display.append(role).append(": ").append(user.getUserName()).append(", имя: ")
          .append(user.getName()).append(", пароль: ").append(user.getPass()).append(", возраст: ")
          .append(user.getAge()).append(".\n");
    }
    return display.toString();
  }

  @Override
  public String signIn(String username, String password) throws ServiceException {
    try {

      return daoUser.signIn(username, password).toString();

    } catch (DAOException exception) {
      throw new ServiceException(exception);
    }
  }


}
