package by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.show.all.customers;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class ShowAllCustomersView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    System.out.println(
        "================================================================\n" +
        controller.executeTask(CommandName.SHOW_CUSTOMERS + ",")  +
        "================================================================\n\n"

    );
  }
}
