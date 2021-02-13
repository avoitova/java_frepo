package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;


public class GroupDeletionTests extends Testbase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
