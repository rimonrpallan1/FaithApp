package faithapp.com.faithapp.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

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

import faithapp.com.faithapp.Common.Config;
import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Activity_Notification;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import faithapp.com.faithapp.adapters.Faith_Landing_Recycler_Adapter;
import faithapp.com.faithapp.model.Landing_Card_Details;

/**
 * Created by Rimon on 4/16/2016.
 *
 *
 * <p>
 * This is <b>Faith_Landing_Fragment</b> Fragment class.
 * Where User can see his FaithApp landing.
 * <p>
 * </p>
 */

public class Faith_Landing_Fragment extends Fragment {

   // Toolbar toolbar_faith_landing;
    Toolbar toolbar;
    RecyclerView faith_landing_fragment_recycleView;
    Faith_Landing_Recycler_Adapter adapter;
    List<Landing_Card_Details> church_detail;
    String photoId;
    private Activity activity;
    String Api = Integer.toString(R.string.allChurches_Api);
    Firebase ref;

    public Faith_Landing_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.faith_landing_fragment, container, false);
        activity = getActivity();
        Firebase.setAndroidContext(getContext());
        //initializeData();

        setHasOptionsMenu(true);

        ref  = new Firebase(Config.FIREBASE_URL);
        String allchurch = ref+"/AllChurch";
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                            //Getting the data from snapshot
//
//
//                        }
                System.out.println("Snapshot of data base" + snapshot.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


                // toolbar_faith_landing= (Toolbar) container.findViewById(R.id.toolbar_faith_landing);
       /* tvDiscover= (TextView)container.findViewById(R.id.TVDiscover);
        tvSlash= (TextView)container.findViewById(R.id.TVSlash);
        tvChat= (TextView)container.findViewById(R.id.TVChat);*/
       /* toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
                faith_landing_fragment_recycleView = (RecyclerView) rootView.findViewById(R.id.faith_landing_fragment_recycleView);
//        adapter = new Faith_Landing_Recycler_Adapter(activity,church_detail);
//        faith_landing_fragment_recycleView.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        faith_landing_fragment_recycleView.setLayoutManager(llm);
//        faith_landing_fragment_recycleView.setAdapter(adapter);
        new GetMethodDemo().execute();

        return rootView;
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
                url = new URL(Api);

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
            ArrayList<Landing_Card_Details> church_details = new ArrayList<>();

            Log.e("Response", "" + server_response);
            try {
                JSONObject allchurchs = new JSONObject(server_response);
                JSONArray data = allchurchs.getJSONArray("data");
                for (int i = 0; i<data.length(); i++){
                    JSONObject church = data.getJSONObject(i);
                    //System.out.println(church);
                    church_details.add(new Landing_Card_Details(church.optString("_id"), church.optString("name"),
                            church.optString("description"), church.optString("image"), church.optString("followers")+" Followers", true));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            adapter = new Faith_Landing_Recycler_Adapter(activity,church_details);
            faith_landing_fragment_recycleView.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            faith_landing_fragment_recycleView.setLayoutManager(llm);
            faith_landing_fragment_recycleView.setAdapter(adapter);
            System.out.println(church_details);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        faith_landing_fragment_recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                // TODO Auto-generated method stub
                if (newState < 1) {

                }else{

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // TODO Auto-generated method stub
                if (dy>0) {

                } else {

                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    /**
     * This method creates an ArrayList that has Church model class objects
     */
    private void initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        photoId =Integer.toString(R.drawable.profile_picture);
        church_detail = new ArrayList<>();
        church_detail.add(new Landing_Card_Details("1","St. Carmael Church","We at Yellow Carmel Church be like that new handboning method will save your life one day",photoId,"300 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("2","Holy Family Parish","We at Holy Family Parish be like that new handboning method will save your life one day",photoId,"800 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("3","Mount Zion Church","We at Mount Zion Church be like that new handboning method will save your life one day",photoId,"450 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("4","St. Joseph Church","We at St. Joseph Church be like that new handboning method will save your life one day",photoId,"1045000 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("5", "St. Peter Basilica", "We at St. Peter Basilica be like that new handboning method will save your life one day", photoId, "4738 FOLLOWERS", true));

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


