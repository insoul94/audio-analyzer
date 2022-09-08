package com.audioanalyzer.application.data.audioparameters;

import java.io.InputStream;

public class RMS extends AudioParameter {

    public RMS() {
        super(Type.RMS);
    }

    public RMS(double value) {
        super(Type.RMS);
        this.setValue(value);
    }

    @Override
    public void calculate(int[] data) {

    }
}
