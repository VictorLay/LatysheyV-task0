package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements ICustomer, Serializable, Comparable<Object> {

  private static int numberOfInstance = 0;
  private ArrayList<ITakenBook> takenBooks;


  public Customer(String userName, String name, String pass, int age) {
    super(userName, name, pass, age);
    takenBooks = new ArrayList<>();
    numberOfInstance++;
  }

  public Customer(String userName, String name, String pass, int age, ArrayList<ITakenBook> customerBooks ) {
    super(userName, name, pass, age);
    takenBooks = customerBooks;
    numberOfInstance++;
  }

  public static int getNumberOfInstance() {
    return numberOfInstance;
  }

  @Override
  public int compareTo(Object otherCustomer) {
    ICustomer otherObj = (Customer) otherCustomer;
    System.out.println(Integer.compare(this.getTakenBooks().size(), otherObj.getTakenBooks().size()));
    return Integer.compare(this.getTakenBooks().size(), otherObj.getTakenBooks().size());
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
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ICustomer other = (Customer) obj;
    return this.getUserName().equals(other.getUserName()) &&
        this.getName().equals(other.getName()) &&
        this.getPass().equals(other.getPass()) &&
        this.getAge() == other.getAge();
  }

}
