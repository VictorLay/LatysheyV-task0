package by.epam.latyshey.library.dao.impl;


import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.bean.User;
import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.data.source.InputSourceCustom;
import by.epam.latyshey.library.data.source.OutputSourceCustom;
import by.epam.latyshey.library.session.SessionParameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSQL implements UserDAO {
  static final String DEL = "¶";
  private final File file1 = new File("src/main/java/by/epam/latyshey/library/data/source/UserSource.txt");
  private final InputSourceCustom inputSourceCustom = new InputSourceCustom(DEL);
  private final OutputSourceCustom outputSourceCustom = new OutputSourceCustom(DEL);
  private static final Logger logger = LogManager.getLogger(UserSQL.class);


  @Override
  public User signIn(String userName, String password) throws DAOException {
    User user= findUser(userName, password);
    SessionParameters.setLoggedInUser(user);
    return user;
  }



  @Override
  public void registration(IUser user) {
  outputSourceCustom.addUserToFile(file1, user);
  }

  @Override
  public ArrayList<IUser> showSQLUser() {

    String data = inputSourceCustom.getDataFromFile(file1);
    String[] objects = inputSourceCustom.getObjectsArray(data);
    ArrayList<IUser> users = new ArrayList<>();

    for (String object : objects) {
      try {
        users.add(createUser(inputSourceCustom.getDataArrayOfObject(object), Customer.class, Employee.class));
      } catch (Exception e) {
        logger.debug(e.getMessage());
        logger.info("Объект не может быть извлечён!");
      }
    }
    return users;
  }

  @Override
  public void setUsers(ArrayList<IUser> users) {
    outputSourceCustom.setUsers(file1, users);
  }

  @Override
  public String updateUser(String userName, String password, IUser newUser) {
    try {
      String newRecord = updateUsersData(userName, password, newUser);
      outputSourceCustom.setUsers(file1, newRecord);
    } catch (DAOException e) {
      e.printStackTrace();
      logger.error("The attempt of update users data was failed.");
      logger.debug(e.getMessage());
    }
    return null;
  }

  private static <C, E> User createUser(String[] arrayOfDataAboutObject, Class<C> customer, Class<E> employee) throws DAOException {

    String userName = arrayOfDataAboutObject[1];
    String name = arrayOfDataAboutObject[2];
    String pass = arrayOfDataAboutObject[3];
    int age = Integer.parseInt(arrayOfDataAboutObject[4]);

    if (customer.toString().equals(arrayOfDataAboutObject[0])) { //todo поменять местами сраниваемые строки / рефактор имен
      try {
        ArrayList<ITakenBook> takenBooks = getTakenBooksArrayList(arrayOfDataAboutObject[5]);
        return new Customer(userName,  name,  pass,  age, takenBooks);
      }catch (ArrayIndexOutOfBoundsException e){
        return  new Customer(userName,  name,  pass,  age);
      }

    }

    if (employee.toString().equals(arrayOfDataAboutObject[0])) {
      return new Employee(userName,  name,  pass,  age);
    }

    logger.debug("Класс извлекаемого объекта не совпадает с ожидаемым.");
    throw new DAOException("Невалидные данные. Проверьте целостность файла данных!");
  }


  private static ArrayList<ITakenBook> getTakenBooksArrayList(String customerBooks) {
    ArrayList<ITakenBook> takenBooks= new ArrayList<>();
    String[] customerBooksArray = customerBooks.split("▼");
    for (String customerBook : customerBooksArray) {
      String[] customerBookData = customerBook.split("§");
      String author = customerBookData[0];
      String title = customerBookData[1];
      Rarity rarity = Rarity.valueOf(customerBookData[2]);
      Date takeData = new Date(Long.parseLong(customerBookData[3]));
      Date returnData = new Date(Long.parseLong(customerBookData[4]));
      ITakenBook takenBook = new TakenBook(author, title, rarity, takeData, returnData);
      takenBooks.add(takenBook);
    }
    return takenBooks;
  }

  private User findUser(String userName, String password) throws DAOException {
    String data = inputSourceCustom.getDataFromFile(file1);
    String[] users = inputSourceCustom.getObjectsArray(data);
    for (String user: users) {
      String[] userDataArray = user.split(DEL);
      if (userName.equals(userDataArray[1])
          && password.equals(userDataArray[3])){
        return createUser(userDataArray, Customer.class, Employee.class);
      }
    }
    throw new DAOException("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n");
  }
  private String updateUsersData(String userName, String password, IUser user) throws DAOException {
    String data = inputSourceCustom.getDataFromFile(file1);
    String[] users = inputSourceCustom.getObjectsArray(data);

    for (int i = 0; i < users.length; i++) {
      String[] userDataArray = users[i].split(DEL);
      if (userName.equals(userDataArray[1])
          && password.equals(userDataArray[3])){
         String oldUser = userDataArray[0]+DEL
             +userDataArray[1]+DEL
             +userDataArray[2]+DEL
             +userDataArray[3]+DEL
             +userDataArray[4]+DEL;
         if (userDataArray.length == 6){
           oldUser += userDataArray[5];
         }

// todo possible ArrayIndexOutOfBoundsException
         String newUser = userDataArray[0]+DEL
             +user.getUserName()+DEL
             +user.getName()+DEL
             +user.getPass()+DEL
             +user.getAge()+DEL
             +outputSourceCustom.convertToStringRecord(((ICustomer)user).getTakenBooks());
        String updateData = data.replace(oldUser, newUser);
         return updateData;
      }
    }
    return null;
  }
}
