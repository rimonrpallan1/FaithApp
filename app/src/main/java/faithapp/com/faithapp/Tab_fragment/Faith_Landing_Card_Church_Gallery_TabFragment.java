package faithapp.com.faithapp.Tab_fragment;


import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;


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
import faithapp.com.faithapp.adapters.Faith_Gallary_Images_Recycler_Adapter;
import faithapp.com.faithapp.custom.FlowLayout;
import faithapp.com.faithapp.model.Gallery_Card_Details;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_Gallery_TabFragment extends Fragment {

    FlowLayout faith_landing_card_church_gallery_tabfragment_image_grid;
    ScrollView landing_card_view_item_click_gallery_grid_scroll_view;
    List<Gallery_Card_Details> church_gallary_pics;
    Faith_Gallary_Images_Recycler_Adapter adapter;
    RecyclerView faith_activity_video_play_grid_recycleView;
    private GridLayoutManager lLayout;

    int count = 0;


    public int valueInDP_height = 0,valueInDP_width = 0;
    public int YOURVALUE = 150;
    int height = 0;
    int width = 0 ;

    private Activity activity;


    public Faith_Landing_Card_Church_Gallery_TabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_landing_card_church_gallery_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_Gallery_TabFragment");
        valueInDP_height= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen._50, getResources().getDisplayMetrics());
        valueInDP_width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen._250, getResources().getDisplayMetrics());
        //faith_landing_card_church_gallery_tabfragment_image_grid = (FlowLayout) rootView.findViewById(R.id.faith_landing_card_church_gallery_tabfragment_image_grid);
        landing_card_view_item_click_gallery_grid_scroll_view = (ScrollView)  rootView.findViewById(R.id.landing_card_view_item_click_gallery_grid_scroll_view);
    /*    for(int x=0;x<15;x++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(R.drawable.ic_church_aulter);
            LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(300, 300);
            image.setLayoutParams(vp);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setMaxHeight(50);
            image.setMaxWidth(50);
            faith_landing_card_church_gallery_tabfragment_image_grid.addView(image);

        }*/

        activity = getActivity();
        church_gallary_pics= initializeData();
        lLayout = new GridLayoutManager(getContext(), 3);

        faith_activity_video_play_grid_recycleView = (RecyclerView)rootView.findViewById(R.id.faith_activity_video_play_grid_recycleView);
        adapter = new Faith_Gallary_Images_Recycler_Adapter(activity,church_gallary_pics);
        faith_activity_video_play_grid_recycleView.setHasFixedSize(true);
        //LinearLayoutManager llm = new LinearLayoutManager(getContext());
        faith_activity_video_play_grid_recycleView.setLayoutManager(lLayout);
        faith_activity_video_play_grid_recycleView.setAdapter(adapter);



                landing_card_view_item_click_gallery_grid_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        Faith_Landing_Card_View_item_click activity = (Faith_Landing_Card_View_item_click) getActivity();
                        if (count != 0) {
                            if (activity.landing_card_view_item_click_expandable_text_view_linearlayout.getVisibility() == View.VISIBLE) {
                                activity.landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.GONE);


//                                AnimatorSet set = new AnimatorSet();
//                                set.playTogether(
//                                        ObjectAnimator.ofFloat(activity.landing_card_view_item_click_pic_btn_linear_layout, "scaleX", 1.0f, 0.9f)
//                                                .setDuration(2000),
//                                        ObjectAnimator.ofFloat(activity.landing_card_view_item_click_pic_btn_linear_layout, "scaleY", 1.0f, 0.9f)
//                                                .setDuration(2000),
//                                        ObjectAnimator.ofObject(activity.landing_card_view_item_click_pic_btn_linear_layout, "backgroundColor", new ArgbEvaluator(),
//                                            /*Red*/0xFFFFFF, /*Blue*/0xFFFFFF)
//                                                .setDuration(2000)
//                                );
//                                set.start();
//
//                                height = activity.landing_card_view_item_click_pic_btn_linear_layout_height_actual_hight;
//                                width = activity.landing_card_view_item_click_pic_btn_linear_layout_width_actual_width;
//                                activity.landing_card_view_item_click_pic_btn_linear_layout.getLayoutParams().height = (activity.landing_card_view_item_click_pic_btn_linear_layout_height_actual_hight) * 9 / 10;
//                                activity.landing_card_view_item_click_pic_btn_linear_layout.getLayoutParams().width = (activity.landing_card_view_item_click_pic_btn_linear_layout_width_actual_width) * 9 / 10;
//                                activity.landing_card_view_item_click_pic_btn_linear_layout.requestLayout();
//                                activity.landing_card_view_item_click_pic_btn_linear_layout.invalidate();
//                                System.out.println("landing_card_view_item_click_pic_btn_linear_layout_height " + height);
//                                System.out.println("landing_card_view_item_click_pic_btn_linear_layout_width  " + width);
                            }
                        }
                        count++;
                    }
                });



        return rootView;
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
    private List<Gallery_Card_Details> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        church_gallary_pics = new ArrayList<>();
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_0)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_1)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_2)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_3)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_4)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_7)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.one)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.two)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.three)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.four)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.five)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.six)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.seven)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.eight)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_0)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_1)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_2)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_3)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_4)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.sample_7)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.one)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.two)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.three)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.four)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.five)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.six)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.seven)));
        church_gallary_pics.add(new Gallery_Card_Details(String.valueOf(R.drawable.eight)));
        return church_gallary_pics;
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