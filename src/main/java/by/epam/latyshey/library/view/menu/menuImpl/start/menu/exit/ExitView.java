package by.epam.latyshey.library.view.menu.menuImpl.start.menu.exit;

import by.epam.latyshey.library.bean.Book;
import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.bean.CustomerHistory;
import by.epam.latyshey.library.bean.Employee;
import by.epam.latyshey.library.bean.TakenBook;
import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.menu.MenuCreate;

public class ExitView implements MenuCreate {

  @Override
  public void executeResponse(String query) {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    exitInformationView(controller);
  }

  private void exitInformationView(Controller controller) {
    controller.executeTask(CommandName.SERIALIZE_SAVE + ",");

    System.out.println(
            "================================================================\n" +
            "Изменения были сериализованы.\n\n" +
            "Отчёт о количестве созданных объектов за сессию:\n" +
            "Было создано книг:" + Book.getNumberOfInstance() + "шт\n" +
            "Было создано Клиентов:" + Customer.getNumberOfInstance() + "шт\n" +
            "Было создано Работников:" + Employee.getNumberOfInstance() + "шт\n" +
            "Было создано Историй:" + CustomerHistory.getNumberOfInstance() + "шт\n" +
            "Было создано Взятых книг:" + TakenBook.getNumberOfInstance() + "шт\n" +
            "================================================================\n");
  }
}
