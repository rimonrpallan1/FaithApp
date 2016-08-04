package faithapp.com.faithapp.Tab_fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.model.Landing_Single_Card_Details;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_MapLocation_TabFragment extends Fragment {


    MapView mMapView;
    private GoogleMap googleMap;

    private Activity activity;


     public double latitude;
     public double longitude;



    public Faith_Landing_Card_Church_MapLocation_TabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_landing_card_church_maplocation_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_MapLocation_TabFragment");
        activity = getActivity();

        new GetMethodDemo().execute();

        return rootView;
    }

/*
    Log.i("video onclick url", videoURI);
    if (videoURI.substring(0, 5).equals("https")) {
        System.out.println("Playing video from Server URL");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(videoURI), "video/mp4");
        startActivity(intent);
    } else {
        System.out.println("Playing video from Device");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File auxFile = new File(videoURI);
        intent.setDataAndType(Uri.fromFile(auxFile), "video/mp4");
        startActivity(intent);
    }
*/
public class GetMethodDemo extends AsyncTask<String , Void ,String> {
    String server_response;

    @Override
    protected String doInBackground(String... strings) {
        Log.e("CatalogClient", "Activity_cardView------------");

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            ///   url = new URL(strings[0]);
            url = new URL("http://192.168.0.66:3000/api/church/"+Faith_Landing_Card_View_item_click.church_id);

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
                latitude = Double.parseDouble(SingleChurch.latitude);
                longitude = Double.parseDouble(SingleChurch.longitude);

            }

            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();


            // latitude and longitude


            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(Faith_Landing_Card_View_item_click.card_view_title_);

            // Changing marker icon
            marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            // adding marker
            googleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude)).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

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