package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

import by.epam.latyshey.library.view.menu.MenuName;
import java.util.Scanner;

public class CustomerMenuImpl implements MenuCreate {

  private final String ADD_BOOK = "1", RETURN_CUSTOMERS_BOOK = "2", SHOW_FREE_BOOKS = "3", SHOW_CUSTOMERS_BOOKS = "4", EXIT = "5";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();
    MenuController menuController = controllerConnection.getMenuController();

    CustomerMenuView(controller, menuController);
  }

  private void CustomerMenuView(Controller controller, MenuController menuController) {

    System.out.println("\nПривет " + SessionParameters.getLoggedInUser().getName() + "!"
        + "\nРады приветствовать посетителя в нашем приложении.");
    Scanner scanner = new Scanner(System.in);
    String choice;
    boolean exit = true;
    do {

      System.out.println("""
          ================================================================
          Выберите соответствущий пункт меню:
          1: Добавить книку в портфель;
          2: Вернуть книгу;
          3: Посмотреть список книг в библиотеке;
          4: Информация о профиле и портфеле;
          5: Выйти.
          ================================================================
          """);
      choice = scanner.nextLine();

      switch (choice) {
        case ADD_BOOK:
          menuController.executeMenuByName(MenuName.ADDED_BOOK_MENU + ",");
          break;

        case RETURN_CUSTOMERS_BOOK:

          menuController.executeMenuByName(MenuName.RETURN_BOOK_MENU + ",");
          break;

        case SHOW_FREE_BOOKS:
          menuController.executeMenuByName(MenuName.SHOW_FREE_LIBRARIAN_BOOKS_VIEW + ",");
          break;

        case SHOW_CUSTOMERS_BOOKS:
          menuController.executeMenuByName(MenuName.SHOW_CUSTOMERS_BOOKS_VIEW);
          break;

        case EXIT:
          menuController.executeMenuByName(MenuName.CUSTOMER_EXIT_VIEW);
          exit = false;
          break;

        default:
          System.out.println("Выбор не распознан, попробуйте снова.");
          break;
      }
    } while (exit);

  }
}
