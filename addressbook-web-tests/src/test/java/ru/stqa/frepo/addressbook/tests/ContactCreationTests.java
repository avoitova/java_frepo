package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends Testbase{

  @Test
  public void testContactCreation(){
    app.goTo().goToHomePage();
    Contacts before = app.getContactHelper().getContactSet();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("Anna")
            .withLastname("Petrova").withCompany("Luxoft").withAddress("Kyiv, Radyshcheva str. 10/14")
            .withMobilePhone("0969365879").withEmail("apetrova@luxoft.com").withPhoto(photo).withGroup("test1");
    app.getContactHelper().createContact(contact,true);
    Contacts after = app.getContactHelper().getContactSet();
    MatcherAssert.assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
