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

public class ContactModificationTests extends Testbase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().goToHomePage();
    if (app.getContactHelper().getContactSet().size() == 0){
      app.getContactHelper().createContact(new ContactData()
              .withFirstname(app.getProperties().getProperty("ContactCreationFirstname"))
              .withLastname(app.getProperties().getProperty("ContactCreationLastname"))
              .withCompany(app.getProperties().getProperty("ContactCreationCompany"))
              .withAddress(app.getProperties().getProperty("ContactCreationAddress"))
              .withMobilePhone(app.getProperties().getProperty("ContactCreationMobile"))
              .withEmail(app.getProperties().getProperty("ContactCreationEmail"))
              .withGroup(app.getProperties().getProperty("ContactCreationGroup")),true);

      /*.withFirstname("Anna").withLastname("Petrova").withCompany("Luxoft").withAddress("Kyiv, Radyshcheva str. 10/14")
              .withMobilePhone("0969365879").withEmail("apetrova@luxoft.com").withGroup("test1"),true);*/
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.getContactHelper().getContactSet();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname(app.getProperties().getProperty("ContactModificationFirstname"))
            .withLastname(app.getProperties().getProperty("ContactModificationLastname"));
    app.getContactHelper().modifyContactById(contact);
    Contacts after = app.getContactHelper().getContactSet();
    MatcherAssert.assertThat(after.size(), equalTo(before.size()));
    MatcherAssert.assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }


}
