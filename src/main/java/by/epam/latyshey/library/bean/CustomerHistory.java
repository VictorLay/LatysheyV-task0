package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import java.io.Serializable;
import java.util.ArrayList;


public class CustomerHistory implements ICustomerHistory, Serializable, Comparable<Object> {

  private ICustomer customer;
  private ArrayList<ITakenBook> takenBooks;


  public CustomerHistory(ICustomer customer) {
    takenBooks = new ArrayList<>();
    this.customer = customer;
  }
  @Override
  public ICustomer getCustomer() {
    return customer;
  }

  @Override
  public int compareTo(Object o) {
    ICustomerHistory otherObj = (ICustomerHistory) o;

    return getCustomer().compareTo(otherObj.getCustomer());
  }

  @Override
  public void setTakenBooks(ArrayList<ITakenBook> takenBooks) {
    this.takenBooks = takenBooks;
  }

  @Override
  public ArrayList<ITakenBook> getTakenBooks() {
    return takenBooks;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    String response;
    response = "История " + customer.getName() + ":\n";
    int i = 1;
    for (ITakenBook book : takenBooks) {
      response += "Книга " + (i++) + ":\n" + book + "\n";
    }
    return response;
  }

}
