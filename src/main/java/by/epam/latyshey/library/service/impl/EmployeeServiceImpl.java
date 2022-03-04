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
    StringBuilder response = new StringBuilder();
    for (IUser customer : users) {
      if (customer instanceof Customer) {
        response.append("Клиент:").append(customer.getName()).append(" возраст:")
            .append(customer.getAge()).append(".\n");
      }
    }
    return response.toString();
  }

  @Override
  public String showHistory() {
    ArrayList<ICustomerHistory> history = historyDAO.readHistory();
    StringBuilder response = new StringBuilder();
    int i = 1;
    history.sort(new HistoriesComparator());
    for (ICustomerHistory h : history) {
      response.append("----------------------------------------------------------------\n")
          .append("[Пользователь: ").append(i++).append("]\n")
          .append(h.toString()).append("\n")
          .append("----------------------------------------------------------------\n");
    }
    return response.toString();
  }

  @Override
  public void registration(IEmployee employee) {
    userDAO.registration(employee);
  }
}
