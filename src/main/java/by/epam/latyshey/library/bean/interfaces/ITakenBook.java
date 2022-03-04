package by.epam.latyshey.library.bean.interfaces;

import java.util.Date;

public interface ITakenBook extends IBook {

  Date getTakeDate();

  Date getReturnDate();

  String getAuthor();

  String getTitle();

  void setAuthor(String author);

  void setTitle(String title);

  String toString();

  void setReturnDate(Date returnDate);

  boolean equals(Object obj);

  int hashCode();

}
