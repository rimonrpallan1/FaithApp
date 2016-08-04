package faithapp.com.faithapp.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import faithapp.com.faithapp.fragments.Faith_Landing_Filter_Language_Fragment;
import faithapp.com.faithapp.fragments.Faith_Landing_Filter_Rites_Fragment;

/**
 * Created by rimon on 22/4/16.
 */
public class Faith_Landing_Filter_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Faith_Landing_Filter_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Faith_Landing_Filter_Rites_Fragment tab1 = new Faith_Landing_Filter_Rites_Fragment();
                return tab1;
            case 1:
                Faith_Landing_Filter_Language_Fragment tab2 = new Faith_Landing_Filter_Language_Fragment();
                return tab2;



            default:
                Faith_Landing_Filter_Rites_Fragment tab7 = new Faith_Landing_Filter_Rites_Fragment();
                return tab7;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}