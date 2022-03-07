package by.epam.latyshey.library.dao.impl;


import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.exception.DAOException;
import by.epam.latyshey.library.data.source.Delimiter;
import by.epam.latyshey.library.data.source.InputSourceCustom;
import by.epam.latyshey.library.data.source.impl.InputSourceCustomImpl;
import by.epam.latyshey.library.data.source.OutputSourceCustom;
import by.epam.latyshey.library.data.source.impl.OutputSourceCustomImpl;
import by.epam.latyshey.library.session.SessionParameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSQL implements UserDAO {

  private final File file;
  private final InputSourceCustom inputSourceCustom = new InputSourceCustomImpl();
  private final OutputSourceCustom outputSourceCustom = new OutputSourceCustomImpl();
  private static final Logger logger = LogManager.getLogger(UserSQL.class);
  private static final String USER_TAG = "User";
  private static final String TAKEN_BOOKS_TAG = "TakenBooks";

  public UserSQL(){
    this.file = new File(
        "src/main/java/by/epam/latyshey/library/data/source/UserSource.txt");
  }

  /**
   * The constructor witch designed for tests.
   * @param file path to text file of data where will be written user's information.
   */
  public UserSQL(File file){
    this.file = file;
  }

  @Override
  public IUser signIn(String userName, String password) throws DAOException {
    IUser user = findUser(userName, password);
    SessionParameters.setLoggedInUser(user);
    return user;
  }

  @Override
  public void registration(IUser user) {
    String taggedUserRecord = userConvertToRecord(user);
    outputSourceCustom.addNewRecord(file, taggedUserRecord);
  }

  @Override
  public ArrayList<IUser> showSQLUser() {
    String[] users = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file), USER_TAG);
    ArrayList<IUser> allUsers = new ArrayList<>();
    for (String user : users) {
      String[] userDataArray = inputSourceCustom.separateByDelimiter(user, Delimiter.DELIM1);
      try {
        allUsers.add(createUser(userDataArray));
      } catch (DAOException e) {
        logger.error(e.getMessage());
      }
    }
    return allUsers;
  }

  @Override
  public void setUsers(ArrayList<IUser> users) {
    String record = usersConvertToRecord(users);
    outputSourceCustom.setAllData(file, record);
  }

  @Override
  public void updateUser(String userName, String password, IUser updatedUser){
    String newRecord = userConvertToRecord(updatedUser);
    try {
      String actualUserRecord = findUserRecord(userName, password);
      String updatedData = outputSourceCustom.updateData(inputSourceCustom.readAllData(file),
          actualUserRecord, newRecord);
      outputSourceCustom.setAllData(file, updatedData);
    } catch (DAOException e) {
      logger.error(e.getMessage());
    }
  }


  private ArrayList<ITakenBook> getTakenBooksArrayList(String[] customerBooks) {
   ArrayList<ITakenBook> takenBooks = new ArrayList<>();
   for (String customerBook : customerBooks) {
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

  private String takenBooksConvertToRecord(ArrayList<ITakenBook> takenBooks) {
    StringBuilder takenBooksInform = new StringBuilder();
    for (ITakenBook takenBook : takenBooks) {
     takenBooksInform.append(takenBook.getAuthor())
         .append(Delimiter.DELIM3).append(takenBook.getTitle())
         .append(Delimiter.DELIM3).append(takenBook.getRarity())
         .append(Delimiter.DELIM3).append(takenBook.getTakeDate().getTime())
         .append(Delimiter.DELIM3).append(takenBook.getReturnDate().getTime())
         .append(Delimiter.DELIM2);
    }
    return ("\n" + outputSourceCustom.wrapFieldsToTag(takenBooksInform.toString(), TAKEN_BOOKS_TAG)
       + Delimiter.DELIM1);
  }

  private IUser findUser(String userName, String password) throws DAOException {
    String[] usersFields = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file),
        USER_TAG);
    for (String userInform : usersFields) {
      String[] userFields = inputSourceCustom.separateByDelimiter(userInform, Delimiter.DELIM1);
      if (userName.equals(userFields[1]) && password.equals(userFields[3])) {
        return createUser(userFields);
      }
    }
    throw new DAOException("\nВНИМАНИЕ! Неверное имя пользователя или пароль!\n");
  }

  private String findUserRecord(String userName, String password) throws DAOException {
    String[] usersFields = inputSourceCustom.findTagsFields(inputSourceCustom.readAllData(file),
        USER_TAG);
    for (String userInform : usersFields) {
      String[] userFields = inputSourceCustom.separateByDelimiter(userInform, Delimiter.DELIM1);
      if (userName.equals(userFields[1]) && password.equals(userFields[3])) {
        return outputSourceCustom.wrapFieldsToTag(userInform, USER_TAG);
      }
    }
    throw new DAOException("\nUnfortunately the user's record with taken params is absent!\n");
  }

  private IUser createUser(String[] arrayOfDataAboutObject) throws DAOException {

    String userName = arrayOfDataAboutObject[1];
    String name = arrayOfDataAboutObject[2];
    String pass = arrayOfDataAboutObject[3];
    int age = Integer.parseInt(arrayOfDataAboutObject[4]);
    if (Customer.class.toString().equals(arrayOfDataAboutObject[0])) {
      try {
        String takenBooks = inputSourceCustom.findTagsFields(arrayOfDataAboutObject[5],
            TAKEN_BOOKS_TAG)[0];  // <-- the Customer class has only one field of TakenBooks.
        //     It means that method return an array with one element.
        String[] takenBooksArray = inputSourceCustom.separateByDelimiter(takenBooks,
            Delimiter.DELIM2);
        return new Customer(userName, name, pass, age, getTakenBooksArrayList(takenBooksArray));
      } catch (Exception e) {
        return new Customer(userName, name, pass, age, new ArrayList<>());
      }
    }
    if (Employee.class.toString().equals(arrayOfDataAboutObject[0])) {
      return new Employee(userName, name, pass, age);
    }
    logger.error("Класс извлекаемого объекта не совпадает с ожидаемым.");
    throw new DAOException("Невалидные данные. Проверьте целостность файла данных!");
  }

  private String usersConvertToRecord(ArrayList<IUser> users) {
    StringBuilder userInform = new StringBuilder();
    StringBuilder record = new StringBuilder();
    for (IUser user : users) {
      userInform
          .append(user.getClass())
          .append(Delimiter.DELIM1).append(user.getUserName())
          .append(Delimiter.DELIM1).append(user.getName())
          .append(Delimiter.DELIM1).append(user.getPass())
          .append(Delimiter.DELIM1).append(user.getAge())
          .append(Delimiter.DELIM1);
      if (user.getClass().equals(Customer.class)) {
        userInform.append(takenBooksConvertToRecord(((Customer) user).getTakenBooks()));
      }
      record.append(outputSourceCustom.wrapFieldsToTag(userInform.toString(), USER_TAG)).append("\n");
      userInform = new StringBuilder();
    }
    return record.toString();
  }

  private String userConvertToRecord(IUser user){
      String userInform =
          user.getClass()
          + Delimiter.DELIM1 + user.getUserName()
          + Delimiter.DELIM1 + user.getName()
          + Delimiter.DELIM1 + user.getPass()
          + Delimiter.DELIM1 + user.getAge()
          + Delimiter.DELIM1;
      if (user.getClass().equals(Customer.class)) {
        userInform += takenBooksConvertToRecord( ( (Customer)user ).getTakenBooks() ) ;
      }
      return outputSourceCustom.wrapFieldsToTag(userInform, USER_TAG);
  }


}