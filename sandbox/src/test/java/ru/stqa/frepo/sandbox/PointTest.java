package ru.stqa.frepo.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
  @Test
  public void testDistance() {
    Point p1 = new Point(2, 2);
    Point p2 = new Point(4, 6);
    Assert.assertEquals(p1.distance(p2), Math.sqrt(20));
  }

  @Test
  public void testDistanceZero(){
    Point p1 = new Point(2,2);
    Point p2 = new Point(2,2);
    Assert.assertEquals(p1.distance(p2),Math.sqrt(0));
  }

  @Test
  public void testDistanceNegativeValues() {
    Point p1 = new Point(-3, -6);
    Point p2 = new Point(3, 6);
    Assert.assertEquals(p1.distance(p2), Math.sqrt(180));
  }
}
