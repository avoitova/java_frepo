package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactDeletingFromGroup extends Testbase {

  @Test
  public void testForDeletionContactFromGroup(){
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();

    Contacts contacts = app.db().contacts();
    ContactData contactForDeletingFromGroup = contacts.iterator().next();
    Groups groupsOfSelectedContactsBefore = contactForDeletingFromGroup.getGroups();

    if (groupsOfSelectedContactsBefore.size()==0) {
      app.goTo().goToHomePage();
      app.getContactHelper().addGroupToContact(contactForDeletingFromGroup,group);
      app.goTo().goToHomePage();

      Contacts contactsAfterAdding = app.db().contacts();
      for(ContactData c: contactsAfterAdding) {
        if (c.equals(contactForDeletingFromGroup)) {
          groupsOfSelectedContactsBefore = c.getGroups();
        }
      }
      app.getContactHelper().deleteGroupFromContact(contactForDeletingFromGroup,group);
    }else{
      app.getContactHelper().deleteGroupFromContact(contactForDeletingFromGroup,group);
      app.goTo().goToHomePage();
    }

    Contacts contactsAfterDeleting = app.db().contacts();

    for(ContactData c: contactsAfterDeleting) {

      if (c.equals(contactForDeletingFromGroup)) {
        MatcherAssert.assertThat(c.getGroups().size(), equalTo(groupsOfSelectedContactsBefore.size()-1));
        MatcherAssert.assertThat(c.getGroups(),
                equalTo(contactForDeletingFromGroup.getGroups().withOut(group)));
      }
    }
  }
}
