package by.epam.latyshey.library.dao.impl;


import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.data.source.test.Delimiter;
import by.epam.latyshey.library.data.source.test.InputSourceCustom;
import by.epam.latyshey.library.data.source.test.InputSourceCustomImpl;
import by.epam.latyshey.library.data.source.test.OutputSourceCustom;
import by.epam.latyshey.library.data.source.test.OutputSourceCustomImpl;
import by.epam.latyshey.library.session.SessionParameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSQL implements UserDAO {

  private final File file = new File(
      "src/main/java/by/epam/latyshey/library/data/source/test/UserSource.txt");
  private final InputSourceCustom inputSourceCustom = new InputSourceCustomImpl();
  private final OutputSourceCustom outputSourceCustom = new OutputSourceCustomImpl();
  private static final Logger logger = LogManager.getLogger(UserSQL.class);


  @Override
  public IUser signIn(String userName, String password) throws DAOException {
    IUser user = findUser(userName, password);
    SessionParameters.setLoggedInUser(user);
    return user;
  }

  @Override
  public void registration(IUser user) {
    String userRecord =
        user.getClass() + Delimiter.DELIM1 + user.getUserName() + Delimiter.DELIM1 + user.getName()
            + Delimiter.DELIM1 + user.getPass() + Delimiter.DELIM1 + user.getAge()
            + Delimiter.DELIM1;
    String taggedUserRecord = outputSourceCustom.wrapFieldsToTag(userRecord, "User");
    outputSourceCustom.addNewRecord(file, taggedUserRecord);
  }

  @Override
  public ArrayList<IUser> showSQLUser() {
    String[] users = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file), "User");
    ArrayList<IUser> allUsers = new ArrayList<>();
    for (String user : users) {
      String[] userDataArray = inputSourceCustom.separateByDelimiter(user, Delimiter.DELIM1);
      try {
        allUsers.add(createUser(userDataArray, Customer.class, Employee.class));
      } catch (DAOException e) {
        logger.error(e.getMessage());
      }
    }
    return allUsers;
  }

  @Override
  public void setUsers(ArrayList<IUser> users) { //todo проблема в неправильной записис повторю
    StringBuilder userTag = new StringBuilder();
    StringBuilder record = new StringBuilder();
    for (IUser user : users) {
      userTag
          .append(user.getClass())
          .append(Delimiter.DELIM1).append(user.getUserName())
          .append(Delimiter.DELIM1).append(user.getName())
          .append(Delimiter.DELIM1).append(user.getPass())
          .append(Delimiter.DELIM1).append(user.getAge());

      if (user.getClass().equals(Customer.class)) {
        userTag.append(Delimiter.DELIM1);
        String takenBooksRecord = convertTakenBooksToRecord(((Customer) user).getTakenBooks());
        takenBooksRecord =
            "\n" + outputSourceCustom.wrapFieldsToTag(takenBooksRecord, "TakenBooks")+Delimiter.DELIM1;
        userTag.append(takenBooksRecord);
      } else {
        userTag.append("\n");
      }
      record.append(outputSourceCustom.wrapFieldsToTag(userTag.toString(), "User") + Delimiter.DELIM6 + "\n\n");
      userTag = new StringBuilder();


    }
    logger.info("Установка пользователей:\n" + record.toString());
    outputSourceCustom.setAllData(file, record.toString());
  }


  @Override
  public String updateUser(String userName, String password, IUser newUser) {
    //todo создать новую запись. найти пользователя. заменить старую запись на новую.
    StringBuilder userTag = new StringBuilder();
    StringBuilder record = new StringBuilder();

      userTag
          .append(newUser.getClass())
          .append(Delimiter.DELIM1).append(newUser.getUserName())
          .append(Delimiter.DELIM1).append(newUser.getName())
          .append(Delimiter.DELIM1).append(newUser.getPass())
          .append(Delimiter.DELIM1).append(newUser.getAge());

      if (newUser.getClass().equals(Customer.class)) {
        userTag.append(Delimiter.DELIM1);
        String takenBooksRecord = convertTakenBooksToRecord(((Customer) newUser).getTakenBooks());
        takenBooksRecord =
            "\n" + outputSourceCustom.wrapFieldsToTag(takenBooksRecord, "TakenBooks") + Delimiter.DELIM1;
        userTag.append(takenBooksRecord);
      } else {
        userTag.append("\n");
      }
      record.append(outputSourceCustom.wrapFieldsToTag(userTag.toString(), "User"));

     String newRecord = record.toString();


    String[] users = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file), "User");
    for (String user : users) {
      String[] userDataArray = inputSourceCustom.separateByDelimiter(user, Delimiter.DELIM1);

      if (userName.equals(userDataArray[1]) && password.equals(userDataArray[3])) {
        String allData = inputSourceCustom.readAllData(file);
        user = outputSourceCustom.wrapFieldsToTag(user, "User");
        String updateFullRecord = outputSourceCustom.updateData(allData, user, newRecord);
        outputSourceCustom.setAllData(file, updateFullRecord);
      }
    }




    logger.info("Установка пользователей:\n" + record.toString());
    
    return null;
  }

  
  public String convertTakenBooksToRecord(ArrayList<ITakenBook> takenBooks) {
    StringBuilder response = new StringBuilder();
    for (ITakenBook takenBook : takenBooks) {
      response.append(takenBook.getAuthor())
          .append(Delimiter.DELIM3).append(takenBook.getTitle())
          .append(Delimiter.DELIM3).append(takenBook.getRarity())
          .append(Delimiter.DELIM3).append(takenBook.getTakeDate().getTime())
          .append(Delimiter.DELIM3).append(takenBook.getReturnDate().getTime())
          .append(Delimiter.DELIM2);
    }
    return response.toString();
  }

  private <C, E> IUser createUser(String[] arrayOfDataAboutObject, Class<C> customer,
      Class<E> employee) throws DAOException {

    String userName = arrayOfDataAboutObject[1];
    String name = arrayOfDataAboutObject[2];
    String pass = arrayOfDataAboutObject[3];
    int age = Integer.parseInt(arrayOfDataAboutObject[4]);

    if (customer.toString().equals(arrayOfDataAboutObject[0])) {
      try {
//         подлежит исправлению
        String takenBooks = inputSourceCustom.findTagsFields(arrayOfDataAboutObject[5],
            "TakenBooks")[0];

        String[] takenBooksArray = inputSourceCustom.separateByDelimiter(takenBooks, Delimiter.DELIM2);

        return new Customer(userName, name, pass, age, getTakenBooksArrayList(takenBooksArray));
      } catch (Exception e){
        return new Customer(userName, name, pass, age, new ArrayList<>()); //new array
      }
    }

    if (employee.toString().equals(arrayOfDataAboutObject[0])) {
      return new Employee(userName, name, pass, age);
    }

    logger.debug("Класс извлекаемого объекта не совпадает с ожидаемым.");
    throw new DAOException("Невалидные данные. Проверьте целостность файла данных!");

  }

  private ArrayList<ITakenBook> getTakenBooksArrayList(String[] customerBooks) {
    ArrayList<ITakenBook> takenBooks = new ArrayList<>();
    String[] customerBooksArray = customerBooks;
    for (String customerBook : customerBooksArray) {
      String[] customerBookData = customerBook.split(Delimiter.DELIM3);
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


  private IUser findUser(String userName, String password) throws DAOException {
    String[] users = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file), "User");


    for (String user : users) {
      String[] userDataArray = inputSourceCustom.separateByDelimiter(user, Delimiter.DELIM1);


      if (userName.equals(userDataArray[1]) && password.equals(userDataArray[3])) {

        return createUser(userDataArray, Customer.class, Employee.class);
      }
    }
    throw new DAOException("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n");
  }


}
