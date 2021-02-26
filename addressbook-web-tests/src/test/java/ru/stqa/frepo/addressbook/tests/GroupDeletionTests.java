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
    Groups after = app.group().all();

    Assert.assertEquals(after.size(),before.size() - 1);
    /*for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }*/
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedGroup)));
  }




}
