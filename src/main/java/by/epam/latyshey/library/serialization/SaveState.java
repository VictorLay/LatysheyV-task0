package by.epam.latyshey.library.serialization;

import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.IUser;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveState implements Serializable {

  private static final long serialVersionUID = 1L;

  private ArrayList<IUser> users;
  private ArrayList<ICustomerHistory> history;
  private ArrayList<IBook> books;

  @Override
  public String toString() {
    StringBuilder response = new StringBuilder();
    for (IUser user : this.users) {
      response.append(user.toString()).append("\n");
    }
    return response.toString();
  }

  public void setUsers(ArrayList<IUser> users) {
    this.users = users;
  }

  public ArrayList<IUser> getUsers() {
    return users;
  }

  public void setHistory(ArrayList<ICustomerHistory> history) {
    this.history = history;
  }

  public void setBooks(ArrayList<IBook> books) {
    this.books = books;
  }

  public ArrayList<ICustomerHistory> getHistory() {
    return history;
  }

  public ArrayList<IBook> getBooks() {
    return books;
  }

  public SaveState(ArrayList<IUser> users, ArrayList<ICustomerHistory> history,
      ArrayList<IBook> books) {
    this.users = users;
    this.history = history;
    this.books = books;
  }
}
