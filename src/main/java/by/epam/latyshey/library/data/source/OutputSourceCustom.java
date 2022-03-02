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

  public void setUsers(File file, String newFullRecord){

    try (OutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(newFullRecord.getBytes(StandardCharsets.UTF_8));
    }catch (Exception e){
      logger.error("ошибка записи в файл пользователей!");
    }
  }

  public String convertToStringRecord(ArrayList<ITakenBook> takenBooks) {
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
