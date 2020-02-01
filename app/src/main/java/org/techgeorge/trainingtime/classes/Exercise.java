package org.techgeorge.trainingtime.classes;

/**
 * Exercise class containing data for single exericse
 * rutine eg: pushup, pullups, squats, etc...
 */

public class Exercise {
    private String exerciseName;
    private String exerciseDesc;
    private long exerciseTime;
    private int exerciseImage;
    private boolean isSelected = false;

    public Exercise(String exerciseName, String exerciseDesc, int exerciseTime, int exerciseImage) {
        this.exerciseName = exerciseName;
        this.exerciseDesc = exerciseDesc;
        this.exerciseTime = exerciseTime;
        this.exerciseImage = exerciseImage;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDesc() {
        return exerciseDesc;
    }

    public long getExerciseTime() {
        return exerciseTime;
    }

    public int getExerciseImage() {
        return exerciseImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setExerciseTime(long exerciseTime) {
        this.exerciseTime = exerciseTime;
    }
}
