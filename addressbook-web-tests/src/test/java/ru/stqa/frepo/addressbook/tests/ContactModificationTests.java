package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactModificationTests extends Testbase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData().withFirstname("Anna")
              .withLastname("Petrova").withCompany("Luxoft").withAddress("Kyiv, Radyshcheva str. 10/14")
              .withMobilePhone("0969365879").withEmail("apetrova@luxoft.com").withGroup("test1"),true);
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.getContactHelper().getContactSet();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Lena").withLastname("Mukha");
    app.getContactHelper().modifyContactById(contact);
    Contacts after = app.getContactHelper().getContactSet();
    MatcherAssert.assertThat(after.size(), equalTo(before.size()));
    MatcherAssert.assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }


}
