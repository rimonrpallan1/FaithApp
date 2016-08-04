package faithapp.com.faithapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import faithapp.com.faithapp.R;
import faithapp.com.faithapp.activities.Faith_Activity_Login;

/**
 * Created by rimon on 18/4/16.
 */
public class Faith_Profile_Fragment extends Fragment {

    TextView faith_profile_user_txt;
    TextView faith_profile_email_txt;
    TextView faith_profile_date_of_birth_txt;
    CircleImageView faith_nav_drawer_img;


    public Faith_Profile_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_profile_fragment, container, false);


        faith_nav_drawer_img = (CircleImageView)rootView.findViewById(R.id.faith_nav_drawer_img);
        faith_profile_user_txt = (TextView)rootView.findViewById(R.id.faith_profile_user_txt);
        faith_profile_email_txt = (TextView)rootView.findViewById(R.id.faith_profile_email_txt);
        if(Faith_Activity_Login.user_image_url!=null && Faith_Activity_Login.user_name!=null && Faith_Activity_Login.user_email_Adress!=null){
            Picasso.with(getContext()).load(Faith_Activity_Login.user_image_url).into(faith_nav_drawer_img);
            faith_profile_user_txt.setText(Faith_Activity_Login.user_name);
            faith_profile_email_txt.setText(Faith_Activity_Login.user_email_Adress);
        }


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.faith_favourite_fragment_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
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


