package by.epam.latyshey.library.controller;

import by.epam.latyshey.library.controller.command.Command;

public final class Controller {

  private final CommandProvider provider = new CommandProvider();
  private final char delimiter = ',';

  public String executeTask(String query) {
    String commandName;
    Command executionCommand;

    commandName = query.substring(0, query.indexOf(delimiter));
    executionCommand = provider.getCommand(commandName);

    String response;
    response = executionCommand.execute(query);
    return response;
  }

}
