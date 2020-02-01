package org.techgeorge.trainingtime.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.techgeorge.trainingtime.R;
import org.techgeorge.trainingtime.adapters.WorkoutAdapter;
import org.techgeorge.trainingtime.classes.Exercise;
import org.techgeorge.trainingtime.classes.Workout;

public class WorkoutFragment extends Fragment {
    private FloatingActionButton saveWorkoutButton;
    private ImageView setTimerImageView;
    private WorkoutAdapter adapter;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        saveWorkoutButton = view.findViewById(R.id.save_workout_btn);
        setTimerImageView = view.findViewById(R.id.set_time_imagview);
        //inflating array (workout list)
        adapter = new WorkoutAdapter(view.getContext(), R.layout.entry_item,
                Workout.getExerciseList());
        listView =  view.findViewById(R.id.exercise_list);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        saveWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerFragment timerFragment = new TimerFragment();
                Bundle args = new Bundle();
                timerFragment.setArguments(args);
                getFragmentManager().beginTransaction().add(R.id.fragment_layout, timerFragment).commit();
            }});

        //activating text to speech when user selects item in listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paren, View view, int position, long id) {
                Toast.makeText(getContext(), position+"", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}