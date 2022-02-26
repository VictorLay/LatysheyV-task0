package by.epam.latyshey.library.view.menu.menuImpl;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

import java.util.Scanner;

public class CustomerMenuImpl implements MenuCreate {

  private final String ADD_BOOK = "1", RETURN_BOOK = "2", SHOW_FREE_BOOKS = "3", SHOW_BOOKS = "4", EXIT = "5";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();
    MenuController menuController = controllerConnection.getMenuController();
    String[] responseArray = response.split(",");

    String userName = responseArray[2],
        name = responseArray[4],
        pass = responseArray[6];
    int age = Integer.parseInt(responseArray[8]);

    System.out.println("\nПривет " + name + "! \nРад приветствовать нашего посетителя.\n");
    Scanner scanner = new Scanner(System.in);
    String query;

    boolean exit = true;
    do {

      System.out.println("""
          Выберите соответствущий пункт меню:
          1: Добавить книку в портфель;
          2: Вернуть книгу;
          3: Посмотреть список книг в библиотеке;
          4: Информация о профиле и портфеле;
          5: Выйти.
          """);
      query = scanner.nextLine();

      switch (query) {
        case ADD_BOOK:

          System.out.println("Введи автора:");
          String author = scanner.nextLine();
          System.out.println("Введи название:");
          String title = scanner.nextLine();

          menuController.executeMenuName(
              controller.executeTask(
                  CommandName.ADD_BOOK_TO_CUSTOMER +
                      ",author=," + author +
                      ",title=," + title +
                      ",userName=," + userName +
                      ",pass=," + pass));
          break;

        case RETURN_BOOK:
          System.out.println(
              "================================================================\n\n" +
                  controller.executeTask(
                      CommandName.SHOW_CUSTOMER_BOOKS +
                          ",username=," + userName +
                          ",password=," + pass) +
                  "================================================================\n\n"
          );
          System.out.println("Укажите [номер книги]");
          query = scanner.nextLine(); //todo the place of potential mistake

          controller.executeTask(
              CommandName.RETURN_BOOK +
                  ",username=," + userName +
                  ",password=," + pass +
                  ",index=," + query);
          break;

        case SHOW_FREE_BOOKS:
          System.out.println(
              "================================================================\n\n" +
              controller.executeTask(CommandName.SHOW_ALL_BOOK + ",") + "\n" +
              "================================================================\n\n" );
          break;

        case SHOW_BOOKS:

          System.out.println(
              "================================================================\n\n" +
                  "Аккаунт клиента библиотеки:\n" +
                  "Логин: " + userName + "\n" +
                  "Имя: " + name + "\n" +
                  "Возраст: " + age + "\n" +
                  controller.executeTask(
                      CommandName.SHOW_CUSTOMER_BOOKS +
                          ",username=," + userName +
                          ",password=," + pass) +
              "================================================================\n\n" );

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
