package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;

public class PeakLevel extends AudioParameter {

    public PeakLevel() {
        super(Type.PeakLevel);
    }

    @Override
    public void calculate(float[] data) {
    }
}
