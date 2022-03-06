package by.epam.latyshey.library;

import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Runner {

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(Customer.class);
    logger.info("ВитяИнфо");

    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();

    menuController.executeMenuByName(MenuName.ENTRY_MENU + ",");

  }
}
