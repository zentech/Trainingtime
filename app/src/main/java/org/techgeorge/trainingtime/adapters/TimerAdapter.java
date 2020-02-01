package org.techgeorge.trainingtime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.techgeorge.trainingtime.R;
import org.techgeorge.trainingtime.classes.Exercise;

import java.util.ArrayList;

public class TimerAdapter extends ArrayAdapter<Exercise> {

    public TimerAdapter(Context context, int num, ArrayList<Exercise> exerciseList) {
        super(context, 0, exerciseList);
        if(exerciseList.size() == 0) {
            return;
        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        final Exercise exercise = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_timer, parent, false);
        }
        // Lookup view for data population
        ImageView exerciseImageview = convertView.findViewById(R.id.item_icon_imageview1);
        TextView exerciseNameTextview = convertView.findViewById(R.id.exercise_textview1);
        TextView timeTextView = convertView.findViewById(R.id.time_textview1);
        final RadioButton radioButton = convertView.findViewById(R.id.exercise_radioButton1);

        exerciseNameTextview.setText(exercise.getExerciseName());
        exerciseImageview.setImageResource(exercise.getExerciseImage());
        //checking if exercise time is in millisec or min
        timeTextView.setText(exercise.getExerciseTime()/60000 +" min"); //convert to min

        //selected exercise checkbox
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton.isChecked()) {
                    exercise.setSelected(false);
                    radioButton.setChecked(false);
                }
                else {
                    exercise.setSelected(true);
                    radioButton.setChecked(true);
                }
            }
        });

        return convertView;
    }
}