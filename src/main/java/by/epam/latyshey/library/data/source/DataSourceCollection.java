package by.epam.latyshey.library.data.source;

import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import java.util.ArrayList;

public class DataSourceCollection {

  private static final DataSourceCollection instance = new DataSourceCollection();
  private ArrayList<IBook> books = new ArrayList<>();
  private ArrayList<ICustomerHistory> histories = new ArrayList<>();

  private DataSourceCollection() {
  }

  public static DataSourceCollection getInstance(){
    return instance;
  }

  public ArrayList<IBook> getBooks(){
    return books;
  }

  public void setBooks(ArrayList<IBook> books) {
    this.books = books;
  }

  public ArrayList<ICustomerHistory> getHistories() {
    return histories;
  }

  public void setHistories(ArrayList<ICustomerHistory> histories) {
    this.histories = histories;
  }
}
