package faithapp.com.faithapp.adapters;

import android.app.Activity;
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
import android.widget.TextView;

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

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.model.Landing_Single_Card_Details;
import faithapp.com.faithapp.model.Notifiaction_Card_Details;

/**
 * Created by rimon on 19/4/16.
 */
public class Faith_Fragment_Notification_Recycler_Adapter extends RecyclerView.Adapter<Faith_Fragment_Notification_Recycler_Adapter.ChurchEventsViewHolder>{

    List<Notifiaction_Card_Details> church_events;
    private Activity activity;
    Landing_Single_Card_Details SingleChurch;
    List<Landing_Single_Card_Details.Notification> SingleChurch_notification_list = new ArrayList<>();
    public static String card_view_id_ = "id";

    public Faith_Fragment_Notification_Recycler_Adapter(Activity activity, List<Notifiaction_Card_Details> church_events){
        this.activity = activity;
        this.church_events = church_events;
        new GetMethodDemo().execute();
    }

    @Override
    public ChurchEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faith_notification_fragment_card_view, parent, false);
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
        try{
            if(SingleChurch_notification_list.size()>0) {
                holder.notification_card_view_title.setText(church_events.get(position).Title);
                int int_color = getColorCode();
                holder.faith_notification_card_view_Ima.setColorFilter(ContextCompat.getColor(activity, int_color), PorterDuff.Mode.MULTIPLY);
                holder.notification_card_view_title.setBackgroundColor(ContextCompat.getColor(activity, int_color));
                holder.notification_card_view_sub_heading.setText(SingleChurch_notification_list.get(position).title);
                holder.notification_card_view_description.setText(SingleChurch_notification_list.get(position).message);
                holder.notification_card_view_post_time.setText(SingleChurch_notification_list.get(position).time);

            }
        }catch(Exception e){

        }

//        holder.faith_notification_card_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(activity, Faith_Notification_Card_View_item_click.class);
////                Log.e("Video",VIDEO_ID);
//                //intent.putExtra("videoData", videoData);
//                activity.startActivity(intent);
//            }
//        });
    }

    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {
            Log.e("CatalogClient", "Fragment------------");

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                ///   url = new URL(strings[0]);
                url = new URL("http://192.168.0.66:3000/api/user/getfavourite/"+ Faith_Landing_Card_View_item_click.church_id);
                System.out.println("http://192.168.0.66:3000/api/user/getfavourite/" + Faith_Landing_Card_View_item_click.church_id);

                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.e("CatalogClient-- Fragment", server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return server_response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            Log.i("Response", "" + server_response);
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
                    //ArrayList <Landing_Single_Card_Details.Notification> single_church_notificationArray = new ArrayList<>();
                    for(int j=0; j<notifiaction.length(); j++){
                        JSONObject notice_inner = notifiaction.getJSONObject(j);
                        Landing_Single_Card_Details.Notification SingleChurch_notification = new Landing_Single_Card_Details.Notification(notice_inner.optString("title"),notice_inner.optString("message"), notice_inner.optString("time"));
                        System.out.println("time"+notice_inner.optString("time"));
                        SingleChurch_notification_list.add(SingleChurch_notification);
                    }
                    SingleChurch.notification_array = SingleChurch_notification_list;

                    notifyDataSetChanged();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e("Response", "" + server_response);


        }
    }

// Converting InputStream to String

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
        return SingleChurch_notification_list.size();
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