package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

public class ContactCreationTests extends Testbase{

  @Test
  public void testContactCreation(){
    app.getContactHelper().createContact(new ContactData("Anna", "Petrova","Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879", "apetrova@luxoft.com", "test1"),true);
  }
}
