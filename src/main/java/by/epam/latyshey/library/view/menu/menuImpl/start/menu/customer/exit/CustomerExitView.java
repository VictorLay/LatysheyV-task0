package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.exit;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class CustomerExitView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    controller.executeTask(CommandName.SERIALIZE_SAVE + ",");
    SessionParameters.removeLoggedInUser();
  }
}
