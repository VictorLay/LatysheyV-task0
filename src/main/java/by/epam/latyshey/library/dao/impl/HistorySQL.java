package by.epam.latyshey.library.dao.impl;

import by.epam.latyshey.library.bean.interfaces.ICustomer;
import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import by.epam.latyshey.library.dao.HistoryDAO;

import java.util.ArrayList;

public class HistorySQL implements HistoryDAO {

  private ArrayList<ICustomerHistory> historySQL;

  public HistorySQL() {
    historySQL = new ArrayList<>();
  }

  @Override
  public void setHistory(ArrayList<ICustomerHistory> histories) {
    historySQL = histories;
  }

  @Override
  public ArrayList<ICustomerHistory> readHistory() {
    return historySQL;
  }

  @Override
  public void createHistory(ICustomerHistory history) {
    this.historySQL.add(history);
  }

  @Override
  public ICustomerHistory findCustomerHistory(ICustomer customer) {
    for (ICustomerHistory history : historySQL) {
      if (history.getCustomer() == customer) {
        return history;
      }
    }
    return null;
  }

  @Override
  public void updateCustomerHistory(ICustomerHistory newHistory, ICustomer customer) {
    for (int i = historySQL.size() - 1; i >= 0; i--) {
      if (historySQL.get(i).getCustomer() == customer) {
        historySQL.set(i, newHistory);
      }
    }
  }


}
