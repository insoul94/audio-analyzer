package com.audioanalyzer.application.data.audioparameters;

public class PeakLevel extends AudioParameter {

    public PeakLevel() {
        super(Type.PeakLevel);
    }

    @Override
    public void calculate(byte[] data) {
    }
}
