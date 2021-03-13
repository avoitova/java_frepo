package ru.stqa.frepo.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;


@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column (name = "id")
  private  int id = Integer.MAX_VALUE;

  @Expose
  @Column (name = "firstname")
  private  String firstname;

  @Expose
  @Column (name = "lastname")
  private  String lastname;

  @Expose
  @Column (name = "company")
  private  String company;

  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private  String address;

  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private  String mobile;

  @Expose
  @Type(type = "text")
  private  String email;

  @Expose
  @Type(type = "text")
  private  String email2;

  @Expose
  @Type(type = "text")
  private  String email3;

  public ContactData() {
  }

  public ContactData(ContactData other) {
    this.id = other.id;
    this.firstname = other.firstname;
    this.lastname = other.lastname;
    this.company = other.company;
    this.address = other.address;
    this.mobile = other.mobile;
    this.email = other.email;
    this.email2 = other.email2;
    this.email3 = other.email3;
    this.group = other.group;
    this.home = other.home;
    this.work = other.work;
    this.allPhones = other.allPhones;
    this.allEmails = other.allEmails;
    this.allAddress = other.allAddress;
    this.photo = other.photo;
  }

  @Expose
  @Transient
  private String group;

  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String home;

  @Expose
  @Column (name = "work")
  @Type(type = "text")
  private String work;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Transient
  private  String allEmails;

  @Expose
  @Transient
  private String allAddress;

  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;


  public File getPhoto() {
    if (photo != null && !photo.isEmpty()) {
      return new File(photo);
    }else {
      return null;
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withAllAddress(String allAddress) {
    this.allAddress = allAddress;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public int getId() { return id; }
  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCompany() {
    return company;
  }
  public String getAddress() {
    return address;
  }

  public String getMobilePhone() {
    return mobile;
  }
  public String getHomePhone() {
    return home;
  }
  public String getWorkPhone() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }
  public String getAllAddress() {
    return allAddress;
  }
  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return address != null ? address.equals(that.address) : that.address == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public String getAllEmails() {
    return allEmails;
  }

}
