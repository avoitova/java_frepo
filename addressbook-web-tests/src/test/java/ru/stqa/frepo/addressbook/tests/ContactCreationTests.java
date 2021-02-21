package ru.stqa.frepo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends Testbase{

  @Test
  public void testContactCreation(){
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Anna", "Petrova",
            "Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879",
            "apetrova@luxoft.com", "test1");
    app.getContactHelper().createContact(contact,true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);
    before.add(contact);

    Comparator<? super ContactData> byLastname = new Comparator<ContactData>() {
      @Override
      public int compare(ContactData o1, ContactData o2) {

        return o1.getLastname().compareTo(o2.getLastname());
      }
    };
    before.sort(byLastname);
    after.sort(byLastname);
    Assert.assertEquals(before,after);
  }
}
