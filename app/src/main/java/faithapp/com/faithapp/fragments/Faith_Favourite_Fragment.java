package faithapp.com.faithapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.adapters.Faith_Fragment_Fravourate_Recycler_Adapter;
import faithapp.com.faithapp.adapters.Faith_Landing_Recycler_Adapter;
import faithapp.com.faithapp.model.Landing_Card_Details;

/**
 * Created by rimon on 18/4/16.
 */
public class Faith_Favourite_Fragment extends Fragment {

    // Toolbar toolbar_faith_landing;
    RecyclerView faith_favourite_fragment_recycleView;
    Faith_Fragment_Fravourate_Recycler_Adapter adapter;
    List<Landing_Card_Details> church_detail;
    String photoId;
    private Activity activity;

    public Faith_Favourite_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_favourite_fragment, container, false);
        activity = getActivity();
        initializeData();

        // toolbar_faith_landing= (Toolbar) container.findViewById(R.id.toolbar_faith_landing);
       /* tvDiscover= (TextView)container.findViewById(R.id.TVDiscover);
        tvSlash= (TextView)container.findViewById(R.id.TVSlash);
        tvChat= (TextView)container.findViewById(R.id.TVChat);*/
        faith_favourite_fragment_recycleView = (RecyclerView)rootView.findViewById(R.id.faith_favourite_fragment_recycleView);
        adapter = new Faith_Fragment_Fravourate_Recycler_Adapter(activity,church_detail);
        faith_favourite_fragment_recycleView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        faith_favourite_fragment_recycleView.setLayoutManager(llm);
        faith_favourite_fragment_recycleView.setAdapter(adapter);

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


        faith_favourite_fragment_recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {

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
        church_detail.add(new Landing_Card_Details("2","Holy Family Parish", "We at Holy Family Parish be like that new handboning method will save your life one day", photoId, "800 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("3","Mount Zion Church","We at Mount Zion Church be like that new handboning method will save your life one day", photoId, "450 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("4","St. Joseph Church","We at St. Joseph Church be like that new handboning method will save your life one day",photoId,"1045000 FOLLOWERS", true));
        church_detail.add(new Landing_Card_Details("5","St. Peter Basilica","We at St. Peter Basilica be like that new handboning method will save your life one day",photoId,"4738 FOLLOWERS", true));

    }


}


