package by.epam.latyshey.library.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {


  public static boolean usersAgeValidation(String age) {
    Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{0,1}$");
    Matcher matcher = pattern.matcher(age);
    return matcher.find();
  }
  public static boolean usersUserNameValidation(String userName) {
    Pattern pattern = Pattern.compile("^[A-Za-zА-Яа-я]{1}[A-Za-zА-Яа-я0-9]{5,14}");
    Matcher matcher = pattern.matcher(userName);
    return matcher.find();
  }
  public static boolean usersNameValidation(String name) {
    Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я]{1}[A-Za-zА-Яа-я\s]{5,20}");
    Matcher matcher = pattern.matcher(name);
    return matcher.find();
  }
  public static boolean usersPasswordValidation(String password) {
    Pattern pattern = Pattern.compile("\\A[^\\s]{6,}\\Z");
    Matcher matcher = pattern.matcher(password);
    return matcher.find();
  }
  public static boolean integerNumberInRangeValidation(int value, int min, int max) {
    return (min <= value) && (value <= max);
  }

  public static boolean isStringValueNumber(String value){
    Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{0,}$");
    Matcher matcher = pattern.matcher(value);
    return matcher.find();
  }
}