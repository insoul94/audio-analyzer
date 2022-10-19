package com.audioanalyzer.application.data.audioparameter.impl;

import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;

public class NoiseFloor extends AudioParameter {

    public NoiseFloor() {
        super(AudioParameterType.NoiseFloor);
    }

    @Override
    public float calculate(float[] data) {
        // TODO: implement
        return 0f;
    }
}
