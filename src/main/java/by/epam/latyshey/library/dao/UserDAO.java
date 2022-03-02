package by.epam.latyshey.library.dao;

import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.exception.DAOException;

import java.util.ArrayList;

public interface UserDAO {

  IUser signIn(String userName, String password) throws DAOException;

  void registration(IUser user);

  ArrayList<IUser> showSQLUser();

  void setUsers(ArrayList<IUser> users);

  String updateUser(String userName, String password, IUser newUser);
}
