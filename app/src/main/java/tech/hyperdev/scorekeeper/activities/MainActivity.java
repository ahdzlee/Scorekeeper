package tech.hyperdev.scorekeeper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tech.hyperdev.scorekeeper.R;
import tech.hyperdev.scorekeeper.fragments.ScoreFragment;

public class MainActivity extends AppCompatActivity {

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

        mTeamOneFragment = ScoreFragment.newInstance(getString(R.string.text_team_one));
        mTeamTwoFragment = ScoreFragment.newInstance(getString(R.string.text_team_two));

        mTeamOneFragment.setOnCounterIncrementListener(mTeamOneIncrementListener);
        mTeamOneFragment.setOnCounterDecrementListener(mTeamOneDecrementListener);

        mTeamTwoFragment.setOnCounterIncrementListener(mTeamTwoIncrementListener);
        mTeamTwoFragment.setOnCounterDecrementListener(mTeamTwoDecrementListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.teamOneContainer, mTeamOneFragment)
                .add(R.id.teamTwoContainer, mTeamTwoFragment)
                .commit();
    }
}
