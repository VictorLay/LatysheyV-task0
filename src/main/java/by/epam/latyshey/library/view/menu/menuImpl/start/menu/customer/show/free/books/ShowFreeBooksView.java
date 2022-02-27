package by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.show.free.books;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class ShowFreeBooksView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    System.out.println(
        "================================================================\n" +
            "Книги библиотеки, которые имеются в наличии: \n\n"+
            controller.executeTask(CommandName.SHOW_ALL_BOOK + ",") +
            "================================================================\n\n");
  }
}
