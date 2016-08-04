package faithapp.com.faithapp.activities;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.adapters.Faith_Landing_Card_Church_PagerAdapter;
import faithapp.com.faithapp.adapters.Faith_Landing_Recycler_Adapter;
import faithapp.com.faithapp.custom.FlowLayout;
import faithapp.com.faithapp.model.Landing_Card_Details;
import faithapp.com.faithapp.model.Landing_Single_Card_Details;
import me.biubiubiu.justifytext.library.JustifyTextView;

/**
 * Created by rimon on 20/4/16.
 */
public class Faith_Landing_Card_View_item_click extends AppCompatActivity {

    private Toolbar toolbar;
    public FlowLayout image_grid;
    public FloatingActionButton landing_card_view_item_click_call_floating_btn, landing_card_view_item_click_prayer_request_floating_btn;
    public Button landing_card_view_item_click_follow_btn;
    public ImageButton landing_card_view_item_click_expand_text_view_img_btn, landing_card_view_item_click_expand_text_view_img_btn_;
    public ImageView landing_card_view_item_click_church_icon_btn;
    public LinearLayout landing_card_view_item_click_expandable_grid_linearlayout, landing_card_view_item_click_expandable_text_view_linearlayout;
    public LinearLayout landing_card_view_item_click_pic_btn_linear_layout;

    TextView card_view_title, card_view_hidden_title;
    JustifyTextView card_view_description, card_view_hidden_description;

    Animation slide_down;
    Animation slide_up;


    Boolean isVisible = true;
    public static String card_view_title_ = "name";
    public static String church_id = "id";

   public String phoneno_call;



    public int landing_card_view_item_click_pic_btn_linear_layout_height_actual_hight = 0;
    public int landing_card_view_item_click_pic_btn_linear_layout_width_actual_width = 0;

    public FrameLayout landing_card_view_item_click_expandable_text_view_hidden_frame_layout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public Faith_Landing_Card_View_item_click() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faith_landing_card_view_item_click);

        Intent intent = getIntent();
        String _card_view_title = intent.getStringExtra("card_view_title");
        if (!_card_view_title.isEmpty()) {
            card_view_title_ = _card_view_title;
        }

        String _card_view_id = intent.getStringExtra("card_view_id");
        if (!_card_view_id.isEmpty()) {
            church_id = _card_view_id;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        landing_card_view_item_click_call_floating_btn = (FloatingActionButton) findViewById(R.id.landing_card_view_item_click_call_floating_btn);
        landing_card_view_item_click_prayer_request_floating_btn = (FloatingActionButton) findViewById(R.id.landing_card_view_item_click_prayer_request_floating_btn);
        landing_card_view_item_click_follow_btn = (Button) findViewById(R.id.landing_card_view_item_click_follow_btn);
        landing_card_view_item_click_expand_text_view_img_btn = (ImageButton) findViewById(R.id.landing_card_view_item_click_expand_text_view_img_btn);
        landing_card_view_item_click_church_icon_btn = (ImageView) findViewById(R.id.landing_card_view_item_click_church_icon_btn);
        landing_card_view_item_click_expandable_grid_linearlayout = (LinearLayout) findViewById(R.id.landing_card_view_item_click_expandable_grid_linearlayout);
        landing_card_view_item_click_expandable_text_view_linearlayout = (LinearLayout) findViewById(R.id.landing_card_view_item_click_expandable_text_view_linearlayout);
        landing_card_view_item_click_pic_btn_linear_layout = (LinearLayout) findViewById(R.id.landing_card_view_item_click_pic_btn_linear_layout);
        landing_card_view_item_click_expandable_text_view_hidden_frame_layout = (FrameLayout) findViewById(R.id.landing_card_view_item_click_expandable_text_view_hidden_frame_layout);
        landing_card_view_item_click_expand_text_view_img_btn_ = (ImageButton) findViewById(R.id.landing_card_view_item_click_expand_text_view_img_btn_);
        card_view_hidden_title = (TextView) findViewById(R.id.card_view_hidden_title);
        card_view_title = (TextView) findViewById(R.id.card_view_title);
        card_view_description = (JustifyTextView) findViewById(R.id.card_view_description);
        card_view_hidden_description = (JustifyTextView) findViewById(R.id.card_view_hidden_description);

        // Animation for the landing_toolbar_search
        card_view_title.setText(card_view_title_);
        card_view_hidden_title.setText(card_view_title_);

        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ViewTreeObserver vto = landing_card_view_item_click_pic_btn_linear_layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                landing_card_view_item_click_pic_btn_linear_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                landing_card_view_item_click_pic_btn_linear_layout_width_actual_width = landing_card_view_item_click_pic_btn_linear_layout.getMeasuredWidth();
                landing_card_view_item_click_pic_btn_linear_layout_height_actual_hight = landing_card_view_item_click_pic_btn_linear_layout.getMeasuredHeight();

            }
        });


                /*------------------------- three dot visible button ---------------------*/

        landing_card_view_item_click_expand_text_view_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible) {
                    landing_card_view_item_click_expandable_grid_linearlayout.setVisibility(View.GONE);
                    landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.GONE);
                    landing_card_view_item_click_expandable_text_view_hidden_frame_layout.setVisibility(View.VISIBLE);

               /*     slide_down.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    landing_card_view_item_click_expandable_grid_linearlayout.setAnimation(slide_down);
                    slide_up.start();*/
                    isVisible = false;
                } else {
                    landing_card_view_item_click_expandable_grid_linearlayout.setVisibility(View.VISIBLE);
                    isVisible = true;
                }
            }
        });

        /*------------------------- three dot hidden button ---------------------*/

        landing_card_view_item_click_expand_text_view_img_btn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                landing_card_view_item_click_expandable_text_view_hidden_frame_layout.setVisibility(View.GONE);
                landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.VISIBLE);
                landing_card_view_item_click_expandable_grid_linearlayout.setVisibility(View.VISIBLE);


            }
        });

        /*------------------------- img button click  ---------------------*/

        landing_card_view_item_click_church_icon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (landing_card_view_item_click_expandable_text_view_linearlayout.getVisibility() == View.GONE && landing_card_view_item_click_expandable_text_view_hidden_frame_layout.getVisibility() == View.GONE) {

                    landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.VISIBLE);

                  /*  new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },500);*/

