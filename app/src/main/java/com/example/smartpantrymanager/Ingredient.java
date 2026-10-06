package com.example.smartpantrymanager;

public class Ingredient {
    private final long id;
    private final String name;
    private final double quantity;
    private final String unit;
    private final String expiryDate;

    public Ingredient(long id, String name, double quantity, String unit, String expiryDate) {
        this.id = id; this.name = name; this.quantity = quantity; this.unit = unit; this.expiryDate = expiryDate;
    }
    public Ingredient(String name, double quantity, String unit, String expiryDate) { this(0, name, quantity, unit, expiryDate); }
    public long getId() { return id; }
    public String getName() { return name; }
    public double getQuantity() { return quantity; }
    public String getUnit() { return unit; }
    public String getExpiryDate() { return expiryDate; }
}
