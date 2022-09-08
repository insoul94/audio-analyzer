package com.audioanalyzer.application.data.audioparameters;

import java.io.InputStream;

public class NoiseFloor extends AudioParameter {

    public NoiseFloor() {
        super(Type.NoiseFloor);
    }

    @Override
    public void calculate(int[] data) {
    }
}
