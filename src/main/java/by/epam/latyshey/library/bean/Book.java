package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.IBook;
import java.io.Serializable;

public class Book implements Serializable, IBook {

  private String author;
  private String title;
  private Rarity rarity;

  public Book(String author, String title, Rarity rarity) {
    this.rarity = rarity;
    this.author = author;
    this.title = title;
  }

  @Override
  public Rarity getRarity() {
    return this.rarity;
  }

  @Override
  public String getAuthor() {
    return author;
  }

  @Override
  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return ("Автор: '" + this.author + "', название: '" + this.title + "', редкость: '" + this.rarity + "'.");
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
