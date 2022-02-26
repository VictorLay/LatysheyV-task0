package by.epam.latyshey.library.view;

import by.epam.latyshey.library.view.menu.MenuCreate;
import by.epam.latyshey.library.view.menu.MenuName;
import by.epam.latyshey.library.view.menu.menuImpl.*;

import by.epam.latyshey.library.view.menu.menuImpl.view.AddedBookViewImpl;
import by.epam.latyshey.library.view.menu.menuImpl.view.ErrorViewImpl;
import by.epam.latyshey.library.view.menu.menuImpl.view.ShowResponseView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuProvider {

  Map<String, MenuCreate> repository = new HashMap<>();

  MenuProvider() {
    repository.put(MenuName.CUSTOMER_MENU, new CustomerMenuImpl());
    repository.put(MenuName.EMPLOYEE_MENU, new EmployeeMenuImpl());
    repository.put(MenuName.AUTHORIZATION_MENU, new AuthorizationMenuImpl());
    repository.put(MenuName.ENTRY_MENU, new EntryMenu());

    repository.put(MenuName.ERROR_VIEW, new ErrorViewImpl());
    repository.put(MenuName.ADDED_BOOK_VIEW, new AddedBookViewImpl());
    repository.put(MenuName.SHOW_RESPONSE_VIEW, new ShowResponseView());
  }

  MenuCreate getMenu(String menuName) {
    String capsMenuName;
    MenuCreate menuCreate;

    capsMenuName = menuName.toUpperCase(Locale.ROOT);

    menuCreate = repository.get(capsMenuName);
    return menuCreate;
  }

}
