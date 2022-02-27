package by.epam.latyshey.library.view.menu.menuImpl.start.menu;


import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import by.epam.latyshey.library.view.menu.MenuName;
import java.util.Scanner;

public class MainMenu implements MenuCreate {

  public static final String AUTHORIZATION = "1", REGISTRATION = "2", EXIT = "3", SHOW_USER_SQL = "4";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();

    mainMenuView(menuController);
  }

  private void mainMenuView(MenuController menuController) {
    boolean exit = true;

    do {
      System.out.println("""
          ================================================================
          Выберите соответствущий пункт меню:
          1: Авторизация;
          2: Регистрация;
          3: Выход.
          4: Показать базу данных пользователей (с паролем).
          ================================================================
          """);
      Scanner scanner = new Scanner(System.in);
      String choice = scanner.nextLine();

      switch (choice) {

        case AUTHORIZATION:
          menuController.executeMenuByName(MenuName.AUTHORIZATION_MENU + ",");
          break;

        case REGISTRATION:
          menuController.executeMenuByName(MenuName.REGISTRATION_MENU + ",");
          break;

        case EXIT:
          menuController.executeMenuByName(MenuName.EXIT_VIEW + ",");
          exit = false;
          break;

        case SHOW_USER_SQL:
          menuController.executeMenuByName(MenuName.SHOW_USERS_VIEW + ",");
          break;

        default:
          System.out.println("Выбор не распознан");
          break;
      }

    } while (exit);

  }
}
