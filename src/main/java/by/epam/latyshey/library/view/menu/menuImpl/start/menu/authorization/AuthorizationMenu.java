package by.epam.latyshey.library.view.menu.menuImpl.start.menu.authorization;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import java.util.Scanner;

public class AuthorizationMenu implements MenuCreate {

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();

    authorizationView(controller, menuController);
  }

  private void authorizationView(Controller controller, MenuController menuController) {
    String query = createAuthorizationQuery();
    String response = controller.executeTask(query);
    menuController.executeMenuByName(response);
  }

  private String createAuthorizationQuery() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Введите Имя пользователя:");
    String userName = scanner.nextLine();

    System.out.println("Введите пароль:");
    String pass = scanner.nextLine();

    String query =
        CommandName.SIGN_IN + "," + "userName=" + "," + userName + "," + "pass=" + "," + pass + ",";
    return query;
  }
}
