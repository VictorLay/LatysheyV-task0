package by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

import by.epam.latyshey.library.view.menu.MenuName;
import java.util.Scanner;

public class EmployeeMenu implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();

    EmployeeMenuView(controller, menuController);
  }

  private void EmployeeMenuView(Controller controller, MenuController menuController) {
    System.out.println("\n Привет " + SessionParameters.getLoggedInUser().getName()
        + "! \nРад приветствовать нашего работника.\n");

    final String SHOW_ALL_CUSTOMERS = "2",
        SHOW_CUSTOMERS_HISTORIES = "1",
        EXIT = "3";
    Scanner scanner = new Scanner(System.in);
    boolean exit = true;
    do {
      System.out.println("""
          Выберите соответствущий пункт меню:
          1: Истории пользователей
          2: Список пользователей библиотеки
          3: Выйти.
          """);
      String query = scanner.nextLine();

      switch (query) {
        case SHOW_CUSTOMERS_HISTORIES:
          menuController.executeMenuByName(MenuName.SHOW_CUSTOMERS_HISTORIES_VIEW + ",");
          break;
        case SHOW_ALL_CUSTOMERS:
          menuController.executeMenuByName(MenuName.SHOW_ALL_CUSTOMERS_VIEW + ",");
          break;
        case EXIT:
          menuController.executeMenuByName(MenuName.EMPLOYEE_EXIT_VIEW + ",");
          exit = false;
          break;
        default:
          System.out.println("Выбор не распознан, попробуйте снова.");
          break;
      }
    } while (exit);
  }
}

