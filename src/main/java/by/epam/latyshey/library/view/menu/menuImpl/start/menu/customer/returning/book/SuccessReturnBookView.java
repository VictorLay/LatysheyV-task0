package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.returning.book;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class SuccessReturnBookView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    System.out.println("Возвращённая книга: \n"+ query);
  }
}
