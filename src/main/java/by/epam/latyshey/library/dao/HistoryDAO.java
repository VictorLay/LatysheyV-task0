package by.epam.latyshey.library.dao;


import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import java.util.ArrayList;

public interface HistoryDAO {

  void createHistory(ICustomerHistory history);

  ICustomerHistory findCustomerHistory(ICustomer customer);

  void updateCustomerHistory(ICustomerHistory history);

  ArrayList<ICustomerHistory> readHistory();

  void setHistories(ArrayList<ICustomerHistory> histories);
}
