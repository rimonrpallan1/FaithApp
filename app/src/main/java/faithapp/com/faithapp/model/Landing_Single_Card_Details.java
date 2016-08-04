package faithapp.com.faithapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rimon on 16/4/16.
 */
public class Landing_Single_Card_Details implements Serializable{

    public String ID;
    public String Title;
    public String Description;
    public String photoURL;
    public String address;
    public String Followers;
    public String Phone;
    public String latitude;
    public String longitude;
    public List<Notification> notification_array;

    public Landing_Single_Card_Details(String ID, String title, String description, String photoURL,String address, String followers, String phone) {
        this.ID = ID;
        Title = title;
        Description = description;
        this.photoURL = photoURL;
        this.address = address;
        Followers = followers;
        Phone = phone;
    }



    /**
     * <p>
     * This is <b>Jobs</b> class.
     * It is a model inner class which deals with the Jobs Details.
     * This class allows you to process the data from server or shared preference file.
     * </p>
     */

    public static class Notification implements Serializable {

        public String title;
        public String message;
        public String time;


        public Notification(String title, String message,String time) {
            this.title = title;
            this.message = message;
            this.time = time;
        }
    }




}
