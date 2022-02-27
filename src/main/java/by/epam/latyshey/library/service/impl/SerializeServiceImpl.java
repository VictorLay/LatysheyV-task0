package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.serialization.SaveState;
import by.epam.latyshey.library.service.SerializeService;

import by.epam.latyshey.library.service.exception.ServiceException;
import java.io.*;
import java.nio.file.Path;

public class SerializeServiceImpl implements SerializeService {
  private final String PATH = "src\\main\\java\\by\\epam\\latyshey\\library\\serialization\\save.ser";
  DAOFactory daoFactory = DAOFactory.getInstance();
  BookDAO bookDAO = daoFactory.getBookDAO();
  HistoryDAO historyDAO = daoFactory.getHistoryDAO();
  UserDAO userDAO = daoFactory.getUserDAO();

  @Override
  public String save() {
    SaveState state = new SaveState(userDAO.showSQLUser(), historyDAO.readHistory(),
        bookDAO.showAllBooks());
    try {
      Path path = Path.of(PATH)
          .toAbsolutePath();
      FileOutputStream outputStream = new FileOutputStream(path.toString());
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(state);
      objectOutputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String load() throws ServiceException{
    String response;
    Path path = Path.of(PATH)
        .toAbsolutePath();
    try {
      FileInputStream fileInputStream = new FileInputStream(path.toString());
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      SaveState loadingState = (SaveState) objectInputStream.readObject();
      userDAO.setUsers(loadingState.getUsers());
      historyDAO.setHistory(loadingState.getHistory());
      bookDAO.setBooks(loadingState.getBooks());
      response = "Подключены файлы сериаллизации.";
    } catch (IOException |ClassNotFoundException e) {
      throw new ServiceException(e);
    }
    return response;
  }
}
