package org.techgeorge.trainingtime.classes;
import android.content.Context;
import android.content.res.Resources;

import org.techgeorge.trainingtime.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Workout {
    public static ArrayList<Exercise> exerciseList = new ArrayList<>();
    private static String[] exercises;
    private static boolean isExecuted = false; //fetch resources flag


    public Workout() {
    }

    public static ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }


    public static void setExerciseList(ArrayList<Exercise> list) {
        exerciseList = list;
    }

    public static boolean getIsExecuted() {
        return isExecuted;
    }

    /**
     * This returns the workout: a selection of exercises from
     * the exercise list.
     * @return
     */
    public static ArrayList<Exercise> getWorkoutList() {
        if(!isExecuted) {
            return null;
        }
        ArrayList<Exercise> workoutRutine = new ArrayList<>();
        for(Exercise ex: exerciseList) {
            if(ex.isSelected()) {
                workoutRutine.add(ex);
            }
        }
        return (workoutRutine.size() == 0) ? getExerciseList() : workoutRutine;
    }


    /**
     * to be executed first time application loads. it will get list of
     * exercise from string.xml resource file. and populate exerciseList
     * @param context
     */
    public static void populateExerciseList(Context context) {
        if(isExecuted) {
            return;
        }
        isExecuted = true;
        Resources res = context.getResources();
        exercises = res.getStringArray(R.array.exercise_list);
        exerciseList.add(new Exercise(exercises[0], "",  600000,
                R.drawable.ic_pushups_exercise));
        exerciseList.add(new Exercise(exercises[1], "", 600000,
                R.drawable.ic_pullups_exercise));
        exerciseList.add(new Exercise(exercises[2], "", 00000,
                R.drawable.ic_squat_exercise));
        exerciseList.add(new Exercise(exercises[3], "", 600000,
                R.drawable.ic_running_exercise));
        exerciseList.add(new Exercise(exercises[4], "", 600000,
                R.drawable.ic_bike_exercise));
        exerciseList.add(new Exercise(exercises[5], "", 600000,
                R.drawable.ic_yoga_exercise));
        exerciseList.add(new Exercise(exercises[6], "", 600000,
                R.drawable.ic_walking_exercise));
        exerciseList.add(new Exercise(exercises[7], "", 600000,
                R.drawable.ic_weightlifting_exercise));
        exerciseList.add(new Exercise(exercises[8], "", 600000,
                R.drawable.ic_rowing_exercise));
        exerciseList.add(new Exercise(exercises[9], "", 600000,
                R.drawable.ic_dips_exercise));
        exerciseList.add(new Exercise(exercises[10], "", 600000,
                R.drawable.ic_lunges_exercise));
        exerciseList.add(new Exercise(exercises[11], "", 600000,
                R.drawable.ic_pushups_exercise));
    }

}
