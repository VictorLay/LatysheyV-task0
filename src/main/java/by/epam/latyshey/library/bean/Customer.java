package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements ICustomer, Serializable, Comparable<Object> {

  private ArrayList<ITakenBook> takenBooks;

  @Override
  public int compareTo(Object o) {
    ICustomer otherObj = (Customer) o;
    return Integer.compare(this.getTakenBooks().size(), otherObj.getTakenBooks().size());
  }

  @Override
  public void setTakenBooks(ArrayList<ITakenBook> takenBooks) {
    this.takenBooks = takenBooks;
  }

  public Customer(String userName, String name, String pass, int age) {
    super(userName, name, pass, age);
    takenBooks = new ArrayList<>();
  }

  @Override
  public ArrayList<ITakenBook> getTakenBooks() {
    return takenBooks;
  }


  @Override
  public String toString() {
    return "CUSTOMER," + super.toString();
  }

  @Override
  public String getUserName() {
    return super.getUserName();
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public String getPass() {
    return super.getPass();
  }

  @Override
  public int getAge() {
    return super.getAge();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

}