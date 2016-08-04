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
import faithapp.com.faithapp.model.Gallery_Card_Details;

/**
 * Created by rimon on 16/4/16.
 */
public class Faith_Gallary_Images_Recycler_Adapter extends RecyclerView.Adapter<Faith_Gallary_Images_Recycler_Adapter.ChurchDetailViewHolder>{

    List<Gallery_Card_Details> gallery_church_detail;
    private Activity activity;
    Boolean isVisible = true;

    public Faith_Gallary_Images_Recycler_Adapter(Activity activity, List<Gallery_Card_Details> gallery_church_detail){
        this.activity = activity;
        this.gallery_church_detail = gallery_church_detail;
    }

    @Override
    public ChurchDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_gallary_image_card_tofragment, parent, false);
        ChurchDetailViewHolder pvh = new ChurchDetailViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(final ChurchDetailViewHolder holder,final int position) {
        holder.faith_fragment_gallery_image_card_image.setImageResource(Integer.parseInt(gallery_church_detail.get(position).ID));

        // here we set the click for the card_view
       /* holder.faith_card_view_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Faith_Landing_Card_View_item_click.class);
                intent.putExtra("card_view_title",gallery_church_detail.get(position).Title);
                activity.startActivity(intent);
            }
        });*/
        // here we change the color of favourite heart in card_view

    }

    @Override
    public int getItemCount() {
        return gallery_church_detail.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public static class ChurchDetailViewHolder extends RecyclerView.ViewHolder {

        CardView faith_gallery_image_grid_card_view;
        ImageView faith_fragment_gallery_image_card_image;

        ChurchDetailViewHolder(View itemView) {
            super(itemView);
            faith_gallery_image_grid_card_view = (CardView) itemView.findViewById(R.id.faith_gallery_image_grid_card_view);
            faith_fragment_gallery_image_card_image = (ImageView) itemView.findViewById(R.id.faith_fragment_gallery_image_card_image);

        }
    }

}