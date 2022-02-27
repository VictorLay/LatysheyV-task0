package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.returning.book;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.validation.Validation;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import java.util.Scanner;

public class ReturnBookMenu implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();
    MenuController menuController = controllerConnection.getMenuController();

    ReturnBookMenuView(controller, menuController);
  }

  private void ReturnBookMenuView(Controller controller, MenuController menuController) {
    boolean exit = true;
    Scanner scanner = new Scanner(System.in);
    String userName = SessionParameters.getLoggedInUser()
        .getUserName(), pass = SessionParameters.getLoggedInUser().getPass();

    do {
      String response = controller.executeTask(
          CommandName.SHOW_CUSTOMER_BOOKS + ",username=," + userName + ",password=," + pass);
      int quantityOfCustomerBooks = Integer.parseInt(response.substring(0, response.indexOf(",")));
      if (quantityOfCustomerBooks == 0) {
        System.out.println("У вас нет книг.");
        break;
      }

      String customersBooks = response.substring(response.indexOf(",") + 1);

      System.out.println("================================================================\n" +
          customersBooks
          + "================================================================\n");

      System.out.println("Укажите [номер книги]");

      String choice = scanner.nextLine();
      if (Validation.isStringValueNumber(choice) && Validation
          .integerNumberInRangeValidation(Integer.parseInt(choice), 0, quantityOfCustomerBooks)) {
        controller.executeTask(
            CommandName.RETURN_BOOK + ",username=," + userName + ",password=," + pass + ",index=,"
                + choice);
        exit = false;

      } else {
        System.out.println("Введённое значение должно соответсвовать номеру книги. (от 1 до "
            + quantityOfCustomerBooks + ")");
      }


    } while (exit);

  }
}
