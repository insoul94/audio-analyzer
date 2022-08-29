package com.audioanalyzer.application.data.audioparameters;

public class NoiseFloor extends AudioParameter {

    public NoiseFloor() {
        super(Type.NoiseFloor);
    }

    @Override
    public void calculate(byte[] data) {
    }
}
