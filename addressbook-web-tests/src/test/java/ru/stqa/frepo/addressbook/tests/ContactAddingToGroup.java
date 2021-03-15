package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactAddingToGroup extends Testbase{


  @Test
  public void testForContactAddingToGroup(){
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactForAddingToGroup = contacts.iterator().next();
    Groups groupsOfSelectedContactsBefore = contactForAddingToGroup.getGroups();

    boolean wasAdded = false;
    GroupData group = null;

    for (GroupData g: groups)
    {

      if (!groupsOfSelectedContactsBefore.contains(g))
      {
        app.goTo().goToHomePage();
        app.getContactHelper().addGroupToContact(contactForAddingToGroup,g);
        wasAdded = true;
        group = g;
        break;
      }
    }

    if (!wasAdded)
    {
      app.goTo().groupPage();
      GroupData g = new GroupData().withName("extragroup");
      app.group().create(g);
      app.goTo().goToHomePage();
      app.getContactHelper().addGroupToContact(contactForAddingToGroup,g);
      group = g;

    }

    Contacts contactsAfterAdding = app.db().contacts();

    for(ContactData c: contactsAfterAdding) {

      if (c.equals(contactForAddingToGroup)) {
        MatcherAssert.assertThat(c.getGroups().size(), equalTo(groupsOfSelectedContactsBefore.size()+1));
        MatcherAssert.assertThat(c.getGroups(),
                equalTo(contactForAddingToGroup.getGroups().withAdded(group)));
      }
    }
  }
}
