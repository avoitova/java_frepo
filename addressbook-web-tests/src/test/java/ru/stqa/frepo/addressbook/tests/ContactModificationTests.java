package ru.stqa.frepo.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends Testbase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Anna","Petrova",
              "Luxoft","Kyiv, Radyshcheva str. 10/14","0969365879",
              "apetrova@luxoft.com","test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData("Lena", "Petrova",
            null,null, null, null,null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
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
