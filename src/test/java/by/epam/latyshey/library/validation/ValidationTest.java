package by.epam.latyshey.library.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidationTest {

  @Test
  public void userAgeValidation() {
    boolean response = Validation.usersAgeValidation("10");
    boolean expected = true;
    assertEquals(expected, response);

    response = Validation.usersAgeValidation("99");
    assertEquals(expected, response);

    response = Validation.usersAgeValidation("1");
    assertEquals(expected, response);

    expected = false;
    response = Validation.usersAgeValidation("02");
    assertEquals(expected, response);

    response = Validation.usersAgeValidation("0");
    assertEquals(expected, response);
    response = Validation.usersAgeValidation("");
    assertEquals(expected, response);
  }

  @Test
  public void usersUserNameValidation() {
    boolean response = Validation.usersUserNameValidation("Leon12");
    boolean expected = true;
    assertEquals(expected, response);

    response = Validation.usersUserNameValidation("Русский");
    assertEquals(expected, response);
    response = Validation.usersUserNameValidation("Р1б2Ard");
    assertEquals(expected, response);
    expected = false;
    response = Validation.usersUserNameValidation("p");
    assertEquals(expected, response);
    response = Validation.usersUserNameValidation("5Russian");
    assertEquals(expected, response);
    response = Validation.usersUserNameValidation("");
    assertEquals(expected, response);
    response = Validation.usersUserNameValidation("Russian@");
    assertEquals(expected, response);
  }
}