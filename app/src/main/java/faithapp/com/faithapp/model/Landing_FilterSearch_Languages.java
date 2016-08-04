package faithapp.com.faithapp.model;

import java.io.Serializable;

/**
 * Created by rimon on 16/4/16.
 */
public class Landing_FilterSearch_Languages implements Serializable {

    public String ID;
    public String Title;


    public Landing_FilterSearch_Languages(String ID, String title) {
        this.ID = ID;
        Title = title;
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
}







