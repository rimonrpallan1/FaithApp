package faithapp.com.faithapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.model.Landing_Card_Details;
import faithapp.com.faithapp.model.Landing_Single_Card_Details;

/**
 * Created by rimon on 16/4/16.
 */
public class Faith_Fragment_Fravourate_Recycler_Adapter extends RecyclerView.Adapter<Faith_Fragment_Fravourate_Recycler_Adapter.ChurchDetailViewHolder>{

    List<Landing_Card_Details> church_detail;
    private Activity activity;
    Boolean isVisible = true;

    public Faith_Fragment_Fravourate_Recycler_Adapter(Activity activity, List<Landing_Card_Details> church_detail){
        this.activity = activity;
        this.church_detail = church_detail;
    }

    @Override
    public ChurchDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_landing_card_view, parent, false);
        ChurchDetailViewHolder pvh = new ChurchDetailViewHolder(v);
        new GetMethodDemo().execute();
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
        //holder.card_view_church_photo.setImageResource(Integer.parseInt(church_detail.get(position).photoURL));
        Picasso.with(activity).load(church_detail.get(position).photoURL).into(holder.card_view_church_photo);

        holder.card_view_description.setText(church_detail.get(position).Description);
        holder.card_view_church_followers_text.setText(church_detail.get(position).Followers);

        // here we set the click for the card_view
        holder.faith_card_view_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Faith_Landing_Card_View_item_click.class);
                intent.putExtra("card_view_title",church_detail.get(position).Title);
                intent.putExtra("card_view_id",church_detail.get(position).getID());
                activity.startActivity(intent);
            }
        });
        // here we change the color of favourite heart in card_view


        holder.card_view_church_heart_layout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible){
                    holder.card_view_church_favourite_icon.setBackgroundResource(R.drawable.icon_heart_red);
                    isVisible = false;
                }
                else
                {
                    holder.card_view_church_favourite_icon.setBackgroundResource(R.drawable.icon_heart);

                    isVisible = true;
                }
            }
        });

    }

    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {
            Log.e("CatalogClient", "Activity_cardView------------");

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                ///   url = new URL(strings[0]);
                url = new URL("http://192.168.0.66:3000/api/user/getfavourite/");

                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.e("CatalogClient", server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Landing_Single_Card_Details SingleChurch;

            Log.e("Response", "" + server_response);
            try {
                JSONObject allchurchs = new JSONObject(server_response);
                JSONArray data = allchurchs.getJSONArray("data");
                for (int i = 0; i<data.length(); i++){
                    JSONObject church = data.getJSONObject(i);
                    //System.out.println(church);
                    SingleChurch =new Landing_Single_Card_Details(church.optString("_id"), church.optString("name"),
                            church.optString("description"), church.optString("image"), church.optString("address"), church.optString("followers"),church.optString("phone"));
                    SingleChurch.latitude = church.getJSONObject("location").optString("latitude");
                    SingleChurch.longitude = church.getJSONObject("location").optString("longitude");
                    JSONArray notifiaction = church.getJSONArray("notification");
                    ArrayList<Landing_Single_Card_Details.Notification> single_church_notificationArray = new ArrayList<>();
                    for(int j=0; j<notifiaction.length(); j++){
                        JSONObject notice_inner = notifiaction.getJSONObject(j);
                        Landing_Single_Card_Details.Notification temp = new Landing_Single_Card_Details.Notification(notice_inner.optString("title"),notice_inner.optString("message"),notice_inner.optString("time"));
                        single_church_notificationArray.add(temp);
                    }

                    SingleChurch.notification_array = single_church_notificationArray;

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e("Response", "" + server_response);


        }
    }


    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
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