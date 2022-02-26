package by.epam.latyshey.library.view.menu.menuImpl;

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
          if (response != null)
          {
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
    System.out.println("Введите Имя пользователя");
    Scanner scanner = new Scanner(System.in);
    String userName, pass;
    userName = scanner.nextLine();
    System.out.println("Введите пароль");
    pass = scanner.nextLine();
    String query = CommandName.SIGN_IN + ","
        + "userName=" + "," + userName + ","
        + "pass=" + "," + pass + ",";
    return query;
  }

  private static String registrationView(Scanner scanner) {
    boolean exit = true;
    String query = null;
    do {
      System.out.println("Кого вы хотите создать ?\n" +
          "1. Посетитель;\n" +
          "2. Работник.\n");
      String i = scanner.nextLine();
      switch (i) {
        case CUSTOMER:
          query = customerRegistrationView();
          exit = false;
          break;
        case EMPLOYEE:
          query = employeeRegistrationView();
          exit = false;
          break;
        default:
          System.out.println("Сделайте другой выбор.");
          break;
      }
    } while (exit);

    return query;
  }

  private static String employeeRegistrationView() {
    Scanner scanner = new Scanner(System.in);
    String userName, name, pass;
    System.out.println("Введите имя пользователя");
    userName = scanner.nextLine();
    System.out.println("Введите имя");
    name = scanner.nextLine();
    System.out.println("Введите пароль");
    pass = scanner.nextLine();
    System.out.println("Введите возраст");
    int age = scanner.nextInt();

    //ВНИМАНИЕ ВНИМАНИЕ ВНИМАНИЕ (необходимо выполнениие валидации пользовательских данных)

    String query = CommandName.REGISTRATION_EMPLOYEE + ","
        + "name=" + "," + userName + ","
        + "name=" + "," + name + ","
        + "pass=" + "," + pass + ","
        + "age=" + "," + age + ",";
    return query;
  }

  private static String customerRegistrationView() {
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
      try
      {
        age = scanner.nextInt();
        exit = false;
      }
      catch (InputMismatchException e)
      {
        System.out.println("Введённый возраст должен быть целым числом");
        return CommandName.ERROR_MESSAGE + ", Пользовательские данные не прошли валидацию слоя View";
      }

    }while (exit);





    //ВНИМАНИЕ ВНИМАНИЕ ВНИМАНИЕ (необходимо выполнениие валидации пользовательских данных)

    String query = CommandName.REGISTRATION_CUSTOMER + ","
        + "name=" + "," + userName + ","
        + "name=" + "," + name + ","
        + "pass=" + "," + pass + ","
        + "age=" + "," + age + ",";
    return query;
  }


}
