package com.audioanalyzer.application.data.audioparameter.impl;

import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;

public class PeakLevel extends AudioParameter {

    public PeakLevel() {
        super(AudioParameterType.PeakLevel);
    }

    @Override
    public float calculate(float[] data) {
        // TODO: implement
        return 0f;
    }
}
