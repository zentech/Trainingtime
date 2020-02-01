package org.techgeorge.trainingtime.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techgeorge.trainingtime.R;

/**
 * stats class: here we display stats and data. step count, calories burned, distance
 * workout time, heart rate, BMI, and other relevant infor to the user
 */

public class StatsFragment extends Fragment implements SensorEventListener {
    private static final String LOG_TAG = "STATS";
    private TextView stepCountTextView;
    private Button clearCounterButton;
    private Sensor stepSensor;
    private Integer stepCount = 0;
    private double MagnitudePrevious = 0;
    private SensorManager sensorManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        stepCountTextView = view.findViewById(R.id.step_counter);
        clearCounterButton = view.findViewById(R.id.clear_button);

        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        clearCounterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stepCount = 0;
                stepCountTextView.setText(stepCount.toString());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getActivity(), "Sensor not available!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            float x_acceleration = sensorEvent.values[0];
            float y_acceleration = sensorEvent.values[1];
            float z_acceleration = sensorEvent.values[2];
            double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration
                    + z_acceleration*z_acceleration);
            double MagnitudeDelta = Magnitude - MagnitudePrevious;
            MagnitudePrevious = Magnitude;

            if (sensorEvent.values[0] > 5){
                stepCount++;
            }
            stepCountTextView.setText(stepCount.toString());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
