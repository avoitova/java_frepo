package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;

public class GroupCreationTests extends Testbase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

  }

}
