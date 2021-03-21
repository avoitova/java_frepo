package ru.stqa.frepo.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends Testbase{

  @Test
  public void testRegistration(){
    app.registration().start("user", "user1@localhost.localdomain");
  }
}
