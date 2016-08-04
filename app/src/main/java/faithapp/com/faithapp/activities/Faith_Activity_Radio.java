package faithapp.com.faithapp.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import faithapp.com.faithapp.R;

/**
 * Created by rimon on 20/4/16.
 */
public class Faith_Activity_Radio extends AppCompatActivity {

    private  Toolbar toolbar;
    ImageButton faith_radio_fragment_button;
    Boolean Play_stop_music = true;
    MediaPlayer player;


    public Faith_Activity_Radio() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faith_radio_fragment);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        faith_radio_fragment_button =(ImageButton) findViewById(R.id.faith_radio_fragment_button);
        player = new MediaPlayer();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Faith App");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        faith_radio_fragment_button.setOnClickListener(new View.OnClickListener() {
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


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faith_activity_radio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {


            case R.id.faith_radio_play_icon:

                return true;
            case R.id.faith_radio_icon:

                return true;

            case R.id.faith_radio_notification_icon:
                Toast.makeText(getApplicationContext(), "Notification Activity Selected", Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(Faith_Activity_Radio.this, Faith_Activity_Notification.class);
                startActivity(activity);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}