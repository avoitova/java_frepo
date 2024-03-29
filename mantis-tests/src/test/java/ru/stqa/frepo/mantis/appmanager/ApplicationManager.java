package ru.stqa.frepo.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;

public class ApplicationManager {

  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private LoginHelper loginhelper;
  private NavigationHelper navigationhelper;
  private SoapHelper soaphelper;
  private RestHelper resthelper;

  public Properties getProperties() {
    return properties;
  }

  private final Properties properties;
  private String browser;
  private WebDriver wd;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

 public void alertClose(){
    wd.switchTo().alert().accept();
 }

  public void stop() {
    if (wd!= null) {
      wd.quit();
    }
  }


  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public FtpHelper ftp(){
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }




  public WebDriver getDriver() {
    if (wd==null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)){
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)){
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public MailHelper mail(){
    if (mailHelper ==null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james(){
    if (jamesHelper == null ){
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }
  public LoginHelper login() {
    if (loginhelper == null){
      loginhelper = new LoginHelper(this);
    }
    return loginhelper;
  }
  public NavigationHelper navigate() {
    if (navigationhelper == null){
      navigationhelper = new NavigationHelper(this);
    }
    return navigationhelper;
  }

  public SoapHelper soap() {
    if (soaphelper == null){
      soaphelper = new SoapHelper(this);
    }
    return soaphelper;
  }

  public RestHelper rest() {
    if (resthelper == null){
      resthelper = new RestHelper(this);
    }
    return resthelper;
  }
}
