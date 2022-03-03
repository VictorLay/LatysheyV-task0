package by.epam.latyshey.library.data.source;

import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import java.util.ArrayList;

public class DataSourceCollection {

  private static final DataSourceCollection instance = new DataSourceCollection();
  private static ArrayList<IBook> books = new ArrayList<>();
  private static ArrayList<ICustomerHistory> histories = new ArrayList<>();

  private DataSourceCollection() {
  }

  public static DataSourceCollection getInstance(){
    return instance;
  }

  public ArrayList<IBook> getBooks(){
    return books;
  }

  public void setBooks(ArrayList<IBook> books) {
    DataSourceCollection.books = books;
  }

  public ArrayList<ICustomerHistory> getHistories() {
    return histories;
  }

  public void setHistories(ArrayList<ICustomerHistory> histories) {
    DataSourceCollection.histories = histories;
  }
}
