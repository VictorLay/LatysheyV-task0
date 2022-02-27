package by.epam.latyshey.library.view;

import by.epam.latyshey.library.view.menu.MenuCreate;
import by.epam.latyshey.library.view.menu.MenuName;
import by.epam.latyshey.library.view.menu.menuImpl.*;

import by.epam.latyshey.library.view.menu.menuImpl.start.menu.MainMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.authorization.AuthorizationMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.CustomerMenuImpl;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.add.book.AddBookMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.add.book.SuccessfullyAddedBookView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.exit.CustomerExitView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.returning.book.ReturnBookMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.returning.book.SuccessReturnBookView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.show.customers.book.ShowCustomersBookView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.customer.show.free.books.ShowFreeBooksView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.EmployeeMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.exit.EmployeeExitView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.show.all.customers.ShowAllCustomersView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.employee.show.customer.history.show.all.ShowCustomersHistoriesView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.exit.ProgramExitView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.registration.RegistrationMenu;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.registration.SuccessfullyRegistrationView;
import by.epam.latyshey.library.view.menu.menuImpl.start.menu.show.users.ShowAllUsersView;
import by.epam.latyshey.library.view.menu.menuImpl.ErrorViewImpl;
import by.epam.latyshey.library.view.menu.menuImpl.view.ShowResponseView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuProvider {

  Map<String, MenuCreate> repository = new HashMap<>();

  MenuProvider() {
    repository.put(MenuName.CUSTOMER_MENU, new CustomerMenuImpl());
    repository.put(MenuName.EMPLOYEE_MENU, new EmployeeMenu());
    repository.put(MenuName.MAIN_MENU, new MainMenu());
    repository.put(MenuName.ENTRY_MENU, new EntryMenu());
    repository.put(MenuName.AUTHORIZATION_MENU, new AuthorizationMenu());
    repository.put(MenuName.REGISTRATION_MENU, new RegistrationMenu());
    repository.put(MenuName.ADDED_BOOK_MENU, new AddBookMenu());
    repository.put(MenuName.RETURN_BOOK_MENU, new ReturnBookMenu());

    repository.put(MenuName.EMPLOYEE_EXIT_VIEW, new EmployeeExitView());
    repository.put(MenuName.SUCCESS_RETURN_BOOK_VIEW, new SuccessReturnBookView());
    repository.put(MenuName.SHOW_ALL_CUSTOMERS_VIEW, new ShowAllCustomersView());
    repository.put(MenuName.SHOW_CUSTOMERS_HISTORIES_VIEW, new ShowCustomersHistoriesView());
    repository.put(MenuName.ERROR_VIEW, new ErrorViewImpl());
    repository.put(MenuName.CUSTOMER_EXIT_VIEW, new CustomerExitView());
    repository.put(MenuName.SUCCESS_ADDED_BOOK_VIEW, new SuccessfullyAddedBookView());
    repository.put(MenuName.SHOW_CUSTOMERS_BOOKS_VIEW, new ShowCustomersBookView());
    repository.put(MenuName.SHOW_FREE_LIBRARIAN_BOOKS_VIEW, new ShowFreeBooksView());
    repository.put(MenuName.SHOW_RESPONSE_VIEW, new ShowResponseView());
    repository.put(MenuName.SUCCESS_REGISTRATION_VIEW, new SuccessfullyRegistrationView());
    repository.put(MenuName.PROGRAM_EXIT_VIEW, new ProgramExitView());
    repository.put(MenuName.SHOW_USERS_VIEW, new ShowAllUsersView());
  }

  MenuCreate getMenu(String menuName) {
    String capsMenuName;
    MenuCreate menuCreate;

    capsMenuName = menuName.toUpperCase(Locale.ROOT);

    menuCreate = repository.get(capsMenuName);
    return menuCreate;
  }

}
