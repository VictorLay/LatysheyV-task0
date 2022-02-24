package by.epam.latyshey.library.view.menu.menuImpl;

import by.epam.latyshey.library.Initialization;
import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import by.epam.latyshey.library.view.menu.MenuName;

import java.util.Scanner;

public class EntryMenu implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();

    boolean exit = true;
    Scanner scanner = new Scanner(System.in);
    String answer;
    do {
      System.out.println("""
          Здравствуйте. Вы входите в систему...
          Выбирете парамтеры входа:
          1. Вход в систему (рекомендовано при повторном входе);
          2. Вход в систему (автоматическая инициализация);
          3. Вход в систему (пустая система);
          4. Тестовый вызов команд (не рекомендовано).
          """);

      answer = scanner.nextLine();
      switch (answer) {
        case "1":
          controller.executeTask(CommandName.SERIALIZE_LOAD + ",");

          menuController.executeMenuName(MenuName.AUTHORIZATION_MENU + ",");
          exit = false;
          break;
        case "2":
          Initialization.init();

          menuController.executeMenuName(MenuName.AUTHORIZATION_MENU + ",");
          exit = false;
          break;
        case "3":
          menuController.executeMenuName(MenuName.AUTHORIZATION_MENU + ",");
          exit = false;
          break;
        case "4":

          Initialization.init();
          by.epam.latyshey.library.TestService test = new by.epam.latyshey.library.TestService();
          test.executeTestCommand(CommandName.SIGN_IN_CUSTOMER);
          test.executeTestCommand(CommandName.SIGN_IN_EMPLOYEE);
          test.executeTestCommand(CommandName.REGISTRATION_CUSTOMER);
          test.executeTestCommand(CommandName.REGISTRATION_EMPLOYEE);

          menuController.executeMenuName(MenuName.AUTHORIZATION_MENU + ",");
          exit = false;
          break;
        default:
          System.out.println("\nВыбор не распознон попробуйте снова.");
          break;
      }

    } while (exit);
  }
}
