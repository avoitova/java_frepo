package ru.stqa.frepo.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.frepo.addressbook.model.GroupData;
import ru.stqa.frepo.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;

public class GroupCreationTests extends Testbase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    MatcherAssert.assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().all();
    MatcherAssert.assertThat(after.size(), equalTo(before.size()+1));
   /* int max = 0;
    for (GroupData g: after){
      if (g.getId() > max){
        max = g.getId();
      }
    }*/
   /* Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    int max = after.stream().max(byId).get().getId();*/



    /*int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.setId(max);
    */
    ;
    MatcherAssert.assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    MatcherAssert.assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    MatcherAssert.assertThat(after, equalTo(before));
  }
}
