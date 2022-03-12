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

import by.epam.latyshey.library.view.menu.MenuName;
import java.util.ArrayList;

public class EmployeeServiceImpl implements EmployeeService {

  DAOFactory daoFactory = DAOFactory.getInstance();
  UserDAO userDAO = daoFactory.getUserDAO();
  HistoryDAO historyDAO = daoFactory.getHistoryDAO();

  @Override
  public String showCustomers() {

    StringBuilder response = new StringBuilder();
    for (IUser user : userDAO.showSQLUser()) {
      if (user instanceof Customer) {
        response.append("Клиент:").append(user.getName()).append(" возраст:").append(user.getAge())
            .append(".\n");
      }
    }
    return response.toString();
  }

  @Override
  public String showHistory() {
    ArrayList<ICustomerHistory> histories = historyDAO.readHistory();
    histories.sort(new HistoriesComparator());

    StringBuilder response = new StringBuilder();
    int i = 1;
    for (ICustomerHistory history : histories) {
      response.append("----------------------------------------------------------------\n")
          .append("[Пользователь: ").append(i++).append("]\n")
          .append(history.toString()).append("\n")
          .append("----------------------------------------------------------------\n");
    }
    return response.toString();
  }

  @Override
  public String registration(IEmployee employee) {
    userDAO.registration(employee);

    return MenuName.SUCCESS_REGISTRATION_VIEW + "," + employee;
  }
}
