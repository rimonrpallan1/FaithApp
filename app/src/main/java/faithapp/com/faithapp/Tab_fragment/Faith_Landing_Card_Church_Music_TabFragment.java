package faithapp.com.faithapp.Tab_fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Landing_Card_View_item_click;
import faithapp.com.faithapp.custom.FlowLayout;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_Music_TabFragment extends Fragment {

    FlowLayout faith_landing_card_church_music_tabfragment_image_grid;
    ScrollView landing_card_view_item_click_music_grid_scroll_view;
    Boolean Play_stop_music = true;
    MediaPlayer player;

    public Faith_Landing_Card_Church_Music_TabFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_landing_card_church_music_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_Music_TabFragment");
        player = new MediaPlayer();

        faith_landing_card_church_music_tabfragment_image_grid = (FlowLayout) rootView.findViewById(R.id.faith_landing_card_church_music_tabfragment_image_grid);
        landing_card_view_item_click_music_grid_scroll_view = (ScrollView) rootView.findViewById(R.id.landing_card_view_item_click_music_grid_scroll_view);
        for(int x=0;x<15;x++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(R.drawable.ic_church_aulter);
            LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(300, 300);
            image.setLayoutParams(vp);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setMaxHeight(50);
            image.setMaxWidth(50);
            faith_landing_card_church_music_tabfragment_image_grid.addView(image);
        }

/*

                landing_card_view_item_click_music_grid_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        Faith_Landing_Card_View_item_click activity = (Faith_Landing_Card_View_item_click) getActivity();
                        activity.landing_card_view_item_click_expandable_text_view_linearlayout.setVisibility(View.GONE);
                    }
                });

*/
        faith_landing_card_church_music_tabfragment_image_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Faith_Landing_Card_Church_Music_TabFragment"+"-- Click");
                if(Play_stop_music) {
                    Play_stop_music =false;
                    try {

                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        player.setDataSource("http://members.onlinenigeria.com/public/music_song/ca/0e/0ebc_d926.mp3");
                        player.prepare();
                        player.start();
                        //
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("Faith_Landing_Card_Church_Music_TabFragment" + "-- E");
                        e.printStackTrace();
                    }
                }
                else {
                    Play_stop_music =true;
                    player.stop();
                }
            }
        });


        return rootView;
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