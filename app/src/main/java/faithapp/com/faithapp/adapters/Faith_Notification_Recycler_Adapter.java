package faithapp.com.faithapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Notification_Card_View_item_click;
import faithapp.com.faithapp.model.Notifiaction_Card_Details;

/**
 * Created by rimon on 19/4/16.
 */
public class Faith_Notification_Recycler_Adapter extends RecyclerView.Adapter<Faith_Notification_Recycler_Adapter.ChurchEventsViewHolder>{

    List<Notifiaction_Card_Details> church_events;
    private Activity activity;

    public Faith_Notification_Recycler_Adapter(Activity activity,List<Notifiaction_Card_Details> church_events){
        this.activity = activity;
        this.church_events = church_events;
    }

    @Override
    public ChurchEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_notification_card_view, parent, false);
        ChurchEventsViewHolder pvh = new ChurchEventsViewHolder(v);

        return pvh;
    }

    private int getColorCode(){
        int[] color = {
                R.color._1,
                R.color._2,
                R.color._3,
                R.color._4,
                R.color._5,
                R.color._6,
                R.color._7,
                R.color._8,
                R.color._9,
                R.color._10,
                R.color._11,
                R.color._12

        };
        int min = 0,max = color.length;
        Random r = new Random();
        int i= r.nextInt(max - min);
        return color[i];
    }

    @Override
    public void onBindViewHolder(ChurchEventsViewHolder holder, int position) {
        holder.notification_card_view_title.setText(church_events.get(position).Title);
        int int_color = getColorCode();
        holder.faith_notification_card_view_Ima.setColorFilter(ContextCompat.getColor(activity, int_color), PorterDuff.Mode.MULTIPLY);
        holder.notification_card_view_title.setBackgroundColor(ContextCompat.getColor(activity, int_color));
        holder.notification_card_view_post_time.setText(church_events.get(position).Time);
        holder.notification_card_view_sub_heading.setText(church_events.get(position).SubHeading);
        holder.notification_card_view_description.setText(church_events.get(position).Description);
        holder.faith_notification_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Faith_Notification_Card_View_item_click.class);
//                Log.e("Video",VIDEO_ID);
                //intent.putExtra("videoData", videoData);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return church_events.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public static class ChurchEventsViewHolder extends RecyclerView.ViewHolder {

        CardView faith_notification_card_view;
        TextView notification_card_view_title;
        TextView notification_card_view_post_time;
        TextView notification_card_view_sub_heading;
        TextView notification_card_view_description;
        ImageView faith_notification_card_view_Ima;

        ChurchEventsViewHolder(View itemView) {
            super(itemView);
            faith_notification_card_view = (CardView) itemView.findViewById(R.id.faith_notification_card_view);
            notification_card_view_title = (TextView) itemView.findViewById(R.id.notification_card_view_title);
            notification_card_view_post_time =(TextView)  itemView.findViewById(R.id.notification_card_view_post_time);
            notification_card_view_sub_heading = (TextView) itemView.findViewById(R.id.notification_card_view_sub_heading);
            notification_card_view_description = (TextView) itemView.findViewById(R.id.notification_card_view_description);
            faith_notification_card_view_Ima = (ImageView) itemView.findViewById(R.id.faith_notification_card_view_Ima);
        }
    }

}