package tech.hyperdev.scorekeeper.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.hyperdev.scorekeeper.R;

public class ScoreFragment extends Fragment {

    private static final String KEY_TEAM_NAME = "key-team-name";
    private TextView mTvTeamName;

    public static ScoreFragment newInstance(String teamName) {
        Bundle args = new Bundle();
        args.putString(KEY_TEAM_NAME, teamName);

        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(args);
        return fragment;
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
    }

    private void findViews(View view) {
        mTvTeamName = (TextView) view.findViewById(R.id.tvTeamName);
    }
}
