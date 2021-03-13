package ru.stqa.frepo.addressbook.tests;

import javafx.scene.effect.SepiaTone;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;


public class GroupDeletionTests extends Testbase{


  @BeforeMethod
  public void ensurePreconditions()  {
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.getProperties().getProperty("GroupCreationName")));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    MatcherAssert.assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.db().groups();
    /*for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }*/
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedGroup)));
  }
}
