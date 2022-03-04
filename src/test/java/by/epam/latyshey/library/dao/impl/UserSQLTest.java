package by.epam.latyshey.library.dao.impl;

import static org.junit.Assert.*;

import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.User;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.service.impl.CustomerServiceImpl;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UserSQLTest {

  UserSQL sql;

  @Before
  public void setUp() {
    sql = new UserSQL();
  }

  @Test
  public void signInTestWitchReturnRegisteredUser() {
    IUser user = null;
    try {
      user = sql.signIn("Volterra", "123456");
    } catch (DAOException e) {
      System.out.println(e.getMessage());
    }
    assertEquals(user.getName(), "vic");
  }


  @Test
  public void signInTestWitchReturnResponseExpectedMissingResult() {
    try {
      IUser user = sql.signIn("Volterra1", "123456");
    } catch (DAOException e) {
      assertEquals("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n", e.getMessage());
    }
  }

  @Test
  public void tryToRegisterAndTryToSignInWithNewUsersParamsExpectedSuccessResult() {
    IUser user = new Customer("Victor", "Latyshey", "123456", 18);
    sql.registration(user);
    try {
      IUser checkUser= sql.signIn(user.getUserName(), user.getPass());
      assertEquals(user, checkUser);
    } catch (DAOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void showSQLUser() {
    ArrayList<IUser> users = sql.showSQLUser();
    for (IUser user: users) {
      System.out.println(user.toString());
    }
  }

  @Test
  public void testShowSQLUser() {
  }

  @Test
  public void setUsers() {
    sql.setUsers(sql.showSQLUser());
  }
}