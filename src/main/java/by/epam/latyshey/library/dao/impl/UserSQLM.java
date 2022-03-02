package by.epam.latyshey.library.dao.impl;

import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;

import by.epam.latyshey.library.session.SessionParameters;
import java.util.ArrayList;

public class UserSQLM implements UserDAO {

  private ArrayList<IUser> userSQL;


//  public UserSQL() {
//    userSQL = new ArrayList<>();
//  }

  @Override
  public void setUsers(ArrayList<IUser> userSQL) {
    this.userSQL = userSQL;
  }

  @Override
  public ArrayList<IUser> showSQLUser() {
    return userSQL;
  }

  @Override
  public IUser signIn(String userName, String password) throws DAOException {
    IUser user = this.findUser(this.userSQL, userName, password);
    if (user == null) {
      throw new DAOException("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n");
    }
    SessionParameters.setLoggedInUser(user);
    return user;
  }

  @Override
  public void registration(IUser user) {
    userSQL.add(user);
  }

  private IUser findUser(ArrayList<IUser> sqlUser, String userName, String password) {
    for (IUser n : sqlUser)
    {
      if (n.getUserName().equals(userName) && n.getPass().equals(password))
      {
        return n;
      }
    }
    return null;
  }


}
