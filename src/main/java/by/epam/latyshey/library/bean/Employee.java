package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.IEmployee;
import java.io.Serializable;

public class Employee extends User implements IEmployee, Serializable {

  private static int numberOfInstance = 0;
  public Employee(String userName, String name, String pass, int age) {
    super(userName, name, pass, age);
    numberOfInstance++;
  }

  public static int getNumberOfInstance() {
    return numberOfInstance;
  }

  @Override
  public String toString() {
    return "EMPLOYEE," + super.toString();
  }

  @Override
  public String getUserName() {
    return super.getUserName();
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public String getPass() {
    return super.getPass();
  }

  @Override
  public int getAge() {
    return super.getAge();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

}
