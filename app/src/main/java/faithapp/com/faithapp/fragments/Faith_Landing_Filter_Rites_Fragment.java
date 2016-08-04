package faithapp.com.faithapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import faithapp.com.faithapp.R;
import faithapp.com.faithapp.adapters.Faith_Landing_Filter_Rites_Recycle_view;
import faithapp.com.faithapp.model.Landing_FilterSearch_Rites;

/**
 * Created by rimon on 29/4/16.
 */
public class Faith_Landing_Filter_Rites_Fragment extends Fragment {

    // Toolbar toolbar_faith_landing;
    RecyclerView landing_search_filter_rites_recycleView;
    Faith_Landing_Filter_Rites_Recycle_view adapter;
    List<Landing_FilterSearch_Rites> Rites_events;
    String photoId;
    private Activity activity;
    LinearLayout landing_search_filter_rites_linear_layout;

    public Faith_Landing_Filter_Rites_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.faith_filter_rites_tabfragment, container, false);
        System.out.println("Faith_Landing_Card_Church_Notification_TabFragment");
        activity = getActivity();
        initializeData();

        landing_search_filter_rites_linear_layout = (LinearLayout) rootView.findViewById(R.id.landing_search_filter_rites_linear_layout);
        landing_search_filter_rites_recycleView = (RecyclerView) rootView.findViewById(R.id.landing_search_filter_rites_recycleView);
        adapter = new Faith_Landing_Filter_Rites_Recycle_view(activity, Rites_events);
        landing_search_filter_rites_recycleView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        landing_search_filter_rites_recycleView.setLayoutManager(llm);
        landing_search_filter_rites_recycleView.setAdapter(adapter);


        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        landing_search_filter_rites_recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {

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

    /**
     * This method creates an ArrayList that has Church Notification model class objects
     */
    private void initializeData() {
        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        Rites_events = new ArrayList<>();
        Rites_events.add(new Landing_FilterSearch_Rites("1", "Rc"));
        Rites_events.add(new Landing_FilterSearch_Rites("2", "Latin"));
        Rites_events.add(new Landing_FilterSearch_Rites("3", "Malankara"));
        Rites_events.add(new Landing_FilterSearch_Rites("4", "Jocobite"));
        Rites_events.add(new Landing_FilterSearch_Rites("5", "Celtic"));
        Rites_events.add(new Landing_FilterSearch_Rites("6", "Syro - malabar"));

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