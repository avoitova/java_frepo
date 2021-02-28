package ru.stqa.frepo.addressbook.tests;

import javafx.scene.effect.SepiaTone;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;


public class GroupDeletionTests extends Testbase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    MatcherAssert.assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.group().all();
    /*for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }*/
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedGroup)));
  }




}
