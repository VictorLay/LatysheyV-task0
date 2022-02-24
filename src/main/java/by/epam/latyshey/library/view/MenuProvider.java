package by.epam.latyshey.library.view;

import by.epam.latyshey.library.view.menu.MenuCreate;
import by.epam.latyshey.library.view.menu.MenuName;
import by.epam.latyshey.library.view.menu.menuImpl.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuProvider {

  Map<String, MenuCreate> repository = new HashMap<>();

  MenuProvider() {
    repository.put(MenuName.CUSTOMER_MENU, new CustomerMenuImpl());
    repository.put(MenuName.EMPLOYEE_MENU, new EmployeeMenuImpl());
    repository.put(MenuName.AUTHORIZATION_MENU, new AuthorizationMenuImpl());
    repository.put(MenuName.ERROR, new ErrorViewImpl());
    repository.put(MenuName.ENTRY_MENU, new EntryMenu());
  }

  MenuCreate getMenu(String menuName) {
    String capsMenuName;
    MenuCreate menuCreate;

    capsMenuName = menuName.toUpperCase(Locale.ROOT);

    menuCreate = repository.get(capsMenuName);
    return menuCreate;
  }

}
