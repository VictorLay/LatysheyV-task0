package by.epam.latyshey.library.view.menu.menuImpl.view;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class AddedBookViewImpl implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    System.out.println(
        "================================================================\n\n" +
            "Вами была добавлена книга:\n" +
            response.substring(response.indexOf(",")+1) + "\n" +
        "================================================================\n\n"
    );
  }
}
