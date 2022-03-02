package by.epam.latyshey.library.data.source;


import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import by.epam.latyshey.library.bean.interfaces.IUser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputSourceCustom {
  private final String DEL;
  private static Logger logger = LogManager.getLogger(OutputSourceCustom.class);

  public OutputSourceCustom(String DEL) {
    this.DEL = DEL;
  }

  public void addUserToFile(File file, IUser user) {

    try (OutputStream outputStream = new FileOutputStream(file, true)) {
      String record = user.getClass() +
                DEL + user.getUserName() +
                DEL + user.getName() +
                DEL + user.getPass() +
                DEL + user.getAge() +
                DEL +"\n";
      logger.info(record);
      outputStream.write(record.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      logger.error("ошибка записи в файл пользователя!");
    }
  }

  public void setUsers(File file, ArrayList<IUser> users){
    String record = ""; //todo need use StingBuilder
    try (OutputStream outputStream = new FileOutputStream(file)) {
      for (IUser user : users) {
        record += user.getClass() +
            DEL + user.getUserName() +
            DEL + user.getName() +
            DEL + user.getPass() +
            DEL + user.getAge();
        if (user.getClass().equals(Customer.class)){
         record += DEL + convertToStringRecord(((Customer) user).getTakenBooks()) + "\n";
        }else {
          record +=  "\n";
        }
      }
      outputStream.write(record.getBytes(StandardCharsets.UTF_8));
    }catch (Exception e){
      logger.error("ошибка записи в файл пользователей!");
    }
  }

  private String convertToStringRecord(ArrayList<ITakenBook> takenBooks) {
    String response ="";
    for (ITakenBook takenBook : takenBooks) {
      response += takenBook.getAuthor() + "§" +
          takenBook.getTitle() + "§" +
          takenBook.getRarity() + "§" +
          takenBook.getTakeDate().getTime() + "§"+
          takenBook.getReturnDate().getTime() +
          "▼";
    }
    return response;
  }
}




//    try {
//      directory.mkdirs();
//      file1.createNewFile();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

//    try ( InputStream inputStream = new BufferedInputStream(new FileInputStream(file1))) {
//
//      byte[] array = new byte[8];
//      int a = inputStream.read(array);
//      StringBuilder result = new StringBuilder();
//
//      int i = 0;
//      while (a > 0) {
//      i++;
//        result.append(new String(array));
//        a = inputStream.read(array);
//      }
//      rootLogger.info(result.toString() + "  "+i);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

//    rootLogger.info(file.getName());
//    rootLogger.info("file.exists(): " + file.exists());
//    rootLogger.info("file.isDirectory(): "  + file.isDirectory());
//    rootLogger.info("file.isFile(): " + file.isFile());
//    rootLogger.info("directory.isDirectory(): " + directory.isDirectory());

//    File[] files = directory.listFiles((dir, name) -> name.contains("1"));
//
//    for (File file : files) {
//      rootLogger.info(file.getName());
//    }

//    bean.User user = new bean.User();
//    user.setName("Anakin");
//    user.setLastName("Skywalker");
//
//    userLogger.info(user.showMeMessage());
//    userLogger.info(user.giveMeASign());
//    userLogger.info("{help}");
//
//    rootLogger.info("Root Logger: "  + user.showMeMessage());
//
//    //debug
//    if (rootLogger.isDebugEnabled()) {
//      rootLogger.debug("RootLogger: In debug message");
//      userLogger.debug("UserLogger in debug");
//    }
//
//    try {
//      bean.User userNull = new bean.User();
//      userNull.getName().toString();
//    } catch (NullPointerException ex) {
//      userLogger.error("error message: " + ex.getMessage());
//      userLogger.fatal("fatal error message: " + ex.getMessage());
//    }
