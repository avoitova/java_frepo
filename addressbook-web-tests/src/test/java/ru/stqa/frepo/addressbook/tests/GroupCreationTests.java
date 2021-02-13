package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;

public class GroupCreationTests extends Testbase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }
}
