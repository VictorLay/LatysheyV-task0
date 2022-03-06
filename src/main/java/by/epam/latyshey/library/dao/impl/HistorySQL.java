package by.epam.latyshey.library.dao.impl;

import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.dao.HistoryDAO;

import by.epam.latyshey.library.data.source.DataSourceCollection;
import java.util.ArrayList;

public class HistorySQL implements HistoryDAO {

  private ArrayList<ICustomerHistory> customerHistories;

  public HistorySQL() {
    DataSourceCollection dataSource = DataSourceCollection.getInstance();
    customerHistories = dataSource.getHistories();
  }

  @Override
  public void setHistories(ArrayList<ICustomerHistory> histories) {
    customerHistories = histories;
  }

  @Override
  public ArrayList<ICustomerHistory> readHistory() {
    return customerHistories;
  }

  @Override
  public void createHistory(ICustomerHistory history) {
    this.customerHistories.add(history);
  }

  @Override
  public ICustomerHistory findCustomerHistory(ICustomer customer) {
    for (ICustomerHistory history : customerHistories) {
      if (history.getCustomer().equals(customer)) {
        return history;
      }
    }
    return null;
  }

  @Override
  public void updateCustomerHistory(ICustomerHistory newHistory) {
    for (int i = customerHistories.size() - 1; i >= 0; i--) {
      if (customerHistories.get(i).getCustomer().equals(newHistory.getCustomer())) {
        customerHistories.set(i, newHistory);
      }
    }

  }


}
