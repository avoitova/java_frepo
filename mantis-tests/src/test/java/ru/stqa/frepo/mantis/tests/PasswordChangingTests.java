package ru.stqa.frepo.mantis.tests;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.frepo.mantis.appmanager.ApplicationManager;
import ru.stqa.frepo.mantis.model.MailMessage;
import ru.stqa.frepo.mantis.model.UserData;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;


public class PasswordChangingTests extends Testbase{


  private SessionFactory sessionFactory;

  @BeforeClass
  public void setUp() throws Exception {

    super.setUp();

    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testPasswordChanging() throws IOException, MessagingException, ServiceException {
    //skipIfNotFixed(795);
    WebDriver wd = app.getDriver();
    app.login().start("administrator","root");
    app.navigate().goToManageMenu();
    app.navigate().goToManageUsers();

    List<UserData> result = dbUserList();
    result.remove(0);
    UserData userForPasswordChange = result.iterator().next();
    //UserData userForPasswordChange = result.get(result.size()-3);
    String username = userForPasswordChange.getUsername();
    String password = "password";
    String email = userForPasswordChange.getEmail();
    app.navigate().selectUserToUpdatePassword(username);
    app.navigate().resetPassword();

    if (app.james().doesUserexist(username)){
      List<MailMessage> mailMessages= app.james().waitForMail(username
              ,password,150000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      Assert.assertTrue(app.newSession().login(username, password));
    }

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  public List<UserData> dbUserList(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData" ).list();
    for ( UserData user :  result ) {
      System.out.println( user);
    }
    session.getTransaction().commit();
    session.close();
    return result;
  }

}
