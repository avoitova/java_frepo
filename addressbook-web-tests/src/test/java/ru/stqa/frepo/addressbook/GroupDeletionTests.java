package ru.stqa.frepo.addressbook;

import org.testng.annotations.*;


public class GroupDeletionTests extends Testbase{

  @Test
  public void testGroupDeletion() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
