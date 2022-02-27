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
          ================================================================
          Здравствуйте. Вы входите в систему...
          Выбирете парамтеры входа:
          1. Вход в систему (рекомендовано при повторном входе);
          2. Вход в систему (автоматическая инициализация);
          3. Ю (пустая система).      
          ================================================================
          """);

      answer = scanner.nextLine();
      switch (answer) {
        case "1":                                                                 //todo заменить строки на константы
          controller.executeTask(CommandName.SERIALIZE_LOAD + ",");
          menuController.executeMenuByName(MenuName.MAIN_MENU + ",");
          exit = false;
          break;
        case "2":
          Initialization.init();

          menuController.executeMenuByName(MenuName.MAIN_MENU + ",");
          exit = false;
          break;
        case "3":
          menuController.executeMenuByName(MenuName.MAIN_MENU + ",");
          exit = false;
          break;
        default:
          System.out.println("\nВыбор не распознон попробуйте снова.");
          break;
      }

    } while (exit);
  }
}
