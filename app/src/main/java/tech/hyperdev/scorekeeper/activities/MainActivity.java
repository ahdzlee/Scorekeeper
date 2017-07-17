package tech.hyperdev.scorekeeper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tech.hyperdev.scorekeeper.R;
import tech.hyperdev.scorekeeper.fragments.ScoreFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScoreFragment teamOneFragment = new ScoreFragment();
        ScoreFragment teamTwoFragment = new ScoreFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.teamOneContainer, teamOneFragment)
                .add(R.id.teamTwoContainer, teamTwoFragment)
                .commit();
    }
}
