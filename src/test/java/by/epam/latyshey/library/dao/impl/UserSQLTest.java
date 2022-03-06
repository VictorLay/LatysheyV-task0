package by.epam.latyshey.library.dao.impl;

import static by.epam.latyshey.library.bean.Rarity.RARE;
import static org.junit.Assert.*;
import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.data.source.InputSourceCustom;
import by.epam.latyshey.library.data.source.impl.InputSourceCustomImpl;
import by.epam.latyshey.library.data.source.OutputSourceCustom;
import by.epam.latyshey.library.data.source.impl.OutputSourceCustomImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserSQLTest {
  private final InputSourceCustom input = new InputSourceCustomImpl();
  private final OutputSourceCustom output = new OutputSourceCustomImpl();
  private static final String DEFAULT_DATA =
      """
          <User>
          class by.epam.latyshey.library.bean.Customer¶rightUserName¶rightName¶rightPassword¶1¶
          <TakenBooks>
          rightAuthor1§rightTitle1§RARE§1000§2000▼rightAuthor2§rightTitle2§RARE§11000§22000▼rightAuthor3§rightTitle3§ORDINARY§111000§222000▼
          </TakenBooks>¶
          </User>
          """;
  private IUser expectedUser;
  private final Logger testLogger = Logger.getLogger("User test");
  private ArrayList<ITakenBook> expectedTakenBooks;
  private UserSQL sql;
  File file;

  @Before
  public void setUp() {
    file = new File("src/test/java/by/epam/latyshey/library/dao/impl/TestDataSource.txt");
    output.setAllData(file, DEFAULT_DATA);
    sql = new UserSQL(file);
    expectedTakenBooks = new ArrayList<>();
    expectedTakenBooks.add(
        new TakenBook("rightAuthor1", "rightTitle1", RARE, new Date(1000), new Date(2000)));
    expectedTakenBooks.add(
        new TakenBook("rightAuthor2", "rightTitle2", RARE, new Date(11000), new Date(22000)));
    expectedTakenBooks.add(
        new TakenBook("rightAuthor3", "rightTitle3", RARE, new Date(111000), new Date(222000)));
    expectedUser = new Customer("rightUserName", "rightName", "rightPassword", 1,
        expectedTakenBooks);
    testLogger.info("Витя Инфо");
    
  }

  @After
  public void setDown(){
    output.setAllData(file, DEFAULT_DATA);
  }

  @Test
  public void signInTestForUserWhereExpectRightResponse() {
    try {
      IUser user = sql.signIn(expectedUser.getUserName(), expectedUser.getPass());
      assert user != null;
      assertEquals(expectedUser.getUserName(), user.getUserName());
      assertEquals(expectedUser.getName(),     user.getName());
      assertEquals(expectedUser.getPass(),     user.getPass());
      assertEquals(expectedUser.getAge(),      user.getAge());
    } catch (DAOException e) {
      testLogger.info(e.getMessage());
    }
  }

  @Test
  public void signInTestForUserWhereReturnResponseExpectedMissingResult() {
    try {
      sql.signIn("incorrectUserName", "incorrectPassword");
      assert false;
    } catch (DAOException e) {
      assertEquals("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n", e.getMessage());
    }

  }

  @Test
  public void signInTestForUserWhereReturnRightResponse() {
    try {
      IUser user = sql.signIn(expectedUser.getUserName(), expectedUser.getPass());
      assert user != null;
      assertEquals(expectedUser.getUserName(), user.getUserName());
      assertEquals(expectedUser.getName(),     user.getName());
      assertEquals(expectedUser.getPass(),     user.getPass());
      assertEquals(expectedUser.getAge(),      user.getAge());
      assertEquals(expectedTakenBooks, ( (Customer)user ).getTakenBooks());
    } catch (DAOException e) {
      testLogger.info(e.getMessage());
    }
  }

  @Test
  public void tryToRegisterAndTryToSignInWithNewUsersParamsExpectedSuccessResult() {
    IUser user = new Customer("Victor", "Latyshey", "123456", 18);
    sql.registration(user);
    try {
      IUser checkUser = sql.signIn(user.getUserName(), user.getPass());
      assertEquals(user, checkUser);
    } catch (DAOException e) {
      testLogger.info(e.getMessage());
    }
  }

  @Test
  public void showSQLUserTestWhereExpectRightResponse() {
    ArrayList<IUser> users = sql.showSQLUser();
    assertEquals(expectedUser, users.get(0));
  }

  @Test
  public void setUsersTestWhereExpectRightResponse() {
    sql.setUsers(sql.showSQLUser());
    String actual = input.readAllData(file);
    assertEquals(DEFAULT_DATA, actual);
  }

  @Test
  public void updateUser() {
    IUser updatedUser = new Customer("updatedUserName", "updatedName", "updatedPassword", 20);
    sql.updateUser(expectedUser.getUserName(), expectedUser.getPass(), updatedUser);
    IUser signInUser = null;
    try {
      signInUser = sql.signIn(updatedUser.getUserName(), updatedUser.getPass());
      assert signInUser != null;
    } catch (DAOException e) {
      testLogger.info(e.getMessage());
      assert false;
    }
    assertEquals(updatedUser, signInUser);
  }
}