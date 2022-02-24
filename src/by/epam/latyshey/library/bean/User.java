package by.epam.latyshey.library.bean;

import by.epam.latyshey.library.bean.interfaces.IUser;
import java.io.Serializable;

public abstract class User implements IUser, Serializable {

  private String userName;
  private String name;
  private String pass;
  private int age;

  public User(String userName, String name, String pass, int age) {
    this.userName = userName;
    this.name = name;
    this.pass = pass;
    this.age = age;
  }

  @Override
  public String toString() {
    return "userName=," + userName + ',' + "name=," + name + ',' + "pass=," + pass + ',' + "age=,"
        + age + ",";
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPass() {
    return pass;
  }

  @Override
  public int getAge() {
    return age;
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
