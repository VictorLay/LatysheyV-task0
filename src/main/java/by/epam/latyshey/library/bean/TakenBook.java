package by.epam.latyshey.library.bean;


import by.epam.latyshey.library.bean.interfaces.IBook;
import by.epam.latyshey.library.bean.interfaces.ITakenBook;
import java.io.Serializable;
import java.util.Date;

public class TakenBook extends Book implements ITakenBook, Serializable {

  private static int numberOfInstance = 0;
  private Date takeDate;
  private Date returnDate;

  public TakenBook(IBook book, Date takeDate) {
    super(book.getAuthor(), book.getTitle(), book.getRarity());
    this.takeDate = takeDate;
    int weeks = book.getRarity().ordinal() + 1;
    long holdingTime = 1000L * 60 * 60 * 24 * 7 * weeks;
    this.returnDate = new Date(takeDate.getTime() + holdingTime);
    numberOfInstance++;
  }

  public static int getNumberOfInstance() {
    return numberOfInstance;
  }

  @Override
  public Date getTakeDate() {
    return takeDate;
  }

  @Override
  public Date getReturnDate() {
    return returnDate;
  }

  @Override
  public String getAuthor() {
    return super.getAuthor();
  }

  @Override
  public String getTitle() {
    return super.getTitle();
  }

  @Override
  public String toString() {
    return super.toString() + "\n   Дата получения: " + this.takeDate + "," +
        "\n   Дата возврата:" + this.returnDate + ".\n";
  }

  @Override
  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
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
    ITakenBook other = (TakenBook) obj;
    return this.getAuthor().equals(other.getAuthor()) &&
        this.getTitle().equals(other.getTitle());

  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
