package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class LUFS extends AudioParameter {

    public LUFS() {
        super(AudioParameterType.LUFS);
    }

    @Override
    public float calculate(float[] data) {
        // TODO: implement
        return 0f;
    }
}
