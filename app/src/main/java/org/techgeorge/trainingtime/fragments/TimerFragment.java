package org.techgeorge.trainingtime.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.techgeorge.trainingtime.R;
import org.techgeorge.trainingtime.adapters.TimerAdapter;
import org.techgeorge.trainingtime.adapters.WorkoutAdapter;
import org.techgeorge.trainingtime.classes.Exercise;
import org.techgeorge.trainingtime.classes.Workout;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class TimerFragment extends Fragment {
    private ProgressBar progressBar;
    private TextView activityTitleTextview;
    private TextView mainTimerTextview;
    private TextView caloriesTextview;
    private TextView workoutTimeTextview;
    private TextView heartRateTextview;
    private FloatingActionButton startTimerButton;
    private FloatingActionButton resetTimerButton;
    private MyCountDownTimer timer;
    private static final int START_TIME_IN_MILLIS = 600000;
    private static final int START_TIME_IN_MINS = START_TIME_IN_MILLIS/60000;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private long ONE_MIN_MILLISEC = 60000;
    private boolean isTimerRunning = false;
    private TimerAdapter adapter;
    private ListView listView;
    private ArrayList<Exercise> workoutList = new ArrayList<>();
    private int workoutIndex = 0; //keeps track of workout inside workoutList

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        progressBar =  view.findViewById(R.id.timer_progressBar);
        mainTimerTextview = view.findViewById(R.id.timer_main_textview);
        activityTitleTextview = view.findViewById(R.id.title_activity_textview);
        caloriesTextview = view.findViewById(R.id.calories_textview);
        workoutTimeTextview = view.findViewById(R.id.workout_time_textview);
        heartRateTextview = view.findViewById(R.id.heartbit_textview);
        startTimerButton = view.findViewById(R.id.timer_button);
        resetTimerButton = view.findViewById(R.id.reset_button);

        mainTimerTextview.setText(START_TIME_IN_MINS+":00");


        /** if workout list hasn't been populated do so, else get selected exercise
         */
        if(!Workout.getIsExecuted()) {
            Workout.populateExerciseList(getContext());
            workoutList = Workout.getExerciseList();
        } else {
            Workout.getWorkoutList();
            workoutList = Workout.getWorkoutList();
        }

        timeLeftInMillis = workoutList.get(workoutIndex).getExerciseTime();
        mainTimerTextview.setText(timeLeftInMillis/60000 +":00"); //convert to minutes

        //inflating array (workout list)
        adapter = new TimerAdapter(view.getContext(), R.layout.entry_timer, workoutList);
        listView =  view.findViewById(R.id.workout_listview);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        //start timer
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    isTimerRunning = true;
                    startTimerButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    timer = new MyCountDownTimer(timeLeftInMillis, 1000);
                    timer.start();
                    progressBar.setProgress(100);
                }
            }
        });
        return view;
    }

    public class MyCountDownTimer extends CountDownTimer {
        int startTime;
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            startTime = (int) millisInFuture / 1000;
        }

        public void onTick(long millisUntilFinished) {
            timeLeftInMillis = millisUntilFinished;
            int minutes = (int) (timeLeftInMillis / ONE_MIN_MILLISEC);
            int seconds = (int) (timeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mainTimerTextview.setText(timeLeftFormatted);
            int progress = ((int) (timeLeftInMillis/1000) * 100 / startTime); //calculating progress in percent
            progressBar.setProgress(progress);
        }

        public void onFinish() {
            mainTimerTextview.setText("00:00");
            startTimerButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            isTimerRunning = false;
            workoutList.remove(workoutIndex);
            if(workoutList.size() == 0) return;
            Log.v("TIMER", workoutIndex + "");
            timeLeftInMillis = workoutList.get(workoutIndex).getExerciseTime();
            adapter.notifyDataSetChanged();
        }

    }

    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        startTimerButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
    }

}
