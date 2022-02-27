package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.show.customers.book;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class ShowCustomersBookView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    System.out.println(
        "================================================================\n" +
            "Аккаунт клиента библиотеки:\n\n" +
            "Логин: " + SessionParameters.getLoggedInUser().getUserName() + "\n" +
            "Имя: " + SessionParameters.getLoggedInUser().getName() + "\n" +
            "Возраст: " + SessionParameters.getLoggedInUser().getAge() + "\n" +
            controller.executeTask(CommandName.SHOW_CUSTOMER_BOOKS
                +",username=," + SessionParameters.getLoggedInUser().getUserName()
                +",password=," + SessionParameters.getLoggedInUser().getPass())
            + "================================================================\n");

  }
}
