package ru.stqa.frepo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends Testbase{
  @Test(enabled = false)
  public void testContactDeletion(){
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Anna","Petrova",
              "Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879",
              "apetrova@luxoft.com","test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.alertClose();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() - 1);
    before.remove(before.size() -1);
    Assert.assertEquals(before,after);

  }

  @Test(enabled = false)
  public void testContactDeletionAll(){
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Anna","Petrova",
              "Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879",
              "apetrova@luxoft.com","test1"),true);
    }
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().deleteSelectedContact();
    app.alertClose();
  }

}
