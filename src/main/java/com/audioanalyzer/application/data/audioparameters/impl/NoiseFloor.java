package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

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
