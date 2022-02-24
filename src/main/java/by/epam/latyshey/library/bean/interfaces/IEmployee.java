package by.epam.latyshey.library.bean.interfaces;

public interface IEmployee extends IUser{

   String toString();

   String getUserName();

   String getName();

   String getPass();

   int getAge();

   int hashCode();

   boolean equals(Object obj);

}
