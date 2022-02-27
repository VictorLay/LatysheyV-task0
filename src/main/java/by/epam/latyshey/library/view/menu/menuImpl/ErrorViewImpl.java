package by.epam.latyshey.library.view.menu.menuImpl;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class ErrorViewImpl implements MenuCreate {

  @Override
  public void executeResponse(String response) {

    System.out.println(
        "================================================================\n" +
        "В программе возникло несоответствие:\n" +
        response.substring(response.indexOf(",")+1) +
        "================================================================\n\n"
    );
  }
}
