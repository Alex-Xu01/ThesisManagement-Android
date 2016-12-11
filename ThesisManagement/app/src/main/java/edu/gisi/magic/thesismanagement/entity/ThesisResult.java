package edu.gisi.magic.thesismanagement.entity;

/**
 * Created by AlexXu on 2016/12/11.
 */

public class ThesisResult {

    private int id;

    private String name;

    private int activity;

    private int size;

    private String house;

    private String images;

    private int price;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getActivity() {
        return this.activity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHouse() {
        return this.house;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return this.images;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}
