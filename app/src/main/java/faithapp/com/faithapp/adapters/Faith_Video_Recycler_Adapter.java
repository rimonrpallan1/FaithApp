package faithapp.com.faithapp.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import faithapp.com.faithapp.R;
import faithapp.com.faithapp.model.Video_Card_Details;

/**
 * Created by rimon on 16/4/16.
 */
public class Faith_Video_Recycler_Adapter extends RecyclerView.Adapter<Faith_Video_Recycler_Adapter.ChurchDetailViewHolder>{

    List<Video_Card_Details> video_church_detail;
    private Activity activity;
    Boolean isVisible = true;

    public Faith_Video_Recycler_Adapter(Activity activity, List<Video_Card_Details> video_church_detail){
        this.activity = activity;
        this.video_church_detail = video_church_detail;
    }

    @Override
    public ChurchDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_video_card_tofragment, parent, false);
        ChurchDetailViewHolder pvh = new ChurchDetailViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(final ChurchDetailViewHolder holder,final int position) {
        holder.faith_fragment_video_card_image.setImageResource(Integer.parseInt(video_church_detail.get(position).ID));

        // here we set the click for the card_view
       /* holder.faith_card_view_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Faith_Landing_Card_View_item_click.class);
                intent.putExtra("card_view_title",video_church_detail.get(position).Title);
                activity.startActivity(intent);
            }
        });*/
        // here we change the color of favourite heart in card_view

    }

    @Override
    public int getItemCount() {
        return video_church_detail.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public static class ChurchDetailViewHolder extends RecyclerView.ViewHolder {

        CardView faith_video_play_grid_card_view;
        ImageView faith_fragment_video_card_image,faith_fragment_video_card_videoicon_center;

        ChurchDetailViewHolder(View itemView) {
            super(itemView);
            faith_video_play_grid_card_view = (CardView) itemView.findViewById(R.id.faith_video_play_grid_card_view);
            faith_fragment_video_card_image = (ImageView) itemView.findViewById(R.id.faith_fragment_video_card_image);
            faith_fragment_video_card_videoicon_center = (ImageView) itemView.findViewById(R.id.faith_fragment_video_card_videoicon_center);

        }
    }

}