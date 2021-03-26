package ru.stqa.frepo.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(ApplicationManager app) {
    super(app);
  }


  public void goToManageMenu(){
    click(By.xpath("//a[@class='manage-menu-link']"));
  }

  public void goToManageUsers() {
    click(By.linkText("Manage Users"));
  }
  public void resetPassword(){
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void selectUserToUpdatePassword(String username){
    click(By.linkText(username));
  }
}
