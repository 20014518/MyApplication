package sg.edu.rp.c346.id20014518.myapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Hobby implements Serializable {

    private int id;
    private String title;
    private String description;
    private int stars;

    public Hobby(int id, String title, String description, int stars) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Hobby setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Hobby setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Hobby setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Hobby setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "*";
        }
        return title + "\n" + description + "\n" + starsString;
    }
}
