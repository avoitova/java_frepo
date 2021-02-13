package ru.stqa.frepo.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.frepo.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(){
  click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation){
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation(){
    click(By.linkText("add new"));
  }

  public void returnToContactPage(){
    click(By.linkText("home page"));
  }

  public void selectContact() {
    click(By.xpath("//td/input"));
  }

  public void selectAllContacts() {
    click(By.id("MassCB"));
  }


  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }
}
