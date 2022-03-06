package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.dao.BookDAO;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.serialization.SaveState;
import by.epam.latyshey.library.service.SerializeService;

import by.epam.latyshey.library.service.exception.ServiceException;
import java.io.*;
import java.nio.file.Files;

public class SerializeServiceImpl implements SerializeService {

  private final File file = new File(
      "src/main/java/by/epam/latyshey/library/serialization/save.ser");
  DAOFactory daoFactory = DAOFactory.getInstance();
  BookDAO bookDAO = daoFactory.getBookDAO();
  HistoryDAO historyDAO = daoFactory.getHistoryDAO();
  UserDAO userDAO = daoFactory.getUserDAO();

  @Override
  public String save() {
    SaveState state = new SaveState(userDAO.showSQLUser(), historyDAO.readHistory(),
        bookDAO.showAllBooks());
    try( ObjectOutputStream objectOutputStream = new ObjectOutputStream( new FileOutputStream(file))) {
      objectOutputStream.writeObject(state);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String load() throws ServiceException {
    String response;
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
      response = "Подключены файлы сериаллизации:";
      SaveState loadingState = (SaveState) objectInputStream.readObject();
      if (Files.notExists(file.toPath())){
        userDAO.setUsers(loadingState.getUsers());
        response += "\nUsersDAO";
      }
      historyDAO.setHistories(loadingState.getHistory());
      response += "\nHistoryDAO";
      bookDAO.setBooks(loadingState.getBooks());
      response += "\nBookDAO";

    } catch (IOException | ClassNotFoundException e) {
      throw new ServiceException(e);
    }
    return response;
  }
}
