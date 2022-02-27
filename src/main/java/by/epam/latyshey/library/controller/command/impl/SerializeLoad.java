package by.epam.latyshey.library.controller.command.impl;

import by.epam.latyshey.library.controller.command.Command;
import by.epam.latyshey.library.service.SerializeService;
import by.epam.latyshey.library.service.exception.ServiceException;
import by.epam.latyshey.library.service.factory.ServiceFactory;

public class SerializeLoad implements Command {

  @Override
  public String execute(String query) {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SerializeService serializeService = serviceFactory.getSerializeService();
    String response = null;
    try {
      response = serializeService.load();
    }
    catch (ServiceException e){
      //log4j System.out.println(e.getMessage());
      response = "";
    }

    return response;
  }
}
