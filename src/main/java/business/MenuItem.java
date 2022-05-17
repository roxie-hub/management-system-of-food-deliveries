package business;

import java.io.Serializable;
import java.util.Objects;

public class MenuItem implements Comparable<MenuItem>, Serializable {
    private String name;
    private int price;
    private int calories;
    private int fat;
    private int protein;
    private int sodium;
    private float rating;

    public MenuItem(String name,float rating, int calories, int protein, int fat, int sodium,int price) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.sodium = sodium;
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getSodium() {
        return sodium;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public int compareTo(MenuItem o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
