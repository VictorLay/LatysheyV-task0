package by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.exit;

import by.epam.latyshey.library.session.SessionParameters;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class EmployeeExitView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    SessionParameters.removeLoggedInUser();
  }
}
