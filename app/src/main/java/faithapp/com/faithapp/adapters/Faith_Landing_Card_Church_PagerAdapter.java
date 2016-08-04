package faithapp.com.faithapp.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import faithapp.com.faithapp.Tab_fragment.Faith_Landing_Card_Church_Gallery_TabFragment;
import faithapp.com.faithapp.Tab_fragment.Faith_Landing_Card_Church_MapLocation_TabFragment;
import faithapp.com.faithapp.Tab_fragment.Faith_Landing_Card_Church_Music_TabFragment;
import faithapp.com.faithapp.Tab_fragment.Faith_Landing_Card_Church_Notification_TabFragment;
import faithapp.com.faithapp.Tab_fragment.Faith_Landing_Card_Church_VideoPlay_TabFragment;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Card_Church_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String church_id;

    public Faith_Landing_Card_Church_PagerAdapter(FragmentManager fm, int NumOfTabs, String church_id) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.church_id = church_id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Faith_Landing_Card_Church_Gallery_TabFragment tab1 = new Faith_Landing_Card_Church_Gallery_TabFragment();
                return tab1;
            case 1:
                Faith_Landing_Card_Church_VideoPlay_TabFragment tab2 = new Faith_Landing_Card_Church_VideoPlay_TabFragment();
                return tab2;
            case 2:
                Faith_Landing_Card_Church_Music_TabFragment tab3 = new Faith_Landing_Card_Church_Music_TabFragment();
                return tab3;
            case 3:
                Faith_Landing_Card_Church_Notification_TabFragment tab4 = new Faith_Landing_Card_Church_Notification_TabFragment(church_id);
                return tab4;
            case 4:
                Faith_Landing_Card_Church_MapLocation_TabFragment tab5 = new Faith_Landing_Card_Church_MapLocation_TabFragment();
                return tab5;


            default:
                Faith_Landing_Card_Church_Gallery_TabFragment tab7 = new Faith_Landing_Card_Church_Gallery_TabFragment();
                return tab7;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}