package ru.stqa.frepo.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("195.39.243.72");
    System.out.println(geoIP);
    Assert.assertTrue(geoIP.contains("UA"));
  }

}

