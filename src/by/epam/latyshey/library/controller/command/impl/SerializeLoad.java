package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.SerializeService;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class SerializeLoad implements Command {

  @Override
  public String execute(String query) {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SerializeService serializeService = serviceFactory.getSerializeService();

    String response = serializeService.load();

    return response;
  }
}
