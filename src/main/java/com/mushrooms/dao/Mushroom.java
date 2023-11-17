package com.mushrooms.dao;

public class Mushroom {

    private int id;
    private String latinName;
    private String englishName;
    private String color;
    private int edible;
    private int poisonous;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEdible() {
        return edible;
    }

    public void setEdible(int edible) {
        this.edible = edible;
    }

    public int getPoisonous() {
        return poisonous;
    }

    public void setPoisonous(int poisonous) {
        this.poisonous = poisonous;
    }
}
