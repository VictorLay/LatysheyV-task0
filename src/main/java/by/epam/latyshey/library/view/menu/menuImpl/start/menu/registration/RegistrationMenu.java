package by.epam.latyshey.library.view.menu.menuImpl.start.menu.registration;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.validation.Validation;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;
import java.util.Scanner;

public class RegistrationMenu implements MenuCreate {

  public static final String CUSTOMER = "1", EMPLOYEE = "2";

  @Override
  public void executeResponse(String response) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();

    registrationView(controller, menuController);
  }

  private void registrationView(Controller controller, MenuController menuController) {
    String query = createRegistrationQuery();
    String response = controller.executeTask(query);
    menuController.executeMenuByName(response);
  }

  private String createRegistrationQuery() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = true;
    String response = null;

    do {
      String query = createQueryWithUsersFields();
      System.out.println("""
          Выбирете статус регистрируемого пользователя:
          1. Посетитель;
          2. Работник.
          """);
      String chose = scanner.nextLine();

      switch (chose) {
        case CUSTOMER:
          response = CommandName.REGISTRATION_CUSTOMER + query;
          exit = false;
          break;

        case EMPLOYEE:
          response = CommandName.REGISTRATION_EMPLOYEE + query;
          exit = false;
          break;

        default:
          System.out.println("Выберите вариант из представленных.");
          break;
      }
    } while (exit);

    return response;
  }

  private String createQueryWithUsersFields() {
    Scanner scanner = new Scanner(System.in);
    String userName, name, pass, age = "";
    boolean exit = true;

    do {
      String validationMessage = "";
      System.out.println("Введите имя пользователя ");
      userName = scanner.nextLine();
      System.out.println("Введите имя");
      name = scanner.nextLine();
      System.out.println("Введите пароль");
      pass = scanner.nextLine();
      System.out.println("Введите возраст");
      age = scanner.nextLine();

      validationMessage += Validation.usersUserNameValidation(userName) ? ""
          : "Имя пользователя '" + userName + "' не подходит\n";
      validationMessage +=
          Validation.usersNameValidation(name) ? "" : "Имя '" + name + "' не подходит\n";
      validationMessage += Validation.usersPasswordValidation(pass) ? ""
          : "Пароль не должен иметь пробелов и содержать от 6 символов\n";
      validationMessage +=
          Validation.usersAgeValidation(age) ? "" : "Возраст должен быть в диапазоне от 1 до 99\n";

      if (validationMessage.equals("")) {
        exit = false;
      } else {
        System.out.println(
              "================================================================\n"
            + validationMessage
            + "\n================================================================");
      }

    } while (exit);

    String response =
        "," + "name=" + "," + userName + "," + "name=" + "," + name + "," + "pass=" + "," + pass
            + "," + "age=" + "," + age + ",";
    return response;
  }
}
