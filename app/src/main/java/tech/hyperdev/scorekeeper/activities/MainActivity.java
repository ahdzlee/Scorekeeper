package tech.hyperdev.scorekeeper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;

import tech.hyperdev.scorekeeper.R;
import tech.hyperdev.scorekeeper.fragments.ScoreFragment;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_FRAGMENT_TEAM_ONE = "key-fragment-team-one";
    private static final String KEY_FRAGMENT_TEAM_TWO = "key-fragment-team-two";

    private ScoreFragment mTeamOneFragment;
    private ScoreFragment mTeamTwoFragment;

    private ScoreFragment.OnCounterIncrementListener mTeamOneIncrementListener =
            new ScoreFragment.OnCounterIncrementListener() {
                @Override
                public void increment() {
                    mTeamOneFragment.incrementCounter();
                }
            };
    private ScoreFragment.OnCounterDecrementListener mTeamOneDecrementListener =
            new ScoreFragment.OnCounterDecrementListener() {
                @Override
                public void decrement() {
                    mTeamOneFragment.decrementCounter();
                }
            };
    private ScoreFragment.OnCounterIncrementListener mTeamTwoIncrementListener =
            new ScoreFragment.OnCounterIncrementListener() {
                @Override
                public void increment() {
                    mTeamTwoFragment.incrementCounter();
                }
            };
    private ScoreFragment.OnCounterDecrementListener mTeamTwoDecrementListener =
            new ScoreFragment.OnCounterDecrementListener() {
                @Override
                public void decrement() {
                    mTeamTwoFragment.decrementCounter();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Restore the fragments' instance state
            mTeamOneFragment = (ScoreFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, KEY_FRAGMENT_TEAM_ONE);

            mTeamTwoFragment = (ScoreFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, KEY_FRAGMENT_TEAM_TWO);
        } else {
            mTeamOneFragment = ScoreFragment.newInstance(getString(R.string.text_team_one));
            mTeamTwoFragment = ScoreFragment.newInstance(getString(R.string.text_team_two));
        }

        mTeamOneFragment.setOnCounterIncrementListener(mTeamOneIncrementListener);
        mTeamOneFragment.setOnCounterDecrementListener(mTeamOneDecrementListener);

        mTeamTwoFragment.setOnCounterIncrementListener(mTeamTwoIncrementListener);
        mTeamTwoFragment.setOnCounterDecrementListener(mTeamTwoDecrementListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teamOneContainer, mTeamOneFragment)
                .replace(R.id.teamTwoContainer, mTeamTwoFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the fragments' instance state
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT_TEAM_ONE, mTeamOneFragment);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT_TEAM_TWO, mTeamTwoFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu from XML
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.menu_day_mode);
        } else {
            menu.findItem(R.id.menu_night_mode).setTitle(R.string.menu_night_mode);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if correct item was clicked
        if (item.getItemId() == R.id.menu_night_mode) {
            // Get night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();

            // Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }

        recreate();

        return true;
    }
}
