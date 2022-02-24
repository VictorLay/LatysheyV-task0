package by.epam.latyshey.library.bean.interfaces;

import by.epam.latyshey.library.bean.Rarity;

public interface IBook {

  Rarity getRarity();

  String getAuthor();

  void setAuthor(String author);

  void setTitle(String title);

  String getTitle();

  String toString();

  int hashCode();

  boolean equals(Object obj);
}
