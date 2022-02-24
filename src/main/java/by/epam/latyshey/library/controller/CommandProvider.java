package by.epam.latyshey.library.controller;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.controller.command.CommandName;
import by.epam.latyshey.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class CommandProvider {

  private final Map<String, Command> repository = new HashMap<>();

  CommandProvider() {
    repository.put(CommandName.SIGN_IN, new SignIn());
    repository.put(CommandName.REGISTRATION_EMPLOYEE, new RegistrationEmployee());
    repository.put(CommandName.REGISTRATION_CUSTOMER, new RegistrationCustomer());
    repository.put(CommandName.SHOW_USER_SQL, new ShowUserSQL());
    repository.put(CommandName.ADD_BOOK, new AddBook());
    repository.put(CommandName.READ_BOOK, new ReadBook());
    repository.put(CommandName.SHOW_ALL_BOOK, new ShowAllBook());
    repository.put(CommandName.ADD_BOOK_TO_CUSTOMER, new AddBookToCustomer());
    repository.put(CommandName.SHOW_CUSTOMER_BOOKS, new ShowCustomersBooks());
    repository.put(CommandName.RETURN_BOOK, new ReturnBook());
    repository.put(CommandName.SHOW_CUSTOMERS_HISTORIES, new ShowHistories());
    repository.put(CommandName.SHOW_CUSTOMERS, new ShowCustomers());
    repository.put(CommandName.SERIALIZE_SAVE, new SerializeSave());
    repository.put(CommandName.SERIALIZE_LOAD, new SerializeLoad());
  }

  Command getCommand(String commandName) {

    String capsCommandName = null;
    Command command = null;

    capsCommandName = commandName.toUpperCase(Locale.ROOT);
    command = repository.get(capsCommandName);
    return command;
  }

}
