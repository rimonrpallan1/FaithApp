package faithapp.com.faithapp.Tab_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.adapters.Faith_Video_Recycler_Adapter;
import faithapp.com.faithapp.model.Video_Card_Details;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_VideoPlay_TabFragment extends Fragment {


    ScrollView landing_card_view_item_click_videoplay_grid_scroll_view;

    RecyclerView faith_activity_video_play_grid_recycleView;
    Faith_Video_Recycler_Adapter adapter;
    List<Video_Card_Details> video_pic;
    ImageView faith_fragment_video_card_videoicon_center;
    private GridLayoutManager lLayout;
    String yourFilePath ;


    private Activity activity;

    public Faith_Landing_Card_Church_VideoPlay_TabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_landing_card_church_videoplay_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_VideoPlay_TabFragment");

        /*faith_landing_card_church_gallery_tabfragment_image_grid = (FlowLayout) rootView.findViewById(R.id.faith_landing_card_church_gallery_tabfragment_image_grid);
        landing_card_view_item_click_videoplay_grid_scroll_view = (ScrollView) rootView.findViewById(R.id.landing_card_view_item_click_videoplay_grid_scroll_view);

        for(int x=0;x<15;x++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(R.drawable.ic_church_aulter);
            LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(300, 300);
            image.setLayoutParams(vp);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setMaxHeight(50);
            image.setMaxWidth(50);
            faith_landing_card_church_gallery_tabfragment_image_grid.addView(image);
        }*/



           /*     landing_card_view_item_click_videoplay_grid_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        Faith_Landing_Card_View_item_click activity = (Faith_Landing_Card_View_item_click) getActivity();
                        if(activity.landing_card_view_item_click_expandable_text_view_linearlayout.getVisibility()== View.VISIBLE)
                            activity.landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.GONE);
                    }
                });
*/

         yourFilePath = getContext().getFilesDir() + "/" + "hello.txt";
        activity = getActivity();
        if(isExternalStorageReadable())
        {
         Log.e("ExternalStorage is readable","");
        }

        video_pic= initializeData();
        lLayout = new GridLayoutManager(getContext(), 3);
        faith_fragment_video_card_videoicon_center = (ImageView) rootView.findViewById(R.id.faith_fragment_video_card_videoicon_center);
        landing_card_view_item_click_videoplay_grid_scroll_view = (ScrollView) rootView.findViewById(R.id.landing_card_view_item_click_videoplay_grid_scroll_view);
        faith_activity_video_play_grid_recycleView = (RecyclerView)rootView.findViewById(R.id.faith_activity_video_play_grid_recycleView);
        adapter = new Faith_Video_Recycler_Adapter(activity,video_pic);
        faith_activity_video_play_grid_recycleView.setHasFixedSize(true);
        //LinearLayoutManager llm = new LinearLayoutManager(getContext());
        faith_activity_video_play_grid_recycleView.setLayoutManager(lLayout);
        faith_activity_video_play_grid_recycleView.setAdapter(adapter);

     /*   faith_fragment_video_card_videoicon_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  Log.i("video onclick url", videoURI);
                if (videoURI.substring(0, 5).equals("https")) {
                    System.out.println("Playing video from Server URL");
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(videoURI), "video/mp4");
                    startActivity(intent);
                } else {*//*
             *//*   System.out.println("Playing video from Device");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                File auxFile = new File(yourFilePath);
                intent.setDataAndType(Uri.fromFile(auxFile), "video/mp4");
                startActivity(intent);
                // }*//*
            }
        });*/

          return rootView;
    }

    /**
     * Checks if external storage is available to at least read
     * @return boolean
     */
    /* */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private List<Video_Card_Details> initializeData(){
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        video_pic = new ArrayList<>();
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_0)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_1)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_2)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_3)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_4)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_7)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.one)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.two)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.three)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.four)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.five)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.six)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.seven)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.eight)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_0)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_1)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_2)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_3)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_4)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.sample_7)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.one)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.two)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.three)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.four)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.five)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.six)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.seven)));
        video_pic.add(new Video_Card_Details(String.valueOf(R.drawable.eight)));
        return video_pic;
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