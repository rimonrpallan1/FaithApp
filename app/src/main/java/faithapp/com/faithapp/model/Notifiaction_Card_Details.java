package faithapp.com.faithapp.model;

/**
 * Created by rimon on 19/4/16.
 */
public class Notifiaction_Card_Details {

    public String Title;
    public String Time;
    public String SubHeading;
    public String Description;

    public Notifiaction_Card_Details(String title, String time, String subHeading, String description) {
        Title = title;
        Time = time;
        SubHeading = subHeading;
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSubHeading() {
        return SubHeading;
    }

    public void setSubHeading(String subHeading) {
        SubHeading = subHeading;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
