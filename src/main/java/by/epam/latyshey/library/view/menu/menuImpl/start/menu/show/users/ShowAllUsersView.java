package by.epam.latyshey.library.view.menu.menuImpl.start.menu.show.users;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class ShowAllUsersView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    showUsersView(controller);
  }

  private void showUsersView(Controller controller) {
    String query = CommandName.SHOW_USER_SQL + ",";
    String response = controller.executeTask(query);
    System.out.println(
              "================================================================\n" +
              response +
              "\n================================================================");
  }

}
