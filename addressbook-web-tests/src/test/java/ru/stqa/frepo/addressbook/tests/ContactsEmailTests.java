package ru.stqa.frepo.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsEmailTests extends Testbase{
  @Test
  public void testContactEmails(){
    app.goTo().goToHomePage();
    ContactData contact = app.getContactHelper().getContactSet().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactsEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }


  public static String cleaned(String email){
    return email.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
