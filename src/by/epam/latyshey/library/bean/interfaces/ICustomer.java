package by.epam.latyshey.library.bean.interfaces;

import java.util.ArrayList;

public interface ICustomer extends IUser{

  int compareTo(Object o);

  void setTakenBooks(ArrayList<ITakenBook> takenBooks);

  ArrayList<ITakenBook> getTakenBooks();

  String toString();

  String getUserName();

  String getName();

  String getPass();

  int getAge();

  int hashCode();

  boolean equals(Object obj);

}
