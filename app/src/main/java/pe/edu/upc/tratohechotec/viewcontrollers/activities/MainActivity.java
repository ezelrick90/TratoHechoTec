package pe.edu.upc.tratohechotec.viewcontrollers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.upc.tratohechotec.R;
import pe.edu.upc.tratohechotec.viewcontrollers.fragments.ChecklistFragment;
import pe.edu.upc.tratohechotec.viewcontrollers.fragments.ProfileFragment;
import pe.edu.upc.tratohechotec.viewcontrollers.fragments.ScheduleFragment;
import pe.edu.upc.tratohechotec.viewcontrollers.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateTo(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        int itemId =  navigation.getSelectedItemId() == 0 ? R.id.navigation_schedule : navigation.getSelectedItemId();
        navigateTo(navigation.getMenu().findItem(itemId));
    }

    private Fragment getFragmentFor(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_schedule: return new ScheduleFragment();
            case R.id.navigation_checklist: return new ChecklistFragment();
            case R.id.navigation_profile: return new ProfileFragment();
            case R.id.navigation_settings: return new SettingsFragment();
            default: return new ScheduleFragment();
        }
    }

    private boolean navigateTo(MenuItem item) {
        item.setChecked(true);

        return getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, getFragmentFor(item))
                .commit() > 0;
    }

}
