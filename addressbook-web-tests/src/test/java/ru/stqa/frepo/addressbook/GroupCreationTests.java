package ru.stqa.frepo.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupCreationTests extends Testbase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
    wd.findElement(By.linkText("Logout")).click();
  }

}
