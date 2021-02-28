package ru.stqa.frepo.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.frepo.addressbook.model.ContactData;
import ru.stqa.frepo.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(){
  click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation){
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectAllContacts() {
    click(By.id("MassCB"));
  }


  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification(int index) {
    List<WebElement> elements = wd.findElements(By.xpath("//img[@alt='Edit']"));
    elements.get(index).click();
  }
  public void initContactModificationById(int id) {
    int index = id;
    WebElement element = wd.findElement(By.xpath("//input[@id='" + id +"']/../.."));
    element.findElement(By.xpath(".//img[@alt='Edit']")).click();
  }

  public void deleteContact(int index) {
    selectContact(index);
    deleteSelectedContact();
  }
  public void deleteContact(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
  }
  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));

  }
  public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactPage();
  }


  public void modifyContact(ContactData contact, int index) {
    initContactModification(index);
    fillContactForm(contact,false);
    submitContactModification();
    returnToContactPage();
  }
  public void modifyContactById(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact,false);
    submitContactModification();
    returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> rows = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr"));
    for(int i = 1; i < rows.size(); ++i){
      //String lastname = rows.get(i).findElement(By.xpath(".//td[position()=2]")).getText();
      //String firstname = rows.get(i).findElement(By.xpath(".//td[position()=3]")).getText();
      // or the same but via API

      List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      contacts.add(new ContactData().withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
  private Contacts contactCache = null;

  public Contacts getContactSet() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr"));
    for(int i = 1; i < rows.size(); ++i){
      //String lastname = rows.get(i).findElement(By.xpath(".//td[position()=2]")).getText();
      //String firstname = rows.get(i).findElement(By.xpath(".//td[position()=3]")).getText();
      // or the same but via API

      List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String[] phones = cells.get(5).getText().split("\n");
      int n = phones.length;
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}
