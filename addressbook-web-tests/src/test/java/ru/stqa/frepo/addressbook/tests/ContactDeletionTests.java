package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactDeletionTests extends Testbase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().goToHomePage();
    if (app.getContactHelper().getContactSet().size() == 0){
      app.getContactHelper().createContact(new ContactData().withFirstname("Anna")
              .withLastname("Petrova").withCompany("Luxoft").withAddress("Kyiv, Radyshcheva str. 10/14")
              .withMobilePhone("0969365879").withEmail("apetrova@luxoft.com").withGroup("test1"),true);

    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.getContactHelper().getContactSet();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().deleteContact(deletedContact);
    app.alertClose();
    Contacts after = app.getContactHelper().getContactSet();
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
