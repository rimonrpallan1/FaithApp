package faithapp.com.faithapp.Tab_fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.adapters.Faith_Fragment_Notification_Recycler_Adapter;
import faithapp.com.faithapp.adapters.Faith_Landing_Recycler_Adapter;
import faithapp.com.faithapp.adapters.Faith_Notification_Recycler_Adapter;
import faithapp.com.faithapp.model.Landing_Card_Details;
import faithapp.com.faithapp.model.Landing_Single_Card_Details;
import faithapp.com.faithapp.model.Notifiaction_Card_Details;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_Notification_TabFragment extends Fragment {

    // Toolbar toolbar_faith_landing;
    RecyclerView faith_activity_notification_recycleView;
    Faith_Fragment_Notification_Recycler_Adapter adapter;
    List<Notifiaction_Card_Details> church_events;
    String photoId;
    private Activity activity;
    ScrollView landing_card_view_item_click_notification_grid_scroll_view;
    public String church_id ;

    public Faith_Landing_Card_Church_Notification_TabFragment(String church_id) {
        this.church_id = church_id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_landing_card_church_notification_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_Notification_TabFragment");
        activity = getActivity();
        initializeData();



        landing_card_view_item_click_notification_grid_scroll_view = (ScrollView) rootView.findViewById(R.id.landing_card_view_item_click_notification_grid_scroll_view);
        faith_activity_notification_recycleView = (RecyclerView)rootView.findViewById(R.id.faith_activity_notification_recycleView);
        adapter = new Faith_Fragment_Notification_Recycler_Adapter(activity,church_events);
        faith_activity_notification_recycleView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        faith_activity_notification_recycleView.setLayoutManager(llm);
        faith_activity_notification_recycleView.setAdapter(adapter);
        new GetMethodDemo().execute();


/*

                landing_card_view_item_click_notification_grid_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        Faith_Landing_Card_View_item_click activity = (Faith_Landing_Card_View_item_click) getActivity();
                        if(activity.landing_card_view_item_click_expandable_text_view_linearlayout.getVisibility()== View.VISIBLE)
                            activity.landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.GONE);

                    }
                });
*/
        return rootView;
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
                url = new URL("http://192.168.0.66:3000/api/church/"+church_id);

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
                    ArrayList <Landing_Single_Card_Details.Notification> single_church_notificationArray = new ArrayList<>();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        faith_activity_notification_recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                // TODO Auto-generated method stub
                if (newState < 1) {

                } else {

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // TODO Auto-generated method stub
                if (dy > 0) {

                } else {

                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private void initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        church_events = new ArrayList<>();
        church_events.add(new Notifiaction_Card_Details("St. Carmael Church","15 min","Novena to Infant of prague","We at Yellow Carmel Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("Holy Family Parish","15 min","Novena to Holy Family","We at Holy Family Parish be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("Mount Zion Church","15 min","Novena to Sacred Heart Of Jesus","We at Mount Zion Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("St. Joseph Church","15 min","Novena to St.Joseph","We at St. Joseph Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("St. Peter Basilica","15 min","Novena toSt. Peter","We at St. Peter Basilica be like that new handboning method will save your life one day"));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

}