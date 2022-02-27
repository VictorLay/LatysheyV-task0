package by.epam.latyshey.library.service;

import by.epam.latyshey.library.service.exception.ServiceException;

public interface SerializeService {

  String save();

  String load() throws ServiceException;
}
