package ru.stqa.frepo.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.frepo.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class Testbase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php.bak","config_inc.php");
    app.stop();
  }

  public void skipIfNotFixed(int IssueId) throws RemoteException, ServiceException, MalformedURLException {
    if(IsIssueOpen(IssueId)){
      throw new SkipException("Ignored because of Issue " + IssueId);
    }
  }

  public boolean IsIssueOpen(int IssueId) throws RemoteException, ServiceException, MalformedURLException {
    boolean open = false;
    IssueData issueData = app.soap().issueInfo(IssueId);
    ObjectRef status = issueData.getStatus();
    String name = status.getName();
    if(!name.equals("closed")){
      open = true;
    }
    return open;
  }
}

