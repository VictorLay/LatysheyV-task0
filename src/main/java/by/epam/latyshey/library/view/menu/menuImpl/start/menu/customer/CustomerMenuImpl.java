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

  private final String ADD_BOOK = "1", RETURN_BOOK = "2", SHOW_FREE_BOOKS = "3", SHOW_BOOKS = "4", EXIT = "5";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();
    MenuController menuController = controllerConnection.getMenuController();

    CustomerMenuView(controller, menuController);
  }
  private void CustomerMenuView(Controller controller, MenuController menuController){
    String userName = SessionParameters.getLoggedInUser().getUserName(),
        name = SessionParameters.getLoggedInUser().getName(),
        pass = SessionParameters.getLoggedInUser().getPass();
    int age =  SessionParameters.getLoggedInUser().getAge();

    System.out.println("\nПривет " + name + "!" +
        "\nРады приветствовать посетителя в нашем приложении.");
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

        case RETURN_BOOK:

          menuController.executeMenuByName(MenuName.RETURN_BOOK_MENU + ",");

//          System.out.println(
//              "================================================================\n" +
//                  controller.executeTask(
//                      CommandName.SHOW_CUSTOMER_BOOKS +
//                          ",username=," + userName +
//                          ",password=," + pass) +
//                  "================================================================\n"
//          );
//          System.out.println("Укажите [номер книги]");
//          choice = scanner.nextLine(); //todo the place of potential mistake
//
//          controller.executeTask(
//              CommandName.RETURN_BOOK +
//                  ",username=," + userName +
//                  ",password=," + pass +
//                  ",index=," + choice);
          break;

        case SHOW_FREE_BOOKS:
          System.out.println(
              "================================================================\n" +
                  controller.executeTask(CommandName.SHOW_ALL_BOOK + ",") + "\n" +
                  "================================================================\n");
          break;

        case SHOW_BOOKS:

          System.out.println(
              "================================================================\n" +
                  "Аккаунт клиента библиотеки:\n" +
                  "Логин: " + userName + "\n" +
                  "Имя: " + name + "\n" +
                  "Возраст: " + age + "\n" +
                  controller.executeTask(CommandName.SHOW_CUSTOMER_BOOKS
                      +",username=," + userName
                      +",password=," + pass)
                  + "================================================================\n");

          break;

        case EXIT:
          controller.executeTask(CommandName.SERIALIZE_SAVE + ",");
          exit = false;
          break;

        default:
          System.out.println("Выбор не распознан, попробуйте снова.");
          break;
      }
    } while (exit);

  }
}
