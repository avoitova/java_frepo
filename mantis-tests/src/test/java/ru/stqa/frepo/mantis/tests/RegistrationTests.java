package ru.stqa.frepo.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.frepo.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends Testbase{

  //@BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException, ServiceException {
    skipIfNotFixed(0000001);
    long now = System.currentTimeMillis();
   //String email = String.format("user%s@localhost.localdomain", now);
    String email = String.format("user%s@localhost", now);
    String user = String.format("user%s", now);
    String password = "password";
    app.james().createUser(user,password);
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages= app.james().waitForMail(user,password,60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user, password));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


 // @AfterMethod
  public void stopMailServer(){
    app.mail().stop();
  }
}
