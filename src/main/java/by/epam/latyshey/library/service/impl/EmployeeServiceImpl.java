package by.epam.latyshey.library.service.impl;

import by.epam.latyshey.library.bean.Customer;
import by.epam.latyshey.library.comparator.HistoriesComparator;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.bean.interfaces.IEmployee;
import by.epam.latyshey.library.bean.interfaces.IUser;
import by.epam.latyshey.library.dao.HistoryDAO;
import by.epam.latyshey.library.dao.UserDAO;
import by.epam.latyshey.library.dao.factory.DAOFactory;
import by.epam.latyshey.library.service.EmployeeService;

import java.util.ArrayList;

public class EmployeeServiceImpl implements EmployeeService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  UserDAO userDAO = daoFactory.getUserDAO();
  HistoryDAO historyDAO = daoFactory.getHistoryDAO();

  @Override
  public String showCustomers() {
    ArrayList<IUser> users = userDAO.showSQLUser();
    String response = "";
    for (IUser customer : users) {
        if (customer instanceof Customer) {
            response += "Клиент:" + customer.getName() + " возраст:" + customer.getAge() + ".\n";
        }
    }
    return response;
  }

  @Override
  public String showHistory() {
    ArrayList<ICustomerHistory> history = historyDAO.readHistory();
    String response = "";
    int i = 1;
    history.sort(new HistoriesComparator()); //todo починить сортировку
    for (ICustomerHistory h : history) {
      response +=
          "----------------------------------------------------------------\n"
        + "[Пользователь: " + (i++) + "]\n"
        + h.toString() + "\n"
        + "----------------------------------------------------------------\n";
    }
    return response;
  }

  @Override
  public void registration(IEmployee employee) {
    userDAO.registration(employee);
  }
}
