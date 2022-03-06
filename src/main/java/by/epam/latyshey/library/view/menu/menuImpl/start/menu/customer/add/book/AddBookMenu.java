package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.add.book;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import java.util.Scanner;

public class AddBookMenu implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();
    MenuController menuController = controllerConnection.getMenuController();

    addBookMenuView(controller, menuController);
  }

  private void addBookMenuView(Controller controller, MenuController menuController){
    String userName = SessionParameters.getLoggedInUser().getUserName();
    String pass = SessionParameters.getLoggedInUser().getPass();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введи автора:");
    String author = scanner.nextLine();
    System.out.println("Введи название:");
    String title = scanner.nextLine();

    String response = controller.executeTask(
            CommandName.ADD_BOOK_TO_CUSTOMER +
                ",author=," + author +
                ",title=," + title +
                ",userName=," + userName +
                ",pass=," + pass);

    menuController.executeMenuByName(response);
  }
}
