package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;

public class NoiseFloor extends AudioParameter {

    public NoiseFloor() {
        super(Type.NoiseFloor);
    }

    @Override
    public void calculate(float[] data) {
    }
}
