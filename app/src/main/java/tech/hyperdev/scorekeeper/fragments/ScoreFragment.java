package tech.hyperdev.scorekeeper.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.hyperdev.scorekeeper.R;

public class ScoreFragment extends Fragment implements View.OnClickListener {

    /**
     * The interface contract to use when incrementing score counter.
     */
    public interface OnCounterIncrementListener {
        void increment();
    }

    /**
     * The interface contract to use when decrementing score counter.
     */
    public interface OnCounterDecrementListener {
        void decrement();
    }

    private static final String KEY_TEAM_NAME = "key-team-name";
    private static final String KEY_TEAM_SCORE = "key-team-score";

    private TextView mTvTeamName;
    private TextView mTvCounter;

    private int mCounter = 0;
    private OnCounterIncrementListener mCounterIncrementListener;
    private OnCounterDecrementListener mCounterDecrementListener;

    /**
     * Creates a new instance of {@link ScoreFragment} with passed team name.
     */
    public static ScoreFragment newInstance(String teamName) {
        Bundle args = new Bundle();
        args.putString(KEY_TEAM_NAME, teamName);

        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Gets the counter's increment listener.
     */
    public OnCounterIncrementListener getOnCounterIncrementListener() {
        return mCounterIncrementListener;
    }

    /**
     * Sets the counter's increment listener.
     */
    public void setOnCounterIncrementListener(OnCounterIncrementListener counterIncrementListener) {
        mCounterIncrementListener = counterIncrementListener;
    }

    /**
     * Gets the counter's decrement listener.
     */
    public OnCounterDecrementListener getOnCounterDecrementListener() {
        return mCounterDecrementListener;
    }

    /**
     * Sets the counter's decrement listener.
     */
    public void setOnCounterDecrementListener(OnCounterDecrementListener counterDecrementListener) {
        mCounterDecrementListener = counterDecrementListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String teamName = getArguments().getString(KEY_TEAM_NAME);
        mTvTeamName.setText(teamName);

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(KEY_TEAM_SCORE);
            mTvCounter.setText(String.valueOf(mCounter));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_TEAM_SCORE, mCounter);
    }

    private void findViews(View view) {
        mTvTeamName = (TextView) view.findViewById(R.id.tvTeamName);
        mTvCounter = (TextView) view.findViewById(R.id.tvCounter);

        view.findViewById(R.id.btnPlus).setOnClickListener(this);
        view.findViewById(R.id.btnMinus).setOnClickListener(this);
    }

    /**
     * Increments counter value and updates the result to the view.
     */
    public void incrementCounter() {
        mCounter++;
        mTvCounter.setText(String.valueOf(mCounter));
    }

    /**
     * Decrements counter value and updates the result to the view.
     */
    public void decrementCounter() {
        mCounter--;
        mTvCounter.setText(String.valueOf(mCounter));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnPlus:
                if (mCounterIncrementListener != null) {
                    mCounterIncrementListener.increment();
                }
                break;

            case R.id.btnMinus:
                if (mCounterDecrementListener != null) {
                    mCounterDecrementListener.decrement();
                }
                break;
        }
    }
}
