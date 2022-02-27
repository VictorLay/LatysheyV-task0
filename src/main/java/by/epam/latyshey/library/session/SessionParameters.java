package by.epam.latyshey.library.session;


import by.epam.latyshey.library.bean.interfaces.IUser;

public class SessionParameters {
  public static IUser loggedInUser;

  public static void setLoggedInUser(IUser loggedInUser) {
    SessionParameters.loggedInUser = loggedInUser;
  }

  public static void removeLoggedInUser(IUser loggedInUser) {
    SessionParameters.loggedInUser = null;
  }

  public static IUser getLoggedInUser() {
    return loggedInUser;
  }


}
