package faithapp.com.faithapp.model;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by rimon on 16/4/16.
 */
public class Landing_Card_Details implements Serializable{

    public String ID;
    public String Title;
    public String Description;
    public String photoURL;
    public String Followers;
    public boolean favourited;

    public Landing_Card_Details(String ID, String title, String description, String photoURL, String followers, boolean favourited) {
        this.ID = ID;
        Title = title;
        Description = description;
        this.photoURL = photoURL;
        Followers = followers;
        this.favourited = favourited;
    }



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getFollowers() {
        return Followers;
    }

    public void setFollowers(String followers) {
        Followers = followers;
    }

    public boolean isFavourited() {
        return favourited;
    }

    public void setFavourited(boolean favourited) {
        this.favourited = favourited;
    }
}
