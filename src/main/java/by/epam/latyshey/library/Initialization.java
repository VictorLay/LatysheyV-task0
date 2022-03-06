package by.epam.latyshey.library;

import by.epam.latyshey.library.bean.Rarity;
import by.epam.latyshey.library.controller.Controller;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.view.ControllerConnection;


public class Initialization {

  private Initialization() {
  }

  public static void init() {
    ControllerConnection controllerConnection = ControllerConnection.getInstance();
    Controller controller = controllerConnection.getController();

    controller.executeTask(
        CommandName.REGISTRATION_EMPLOYEE + ",userName=,Professor,name=,Карл,pass=,123456,age=,56");
    controller.executeTask(
        CommandName.REGISTRATION_CUSTOMER + ",userName=,DocDolitle,name=,Соня,pass=,123456,age=,8");
    controller.executeTask(
        CommandName.REGISTRATION_CUSTOMER + ",userName=,Midas,name=,Вова,pass=,123456,age=,28");
    controller.executeTask(
        CommandName.REGISTRATION_CUSTOMER + ",userName=,VicLay,name=,Витя,pass=,123456,age=,22");

    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Эрих Мария Ремарк,title=,Три товарища,Rarity=," + Rarity.RARE);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Виктор Гюго,title=,Отверженные,Rarity=," + Rarity.ORDINARY);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Михаил Шолохов,title=,Тихий Дон,Rarity=,"+ Rarity.ORDINARY);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Николай Гоголь,title=,Тарас Бульба,Rarity=,"+ Rarity.ORDINARY);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Михаил Булгаков,title=,Мастер и Маргарита,Rarity=,"+ Rarity.ORDINARY);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Федор Достоевский,title=,Преступление и наказание,Rarity=,"+ Rarity.ORDINARY);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Антуан де Сент-Экзюпери,title=,Маленький принц,Rarity=,"+ Rarity.RARE);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Чарльз Дарвин,title=,Происхождение видов,Rarity=,"+ Rarity.RARE);
    controller.executeTask(
        CommandName.ADD_BOOK + ",author=,Ричард Докинз,title=,Эгоистичный ген,Rarity=,"+ Rarity.VERY_RARE);

    controller.executeTask(CommandName.ADD_BOOK_TO_CUSTOMER
        + ",author=,Эрих Мария Ремарк,title=,Три товарища,userName=,Midas,pass=,123456");
    controller.executeTask(CommandName.ADD_BOOK_TO_CUSTOMER
        + ",author=,Антуан де Сент-Экзюпери,title=,Маленький принц,userName=,Midas,pass=,123456");
    controller.executeTask(CommandName.ADD_BOOK_TO_CUSTOMER
        + ",author=,Чарльз Дарвин,title=,Происхождение видов,userName=,VicLay,pass=,123456");
    controller.executeTask(CommandName.ADD_BOOK_TO_CUSTOMER
        + ",author=,Ричард Докинз,title=,Эгоистичный ген,userName=,VicLay,pass=,123456");
  }
}
