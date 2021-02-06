package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.*;


public class GroupDeletionTests extends Testbase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
