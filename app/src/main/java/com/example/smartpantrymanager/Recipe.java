package com.example.smartpantrymanager;
import java.util.List;
public class Recipe {
 public static class RequiredIngredient { public final String name,unit; public final double quantity; public RequiredIngredient(String n,double q,String u){name=n;quantity=q;unit=u;} public String display(){return quantity+" "+unit+" "+name;} }
 private final long id; private final String name,method; private final List<RequiredIngredient> ingredients;
 public Recipe(long i,String n,List<RequiredIngredient> r,String m){id=i;name=n;ingredients=r;method=m;} public long getId(){return id;} public String getName(){return name;} public List<RequiredIngredient> getIngredients(){return ingredients;} public String getMethod(){return method;}
}
