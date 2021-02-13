package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

public class ContactModificationTests extends Testbase{

  @Test
  public void testContactModification(){
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Petrova","Luxoft","Kyiv, Radyshcheva str. 10/14", "0960000000", "apetrova@luxoft.com",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();

  }
}
