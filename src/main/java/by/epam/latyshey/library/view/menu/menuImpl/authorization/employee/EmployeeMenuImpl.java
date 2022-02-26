package by.epam.latyshey.library.view.menu.menuImpl.authorization.employee;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

import java.util.Scanner;

public class EmployeeMenuImpl implements MenuCreate {

  private final String SHOW_CUSTOMERS = "2", SHOW_CUSTOMERS_HISTORIES = "1", EXIT = "3";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();
    String[] responseArray = response.split(",");

    String userName = responseArray[2],
        name = responseArray[4],
        pass = responseArray[6];
    int age = Integer.parseInt(responseArray[8]);

    System.out.println("\n Привет " + name + "! \nРад приветствовать нашего работника.\n");
    Scanner scanner = new Scanner(System.in);
    String query;
    boolean exit = true;
    do {

      System.out.println("""
          Выберите соответствущий пункт меню:
          1: Истории пользователей
          2: Список пользователей библиотеки
          3: Выйти.
          """);
      query = scanner.nextLine();

      switch (query) {
        case SHOW_CUSTOMERS_HISTORIES:
          System.out.println(controller.executeTask(CommandName.SHOW_CUSTOMERS_HISTORIES + ","));
          break;
        case SHOW_CUSTOMERS:
          System.out.println(controller.executeTask(CommandName.SHOW_CUSTOMERS + ","));
          break;
        case EXIT:
          exit = false;
          break;
        default:
          System.out.println("Выбор не распознан, попробуйте снова.");
          break;
      }


    } while (exit);

  }
}
