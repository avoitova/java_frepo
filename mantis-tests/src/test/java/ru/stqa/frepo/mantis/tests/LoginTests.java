package ru.stqa.frepo.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.frepo.mantis.appmanager.HttpSession;
import ru.stqa.frepo.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Set;

public class LoginTests extends Testbase {


  @Test
  public void testLogin() throws IOException, ServiceException {
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login("administrator","root"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));
  }



}
