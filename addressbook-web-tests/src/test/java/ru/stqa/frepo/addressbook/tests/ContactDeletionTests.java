package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactDeletionTests extends Testbase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size()==0){
      app.goTo().goToHomePage();
      app.getContactHelper().createContact(new ContactData()
              .withFirstname(app.getProperties().getProperty("ContactCreationFirstname"))
              .withLastname(app.getProperties().getProperty("ContactCreationLastname"))
              .withCompany(app.getProperties().getProperty("ContactCreationCompany"))
              .withAddress(app.getProperties().getProperty("ContactCreationAddress"))
              .withMobilePhone(app.getProperties().getProperty("ContactCreationMobile"))
              .withEmail(app.getProperties().getProperty("ContactCreationEmail"))
              .withGroup(app.getProperties().getProperty("ContactCreationGroup")),true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().goToHomePage();
    app.getContactHelper().deleteContact(deletedContact);
    app.alertClose();
    app.checkContactDeleted();
    Contacts after = app.db().contacts();
    MatcherAssert.assertThat(after.size(), equalTo(before.size() - 1));
    MatcherAssert.assertThat(after, equalTo(before.withOut(deletedContact)));
  }

  @Test(enabled = false)
  public void testContactDeletionAll(){
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData().withFirstname("Anna")
              .withLastname("Petrova").withCompany("Luxoft").withAddress("Kyiv, Radyshcheva str. 10/14")
              .withMobilePhone("0969365879").withEmail("apetrova@luxoft.com").withGroup("test1"),true);
    }
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().deleteSelectedContact();
    app.alertClose();
  }

}
