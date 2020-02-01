package org.techgeorge.trainingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.techgeorge.trainingtime.fragments.StatsFragment;
import org.techgeorge.trainingtime.fragments.TimerFragment;
import org.techgeorge.trainingtime.fragments.WorkoutFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //adding fragment to current activity
        Fragment fragment = new TimerFragment();
        fragmentTransaction.add(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
    }

    /**
     * Bottom Navigation Menu listener
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch(menuItem.getItemId()) {
                case R.id.navigation_timer:
                    fragment = new TimerFragment();
                    break;
                case R.id.navigation_workout:
                    fragment = new WorkoutFragment();
                    break;
                case R.id.navigation_stats:
                    fragment = new StatsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,
                    fragment).commit();
            return true;
        }
    };
}
