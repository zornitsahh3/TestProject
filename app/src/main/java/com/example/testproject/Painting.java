package com.example.testproject;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "paintings")
public class Painting {
    // Indicates whether the painting is marked as favorite by the user
    public boolean isFavorite = false;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String paintingName;
    private String authorName;
    // --- Represents the image-we reference the image by the id ---
    private int paintingImage;
    // --- Constructor ---
    // Creates a new Painting object with a name, author, and image
    public Painting(String paintingName, String authorName,int paintingImage) {
        this.paintingName = paintingName;
        this.paintingImage = paintingImage;
        this.authorName=authorName;
    }
    //Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPaintingName() {
        return paintingName;
    }
    public int getPaintingImage() {
        return paintingImage;
    }
    public String getAuthorName() {
        return authorName;
    }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }
    public void setPaintingImage(int paintingImage) {
        this.paintingImage = paintingImage;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
