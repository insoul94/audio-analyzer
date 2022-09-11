package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class PeakLevel extends AudioParameter {

    public PeakLevel() {
        super(AudioParameterType.PeakLevel);
    }

    @Override
    public void calculate(float[] data) {
        // TODO: implement
    }
}
