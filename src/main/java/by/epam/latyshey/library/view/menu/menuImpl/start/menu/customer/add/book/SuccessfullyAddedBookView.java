package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.add.book;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class SuccessfullyAddedBookView implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    System.out.println(
        "================================================================\n" +
            "Вами была добавлена книга:\n" +
            response.substring(response.indexOf(",")+1) + "" +
        "================================================================\n\n"
    );
  }
}
