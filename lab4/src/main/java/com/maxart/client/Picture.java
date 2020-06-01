package com.maxart.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Picture {

    private int id;
    private String name;
    private String author;
    private int year;
    private String material;
    private float height;
    private float width;

    public Picture() {
    }

    Picture(int id, String name, String author, int year, String material, float height, float width) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.material = material;
        this.height = height;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", material='" + material + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
