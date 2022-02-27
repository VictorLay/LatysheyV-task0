package by.epam.latyshey.library.view.menu.menuImpl.start.menu.registration;

import by.epam.latyshey.library.view.menu.MenuCreate;

public class SuccessfullyRegistrationView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    String[] queryArray = query.split(",");
    String Status = queryArray[1],
        userName = queryArray[3],
        name = queryArray[5],
        age = queryArray[9];
    System.out.println(
          "================================================================\n" +
          "Введённые данные успешно прошли проверку. Добавлен новый пользо-\n" +
          "ватель:\n" +
          "Статус: " + Status + "\n" +
          "Имя пользователя: " + userName + "\n" +
          "Имя: " + name + "\n" +
          "Возраст:" + age + "\n" +
          "================================================================\n\n");
  }
}
