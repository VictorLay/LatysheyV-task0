package by.epam.latyshey.library;

import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;

import java.util.HashMap;
import java.util.Map;


public class TestService {

  //private Controller by.epam.latyshey.library.controller;
  private Map<String, TestService> repository = new HashMap<>();

  public TestService() {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();
    Controller controller = controllerConnection.getController();
    repository.put(CommandName.SIGN_IN_CUSTOMER,
        TestService.TestCustomerSignIn(controller, menuController));
    repository.put(CommandName.SIGN_IN_EMPLOYEE,
        TestService.TestEmployeeSignIn(controller, menuController));
    repository.put(CommandName.REGISTRATION_CUSTOMER,
        TestService.TestCustomerRegistration(controller, menuController));
    repository.put(CommandName.REGISTRATION_EMPLOYEE,
        TestService.TestEmployeeRegistration(controller, menuController));
  }

  public void executeTestCommand(String commandName) {
    repository.get(commandName);

  }

  private static TestService TestCustomerSignIn(Controller controller,
      MenuController menuController) {
    String response;
    response = controller.executeTask(CommandName.SIGN_IN + ",username=,Midas,pass=,123456");
    menuController.executeMenuName(response);
    return null;
  }

  private static TestService TestEmployeeSignIn(Controller controller,
      MenuController menuController) {
    String response;
    response = controller.executeTask(CommandName.SIGN_IN + ",username=,Professor,pass=,123456");
    menuController.executeMenuName(response);
    return null;
  }

  private static TestService TestEmployeeRegistration(Controller controller,
      MenuController menuController) {
    String response;
    response = controller.executeTask(CommandName.REGISTRATION_EMPLOYEE
        + ",userName=,TestEmpl,name=,TestName,pass=,123456,age=,50");
    // System.out.println(response);
    return null;
  }

  private static TestService TestCustomerRegistration(Controller controller,
      MenuController menuController) {
    String response;

    response = controller.executeTask(CommandName.REGISTRATION_CUSTOMER
        + ",userName=,TestCust,name=,TestName,pass=,123456,age=,8");
    //System.out.println(response);
    return null;
  }


}
