package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;

public class GroupModoficationTests extends Testbase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName(app.getProperties().getProperty("GroupCreationName")));
    }
  }

  @Test
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
            .withName(app.getProperties().getProperty("GroupModificationName"))
            .withHeader(app.getProperties().getProperty("GroupModificationHeader"))
            .withFooter(app.getProperties().getProperty("GroupModificationFooter"));
    app.group().modify(group);
    MatcherAssert.assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedGroup).withAdded(group)));
  }


}
