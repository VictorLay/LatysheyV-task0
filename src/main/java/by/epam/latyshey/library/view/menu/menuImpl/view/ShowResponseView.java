package by.epam.latyshey.library.view.menu.menuImpl.view;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class ShowResponseView implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    System.out.println(
        "================================================================\n\n" +
        response +
        "================================================================\n\n");
  }
}
