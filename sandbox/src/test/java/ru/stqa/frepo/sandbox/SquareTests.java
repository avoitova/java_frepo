package ru.stqa.frepo.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.frepo.sandbox.Square;


public class SquareTests {

  @Test
  public void testArea(){
    Square s = new Square(5);
      Assert.assertEquals(s.area(),20);
    }
  }

