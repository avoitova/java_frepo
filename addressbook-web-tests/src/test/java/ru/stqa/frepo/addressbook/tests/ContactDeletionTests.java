package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends Testbase{
  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContact();
    app.getGroupHelper().deleteSelectedContact();
    app.alertClose();
  }

  @Test
  public void testContactDeletionAll(){
    app.getContactHelper().selectAllContacts();
    app.getGroupHelper().deleteSelectedContact();
    app.alertClose();
  }

}
