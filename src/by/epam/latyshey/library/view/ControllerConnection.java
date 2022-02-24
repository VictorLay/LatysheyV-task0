package by.epam.latyshey.library.view;

import by.epam.latyshey.library.controller.Controller;

public class ControllerConnection {

  private static final ControllerConnection instance = new ControllerConnection();

  private ControllerConnection() {
  }

  private final Controller controller = new Controller();
  private final MenuController menuController = new MenuController();

  public static ControllerConnection getInstance() {
    return instance;
  }

  public Controller getController() {
    return this.controller;
  }

  public MenuController getMenuController() {
    return this.menuController;
  }

}