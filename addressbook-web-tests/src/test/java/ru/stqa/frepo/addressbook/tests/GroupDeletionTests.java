package ru.stqa.frepo.addressbook.tests;

import javafx.scene.effect.SepiaTone;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;

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
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(),before.size() - 1);
    before.remove(deletedGroup);
    /*for (int i = 0; i < after.size(); i++){
      Assert.assertEquals(before.get(i),after.get(i));
    }*/
    Assert.assertEquals(before,after);
  }




}
