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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.model.Landing_Card_Details;

/**
 * Created by rimon on 16/4/16.
 */
public class Faith_Radio_Recycler_Adapter extends RecyclerView.Adapter<Faith_Radio_Recycler_Adapter.ChurchDetailViewHolder>{

    List<Landing_Card_Details> church_detail;
    private Activity activity;
    Boolean isVisible = true;

    public Faith_Radio_Recycler_Adapter(Activity activity, List<Landing_Card_Details> church_detail){
        this.activity = activity;
        this.church_detail = church_detail;
    }

    @Override
    public ChurchDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_landing_card_view, parent, false);
        ChurchDetailViewHolder pvh = new ChurchDetailViewHolder(v);
        return pvh;
    }

    private int getColorCode(){
        int[] color = {
                R.color._5
        };
        int min = 0,max = color.length;
        Random r = new Random();
        int i= r.nextInt(max - min);
        return color[i];
    }




    @Override
    public void onBindViewHolder(final ChurchDetailViewHolder holder,final int position) {
        holder.card_view_title.setText(church_detail.get(position).Title);
        holder.faith_landing_card_view_outer_img_.setColorFilter(ContextCompat.getColor(activity, getColorCode()), PorterDuff.Mode.MULTIPLY);
        holder.card_view_church_photo.setImageResource(Integer.parseInt(church_detail.get(position).photoURL));
        holder.card_view_description.setText(church_detail.get(position).Description);
        holder.card_view_church_followers_text.setText(church_detail.get(position).Followers);

        // here we set the click for the card_view
        holder.faith_card_view_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Faith_Landing_Card_View_item_click.class);
                intent.putExtra("card_view_title",church_detail.get(position).Title);
                activity.startActivity(intent);
            }
        });
        // here we change the color of favourite heart in card_view



    }

    @Override
    public int getItemCount() {
        return church_detail.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public static class ChurchDetailViewHolder extends RecyclerView.ViewHolder {

        CardView faith_card_view_card_view;
        CircleImageView card_view_church_photo;
        TextView card_view_title;
        TextView card_view_description;
        TextView card_view_church_followers_text;
        ImageView card_view_church_favourite_icon,faith_landing_card_view_outer_img_;
        LinearLayout card_view_church_heart_layout_icon;

        ChurchDetailViewHolder(View itemView) {
            super(itemView);
            faith_card_view_card_view = (CardView) itemView.findViewById(R.id.faith_card_view_card_view);
            card_view_church_photo = (CircleImageView) itemView.findViewById(R.id.card_view_church_photo);
            card_view_title = (TextView) itemView.findViewById(R.id.card_view_title);
            card_view_description = (TextView) itemView.findViewById(R.id.card_view_description);
            card_view_church_followers_text = (TextView) itemView.findViewById(R.id.card_view_church_followers_text);
            card_view_church_favourite_icon = (ImageView) itemView.findViewById(R.id.card_view_church_favourite_icon);
            card_view_church_heart_layout_icon =(LinearLayout) itemView.findViewById(R.id.card_view_church_heart_layout_icon);
            faith_landing_card_view_outer_img_ = (ImageView) itemView.findViewById(R.id.faith_landing_card_view_outer_img_);
        }
    }

}