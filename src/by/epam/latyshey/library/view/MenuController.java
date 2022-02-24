package by.epam.latyshey.library.view;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class MenuController {

  private final MenuProvider providerMenu = new MenuProvider();

  public void executeMenuName(String response) {
    String menuName;

    menuName = response.substring(0, response.indexOf(","));
    MenuCreate menuCreate = providerMenu.getMenu(menuName);

    menuCreate.executeResponse(response);
  }
}
