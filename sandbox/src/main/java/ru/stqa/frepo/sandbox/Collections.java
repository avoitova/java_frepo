package ru.stqa.frepo.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String[] args){
    String[] langs = {"java", "C#", "Python","PHP"};
    List<String> languages = Arrays.asList("java", "C#", "Python","PHP");

  /*  List<String>  languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");*/

    /*for (int i = 0; i < languages.size(); i++){
      System.out.println("I want to know " + languages.get(i));
    }*/
    for (String l: languages){
      System.out.println("I want to know " + l);
    }

  }
}
