package faithapp.com.faithapp.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import faithapp.com.faithapp.adapters.Faith_Notification_Recycler_Adapter;
import faithapp.com.faithapp.model.Notifiaction_Card_Details;

/**
 * Created by rimon on 19/4/16.
 */
public class Faith_Activity_Notification extends AppCompatActivity {

    // Toolbar toolbar_faith_landing;
    private  Toolbar toolbar;
    RecyclerView faith_activity_notification_recycleView;
    Faith_Notification_Recycler_Adapter adapter;
    List<Notifiaction_Card_Details> church_events;
    private Activity activity;

    public Faith_Activity_Notification() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faith_notification_fragment);
        activity = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Faith App");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        initializeData();

        faith_activity_notification_recycleView = (RecyclerView)findViewById(R.id.faith_activity_notification_recycleView);
        adapter = new Faith_Notification_Recycler_Adapter(activity,church_events);
        faith_activity_notification_recycleView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        faith_activity_notification_recycleView.setLayoutManager(llm);
        faith_activity_notification_recycleView.setAdapter(adapter);
        new GetMethodDemo().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faith_favourite_fragment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {


        }
        return super.onOptionsItemSelected(item);
    }

    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {
            Log.e("CatalogClient", "------------");

            URL url;
            HttpURLConnection urlConnection = null;

            try {
             ///   url = new URL(strings[0]);
                url = new URL("http://192.168.0.66:3000/api/allchurch");

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

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private void initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        //     church_events = new ArrayList<>();
        church_events.add(new Notifiaction_Card_Details("St. Carmael Church","15 min","Novena to Infant of prague","We at Yellow Carmel Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("Holy Family Parish","15 min","Novena to Holy Family","We at Holy Family Parish be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("Mount Zion Church","15 min","Novena to Sacred Heart Of Jesus","We at Mount Zion Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("St. Joseph Church","15 min","Novena to St.Joseph","We at St. Joseph Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("St. Peter Basilica","15 min","Novena toSt. Peter","We at St. Peter Basilica be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("St. Carmael Church","15 min","Novena to Infant of prague","We at Yellow Carmel Church be like that new handboning method will save your life one day"));
        church_events.add(new Notifiaction_Card_Details("Holy Family Parish","15 min","Novena to Holy Family","We at Holy Family Parish be like that new handboning method will save your life one day"));
    }
}


