package by.epam.latyshey.library.comparator;

import by.epam.latyshey.library.bean.interfaces.ICustomerHistory;
import java.util.Comparator;

public class HistoriesComparator implements Comparator<ICustomerHistory> {

  @Override
  public int compare(ICustomerHistory o1, ICustomerHistory o2) {
    return o2.compareTo(o1);
  }
}
