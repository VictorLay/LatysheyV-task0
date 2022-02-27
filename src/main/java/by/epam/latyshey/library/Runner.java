package by.epam.latyshey.library;

import by.epam.latyshey.library.view.ControllerConnection;
import by.epam.latyshey.library.view.MenuController;
import by.epam.latyshey.library.view.menu.MenuName;


public class Runner {

  public static void main(String[] args) {


    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    MenuController menuController = controllerConnection.getMenuController();

    menuController.executeMenuByName(MenuName.ENTRY_MENU + ",");
    //todo валидация слоя сервисов
    // исключение для "непадательности" для сериализации при запуске программы
    // !реализация поиска по сущностям
    // !написать javadoc для классов из проекта
    // !mockito эмуляция
    // !log4j
    // !запись и чтение данных из файла
  }
}
