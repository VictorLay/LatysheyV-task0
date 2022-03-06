package by.epam.latyshey.library.view.menu.menuImpl.start.menu.registration;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class SuccessfullyRegistrationView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    String[] queryArray = query.split(",");
    String status = queryArray[1];
    String userName = queryArray[3];
    String name = queryArray[5];
    String age = queryArray[9];
    System.out.println(
          "================================================================\n" +
          "Введённые данные успешно прошли проверку. Добавлен новый пользо-\n" +
          "ватель:\n" +
          "Статус: " + status + "\n" +
          "Имя пользователя: " + userName + "\n" +
          "Имя: " + name + "\n" +
          "Возраст:" + age + "\n" +
          "================================================================\n\n");
  }
}
