package faithapp.com.faithapp.adapters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.model.Landing_FilterSearch_Rites;

/**
 * Created by rimon on 29/4/16.
 */
public class Faith_Landing_Filter_Rites_Recycle_view  extends RecyclerView.Adapter<Faith_Landing_Filter_Rites_Recycle_view.RitesDetailViewHolder> {

    List<Landing_FilterSearch_Rites> Rites_detail;
    private Activity activity;
    Boolean isVisible = true;

    public Faith_Landing_Filter_Rites_Recycle_view(Activity activity, List<Landing_FilterSearch_Rites> Rites_detail) {
        this.activity = activity;
        this.Rites_detail = Rites_detail;
    }

    @Override
    public RitesDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_landing_search_view_rites_card, parent, false);
        RitesDetailViewHolder pvh = new RitesDetailViewHolder(v);
        return pvh;
    }

    private int getColorCode() {
        int[] color = {
                R.color._5
        };
        int min = 0, max = color.length;
        Random r = new Random();
        int i = r.nextInt(max - min);
        return color[i];
    }


    @Override
    public void onBindViewHolder(final RitesDetailViewHolder holder, final int position) {
        holder.landing_search_filter_rites_card_text.setText(Rites_detail.get(position).Title);
        //holder.landing_search_filter_rites_card_text_outer_img_.setColorFilter(ContextCompat.getColor(activity, getColorCode()), PorterDuff.Mode.MULTIPLY);
        // here we set the click for the card_view


    }

    @Override
    public int getItemCount() {
        return Rites_detail.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * ViewHolder class which holds Initialisation to all views and buttons.
     */

    public static class RitesDetailViewHolder extends RecyclerView.ViewHolder {

        TextView landing_search_filter_rites_card_text;
        ImageView landing_search_filter_rites_card_text_outer_img_;

        RitesDetailViewHolder(View itemView) {
            super(itemView);
            landing_search_filter_rites_card_text = (TextView) itemView.findViewById(R.id.landing_search_filter_rites_card_text);
            landing_search_filter_rites_card_text_outer_img_ = (ImageView) itemView.findViewById(R.id.landing_search_filter_rites_card_text_outer_img_);
        }
    }
}