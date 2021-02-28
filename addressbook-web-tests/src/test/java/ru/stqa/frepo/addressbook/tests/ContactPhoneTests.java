package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends Testbase{

  @Test
  public void testContactPhones(){
    app.goTo().goToHomePage();
    ContactData contact = app.getContactHelper().getContactSet().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }
  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
