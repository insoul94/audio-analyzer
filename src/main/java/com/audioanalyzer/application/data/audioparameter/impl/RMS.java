package com.audioanalyzer.application.data.audioparameter.impl;

import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;

public class RMS extends AudioParameter {

    public RMS() {
        super(AudioParameterType.RMS);
    }

    @Override
    public float calculate(float[] data) {
        double sum = 0;
        for (int i = 0; i < data.length - 1; i++) {
            sum += (double) data[i] * data[i];
        }
        double rms = Math.sqrt(sum / data.length);
        return (float) rms;
    }
}
