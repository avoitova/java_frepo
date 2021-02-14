package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

public class ContactDeletionTests extends Testbase{
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Anna","Petrova",
              "Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879",
              "apetrova@luxoft.com","test1"),true);
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.alertClose();
  }

  @Test
  public void testContactDeletionAll(){
    app.getNavigationHelper().goToHomePage();
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
