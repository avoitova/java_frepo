package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.GroupData;

public class GroupModoficationTests extends Testbase{
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
