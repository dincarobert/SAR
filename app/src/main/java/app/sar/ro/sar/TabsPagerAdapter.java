package app.sar.ro.sar;

/**
 * Created by Admin on 19/01/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new MyInfoFragment();
            case 1:
                // Games fragment activity
                return new GetGsmSignalStrengthFragment();
            case 2:
                // Movies fragment activity
                return new GetGsmSignalStrengthFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}