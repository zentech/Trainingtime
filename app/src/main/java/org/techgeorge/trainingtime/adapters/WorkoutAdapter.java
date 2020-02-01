package org.techgeorge.trainingtime.adapters;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.techgeorge.trainingtime.R;
import org.techgeorge.trainingtime.classes.Exercise;
import org.techgeorge.trainingtime.classes.Workout;

import java.util.ArrayList;

public class WorkoutAdapter extends ArrayAdapter<Exercise> {
    private long ONE_MIN_MILLISEC = 60000; //one minute in millisec

    public WorkoutAdapter(Context context, int num, ArrayList<Exercise> exerciseList) {
        super(context, 0, exerciseList);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final Exercise exercise = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_item, parent, false);
        }
        // Lookup view for data population
        ImageView exerciseImageview = convertView.findViewById(R.id.item_icon_imageview);
        TextView exerciseNameTextview = convertView.findViewById(R.id.exercise_textview);
        final TextView exerTimeTextview = convertView.findViewById(R.id.exercise_time_textview);
        final ImageView setTimeImageview = convertView.findViewById(R.id.set_time_imagview);
        final CheckBox exerciseCheckbox = convertView.findViewById(R.id.exercise_checkBox);

        exerciseNameTextview.setText(exercise.getExerciseName());
        exerciseImageview.setImageResource(exercise.getExerciseImage());


        //selected exercise checkbox
        exerciseCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exerciseCheckbox.isChecked()) {
                    exercise.setSelected(true);
                    Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    exercise.setSelected(false);
                }
            }
        });

        setTimeImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimerDialogBox(position, exercise, exerTimeTextview, exerciseCheckbox);
            }
        });

        return convertView;
    }

    /**
     * alert dialogbox to set time for item in listview
     */
    private void setTimerDialogBox(final int position, final Exercise exercise, final TextView exerTimeTextview,
                                   final CheckBox exerCheckBox) {
        Log.v("POS", position+"");
        // Creating alert Dialog with one Button
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.dialogBox);
        alertDialog.setTitle("Set Time");
        final EditText input = new EditText(getContext());
        alertDialog.setView(input);
        alertDialog.setIcon(R.drawable.ic_timer_black_24dp1);

        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        int time = Integer.parseInt(input.getText().toString());
                        Workout.exerciseList.get(position).setExerciseTime(time * ONE_MIN_MILLISEC); //convert ot millisec
                        exerTimeTextview.setText(time +" min");
                        notifyDataSetChanged();
                        exercise.setSelected(true);
                        exerCheckBox.setChecked(true);
                        Toast.makeText(getContext(),"Time added", Toast.LENGTH_SHORT).show();
                    }
                }).create();

        alertDialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        alertDialog.show();
    }
}