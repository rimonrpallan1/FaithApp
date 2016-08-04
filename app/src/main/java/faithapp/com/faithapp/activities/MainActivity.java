package faithapp.com.faithapp.activities;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import faithapp.com.faithapp.R;
import faithapp.com.faithapp.adapters.Faith_Landing_Filter_PagerAdapter;
import faithapp.com.faithapp.fragments.Faith_Landing_Fragment;
import faithapp.com.faithapp.fragments.Faith_Favourite_Fragment;
import faithapp.com.faithapp.fragments.Faith_Profile_Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private  Toolbar toolbar,toolbar_search,toolbar_first;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private NavigationView mDrawerListView;
    private DrawerLayout mDrawerLayout;

    // Search Toolbar initial

    EditText   faith_search_edit_text;
    TextView faith_search_text_view;
    ImageView faith_search_filter,faith_search_close_icon,faith_search_close_icon_change;

    //Animation for Search toolbar

    Animation slide_in_right;
    Animation slide_out_left;

    Animation slide_up;
    Animation slide_down;

    FrameLayout faith_main_activity_search_filter_frame_layout, faith_main_activity_search_filter_linear_layout;

    Boolean isVisible = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_search = (Toolbar) findViewById(R.id.toolbar_search);
        faith_search_close_icon = (ImageView) findViewById(R.id.faith_search_close_icon);

        faith_search_edit_text = (EditText) findViewById(R.id.faith_search_edit_text);
        faith_search_text_view =(TextView) findViewById(R.id.faith_search_text_view);
        faith_search_filter = (ImageView) findViewById(R.id.faith_search_filter);
        faith_main_activity_search_filter_frame_layout = (FrameLayout) findViewById(R.id.faith_main_activity_search_filter_linear_layout);
        //faith_main_activity_search_filter_linear_layout = (FrameLayout) findViewById(R.id.faith_main_activity_search_filter_linear_layout_);

        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Faith_Landing_Fragment())
                    .commit();


        }


        // ChatActivity Navigation Drawer
        mDrawerListView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        //Initializing NavigationView
        mDrawerListView = (NavigationView) findViewById(R.id.navigation_view);


        addDrawerItems();
        setupDrawer();
        setMenuCounter(R.id.profile_drawer, 10);
        //------------ End of Navigation Drawer-----------------------------

        faith_search_close_icon.setOnClickListener(this);
        faith_search_edit_text.setOnClickListener(this);
        faith_search_filter.setOnClickListener(this);
        faith_search_text_view.setOnClickListener(this);
        faith_main_activity_search_filter_frame_layout.setOnClickListener(this);