//                    System.out.println("landing_card_view_item_click_pic_btn_linear_layout_height -------- img click " + landing_card_view_item_click_pic_btn_linear_layout.getHeight());
//                    System.out.println("landing_card_view_item_click_pic_btn_linear_layout_width  -------- img click" + landing_card_view_item_click_pic_btn_linear_layout.getWidth());
//                    landing_card_view_item_click_pic_btn_linear_layout.getLayoutParams().height = landing_card_view_item_click_pic_btn_linear_layout_height_actual_hight;
//                    landing_card_view_item_click_pic_btn_linear_layout.getLayoutParams().width = landing_card_view_item_click_pic_btn_linear_layout_width_actual_width;
//                    landing_card_view_item_click_pic_btn_linear_layout.requestLayout();
//                    landing_card_view_item_click_pic_btn_linear_layout.invalidate();

                    //---------------------- Animation for the Church Image Layout -------------------------------------
//
//                    AnimatorSet set = new AnimatorSet();
//                    set.playTogether(
//                            ObjectAnimator.ofFloat(landing_card_view_item_click_pic_btn_linear_layout, "scaleX", 0.9f, 1.0f)
//                                    .setDuration(1000),
//                            ObjectAnimator.ofFloat(landing_card_view_item_click_pic_btn_linear_layout, "scaleY", 0.9f, 1.0f)
//                                    .setDuration(1000),
//                            ObjectAnimator.ofObject(landing_card_view_item_click_pic_btn_linear_layout, "backgroundColor", new ArgbEvaluator(),
//                                    0xFFFFFF, 0xFFFFFF)
//                                    .setDuration(1000)
//                    );
//                    set.start();

                    //---------------------- Animation for the Grid View Layout -------------------------------------

               /*     slide_up.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            System.out.println("landing_card_view_item_click_expandable_grid_linearlayout.setAnimation(slide_up);");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    landing_card_view_item_click_expandable_grid_linearlayout.setAnimation(slide_up);*/


                }
            }
        });



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_crop_original_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_play_arrow_black_18dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_radio));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notifications_black_18dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_location_on_black_18dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Faith_Landing_Card_Church_PagerAdapter adapter = new Faith_Landing_Card_Church_PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), church_id);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("please print ");

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        new GetMethodDemo().execute();
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
                    Picasso.with(getApplicationContext()).load(SingleChurch.photoURL).into(landing_card_view_item_click_church_icon_btn);
                    card_view_title.setText(SingleChurch.Title);
                    card_view_description.setText(SingleChurch.Description);
                    card_view_hidden_description.setText(SingleChurch.Description);

                    phoneno_call = SingleChurch.Phone;

                    landing_card_view_item_click_call_floating_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try{
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+ phoneno_call));
                                startActivity(callIntent);
                            }catch (Exception e) {
                                // no activity to handle intent. show error dialog/toast whatever
                            }

                        }
                    });


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faith_landing_card_view_item_click_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.faith_landing_card_view_item_click_menu_radio_icon:

                return true;
            case R.id.faith_landing_card_view_item_click_menu_radio_notification_icon:
                Toast.makeText(getApplicationContext(), "Notification Fragment Selected", Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(Faith_Landing_Card_View_item_click.this, Faith_Activity_Notification.class);
                startActivity(activity);
                return true;
            case R.id.faith_landing_card_view_item_click_menu_radio_play_icon:
                Toast.makeText(getApplicationContext(), "Radio Fragment Selected", Toast.LENGTH_SHORT).show();
                Intent activity2 = new Intent(Faith_Landing_Card_View_item_click.this, Faith_Activity_Radio.class);
                startActivity(activity2);
                getSupportActionBar().setTitle("");
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        v.startAnimation(anim);
    }


    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();
    }


}