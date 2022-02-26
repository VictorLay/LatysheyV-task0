package by.epam.latyshey.library.view.menu.menuImpl;

import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.CustomerHistory;
import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.controller.command.ErrorName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthorizationMenuImpl implements MenuCreate {

  public static final String AUTHORIZATION = "1", REGISTRATION = "2", EXIT = "3", SHOW_USER_SQL = "4";
  public static final String CUSTOMER = "1", EMPLOYEE = "2";


  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();

    authorization(controller, menuController);
  }

  private void authorization(Controller controller, MenuController menuController) {
    boolean exit = true;

    do {
      System.out.println("""
          Выберите соответствущий пункт меню:
          1: Авторизация;
          2: Регистрация;
          3: Выход.
          4: Показать базу данных пользователей (с паролем).
          """);
      Scanner scanner = new Scanner(System.in);
      String query, response;
      String answer = scanner.nextLine();

      switch (answer) {
        case AUTHORIZATION:
          query = logInView();
          response = controller.executeTask(query);
          menuController.executeMenuName(response);

          break;
        case REGISTRATION:
          query = registrationView(scanner);
          response = controller.executeTask(query);
          if (response != null) {
            menuController.executeMenuName(response);
          }
          break;
        case EXIT:
          controller.executeTask(CommandName.SERIALIZE_SAVE + ",");

          exit = false;
          System.out.println("""
                                          
              Изменения были сохранены.
              Пока.
              """);
          System.out.println(
              "Было создано книг:" + Book.getNumberOfInstance() + "шт\n" + "Было создано Клиентов:"
                  + Customer.getNumberOfInstance() + "шт\n" + "Было создано Работников:"
                  + Employee.getNumberOfInstance() + "шт\n" + "Было создано Историй:"
                  + CustomerHistory.getNumberOfInstance() + "шт\n" + "Было создано Взятых книг:"
                  + TakenBook.getNumberOfInstance() + "шт\n");
          break;
        case SHOW_USER_SQL:
          query = CommandName.SHOW_USER_SQL + ",";
          response = controller.executeTask(query);
          System.out.println(response);
          break;
        default:
          System.out.println("Выбор не распознан");
          break;
      }

    } while (exit);

  }

  private static String logInView() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Введите Имя пользователя:");
    String userName = scanner.nextLine();

    System.out.println("Введите пароль:");
    String pass = scanner.nextLine();

    String query =
        CommandName.SIGN_IN + "," + "userName=" + "," + userName + "," + "pass=" + "," + pass + ",";
    return query;
  }

  private static String registrationView(Scanner scanner) {
    boolean exit = true;
    String query = null;

    do {
      String response;
      try {
        response = userRegistrationView();
      } catch (InputMismatchException e) {
        System.out.println("Введённый возраст должен быть целым числом");
        return CommandName.ERROR_MESSAGE
            + ", Пользовательские данные не прошли валидацию слоя View";
      }

      System.out.println("Кого вы хотите создать ?\n" + "1. Посетитель;\n" + "2. Работник.\n");
      String i = scanner.nextLine();
      switch (i) {
        case CUSTOMER:
          query = CommandName.REGISTRATION_CUSTOMER + response;
          exit = false;
          break;
        case EMPLOYEE:
          query = CommandName.REGISTRATION_EMPLOYEE + response;
          exit = false;
          break;
        default:
          System.out.println("Сделайте другой выбор.");
          break;
      }
    } while (exit);

    return query;
  }


  private static String userRegistrationView() throws InputMismatchException {
    Scanner scanner = new Scanner(System.in);
    String userName, name, pass;
    int age = 0;
    boolean exit = true;

    do {
      System.out.println("Введите имя пользователя");
      userName = scanner.nextLine();
      System.out.println("Введите имя");
      name = scanner.nextLine();
      System.out.println("Введите пароль");
      pass = scanner.nextLine();
      System.out.println("Введите возраст");

      age = scanner.nextInt();
      exit = false;

    } while (exit);

    String query =
        "," + "name=" + "," + userName + "," + "name=" + "," + name + "," + "pass=" + "," + pass
            + "," + "age=" + "," + age + ",";
    return query;
  }


}
