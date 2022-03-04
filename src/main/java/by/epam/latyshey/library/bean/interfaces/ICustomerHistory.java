package by.epam.latyshey.library.bean.interfaces;

import java.util.ArrayList;

public interface ICustomerHistory {

  ICustomer getCustomer();

  int compareTo(Object otherHistory);

  void setTakenBooks(ArrayList<ITakenBook> takenBooks);

  ArrayList<ITakenBook> getTakenBooks();

  int hashCode();

  boolean equals(Object obj);

  String toString();
}
