package by.epam.latyshey.library;

import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuName;


public class Runner {

  public static void main(String[] args) {


    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();

    menuController.executeMenuByName(MenuName.ENTRY_MENU + ",");
  }
}