//        faith_main_activity_search_filter_linear_layout.setOnClickListener(this);


        // Animation for the landing_toolbar_search
        slide_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right);
        slide_out_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_left);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_up);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_down);
        //faith_search_edit_text.getBackground().setColorFilter(getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);

        //---------------------------------------- Search_Filter_Display ----------------------------------------

        TabLayout tabLayout = (TabLayout) findViewById(R.id.faith_main_activity_search_filter_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("RITES"));
        tabLayout.addTab(tabLayout.newTab().setText("LANGUAGES"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.faith_main_activity_search_filter_pager);
        final Faith_Landing_Filter_PagerAdapter adapter = new Faith_Landing_Filter_PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOffscreenPageLimit(2);
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


        faith_main_activity_search_filter_frame_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slide_up_Search_filter_Layout();

            }
        });



    }


    /**
     * This method is used for the animation for filter button clicks,
     * This also set the toolbar filter layout to slide back if the filter button is clicked.
     */

    public void toolbar_filter_Search_Layout_Visibility() {
        if (faith_main_activity_search_filter_frame_layout.getVisibility() == View.GONE ) {
            faith_main_activity_search_filter_frame_layout.setBackgroundResource(android.R.color.transparent);
            faith_main_activity_search_filter_frame_layout.setVisibility(View.VISIBLE);
            faith_main_activity_search_filter_frame_layout.setAnimation(slide_down);
            slide_down.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    faith_main_activity_search_filter_frame_layout.setBackgroundColor(Color.parseColor("#80000000"));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            slide_down.start();
        } else {
            slide_up_Search_filter_Layout();
        }

    }

    /**
     * This method is used to give animation slide_up to the search frame.
     */

    public void slide_up_Search_filter_Layout() {
        if (faith_main_activity_search_filter_frame_layout.getVisibility() == View.VISIBLE) {
            slide_up.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    faith_main_activity_search_filter_frame_layout.setBackgroundResource(android.R.color.transparent);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            faith_main_activity_search_filter_frame_layout.setAnimation(slide_up);
            slide_up.start();
            faith_main_activity_search_filter_frame_layout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private void addDrawerItems() {

        mDrawerListView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home_drawer:
                        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
                        Faith_Landing_Fragment Faith_Landing_Fragment = new Faith_Landing_Fragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, Faith_Landing_Fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        setDrawerState(true);
                        getSupportActionBar().setTitle("Faith App");

                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.profile_drawer:
                        Toast.makeText(getApplicationContext(), "PROFILE Selected", Toast.LENGTH_SHORT).show();
                        Faith_Profile_Fragment Faith_Profile_Fragment = new Faith_Profile_Fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, Faith_Profile_Fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        setDrawerState(false);
                        getSupportActionBar().setTitle("Profile");

                        return true;
                    case R.id.favorites_drawer:
                        Toast.makeText(getApplicationContext(), "FAVORITES Selected", Toast.LENGTH_SHORT).show();
                        Faith_Favourite_Fragment Faith_Favourite_Fragment = new Faith_Favourite_Fragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, Faith_Favourite_Fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        setDrawerState(false);
                        getSupportActionBar().setTitle("Favourites");

                        return true;
                    case R.id.feed_support_drawer:
                        Toast.makeText(getApplicationContext(), "FEEDBACK & SUPPORT Selected", Toast.LENGTH_SHORT).show();
                        Intent activity = new Intent(MainActivity.this, Faith_Activity_Login.class);
                        startActivity(activity);
                        return true;
                    case R.id.rate_us_drawer:
                        Toast.makeText(getApplicationContext(), "RATE US Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.share_drawer:
                        Toast.makeText(getApplicationContext(), "SHARE Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    default:

                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });
    }

    /**
     * This method is used to set the item count in Navigation Drawer
     * @param itemId   resource id of particular item in drawer
     * @param count    count number of particular item in drawer
     */


    private void setMenuCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) mDrawerListView.getMenu().findItem(itemId).getActionView();
        view.setText(count > 0 ? String.valueOf(count) : null);
    }


    private void setupDrawer() {


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.drawer_open, R.string.drawer_close) {


            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //mDrawerLayout.openDrawer(Gravity.RIGHT);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);


                // mDrawerLayout.closeDrawer(Gravity.RIGHT);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();
            this.getSupportActionBar().setHomeButtonEnabled(true);

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.syncState();
            this.getSupportActionBar().setHomeButtonEnabled(false);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                if(mDrawerToggle.isDrawerIndicatorEnabled()){
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }else{
                    onBackPressed();
                    getSupportActionBar().setTitle("Faith App");
                }
                return true;
            case R.id.notification_icon:
                Toast.makeText(getApplicationContext(), "Notification Fragment Selected", Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(MainActivity.this, Faith_Activity_Notification.class);
                startActivity(activity);
                return true;
            case R.id.play_icon:
                Toast.makeText(getApplicationContext(), "Radio Fragment Selected", Toast.LENGTH_SHORT).show();
                Intent activity2 = new Intent(MainActivity.this, Faith_Activity_Radio.class);
                startActivity(activity2);
                getSupportActionBar().setTitle("");
                return true;
            case R.id.search_icon:
                faith_search_close_icon.setAnimation(slide_in_right);
                slide_in_right.setAnimationListener(new Animation.AnimationListener() {
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
                slide_in_right.start();
                toolbar_search.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.GONE);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mDrawerListView.getMenu().getItem(0).setChecked(true);
        setDrawerState(true);
    }

    @Override
    public void onClick(View view) {
        System.out.println("View ID : " + view.getId());
        switch (view.getId()) {
            case R.id.faith_search_close_icon:
                faith_search_text_view.setVisibility(View.GONE);
                faith_search_edit_text.setVisibility(View.VISIBLE);
                faith_search_close_icon.setAnimation(slide_out_left);
                slide_out_left.setAnimationListener(new Animation.AnimationListener() {
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
                slide_out_left.start();
                toolbar_search.setVisibility(View.GONE);
                toolbar.setVisibility(View.VISIBLE);
                slide_up_Search_filter_Layout();
                break;
            case R.id.faith_search_edit_text:
                break;
            case R.id.faith_search_filter:
                faith_search_edit_text.setVisibility(View.GONE);
                faith_search_text_view.setVisibility(View.VISIBLE);
                toolbar_filter_Search_Layout_Visibility();

                break;



        }
    }



}
