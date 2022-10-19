package com.audioanalyzer.application.data.audioparameter.impl;

import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;

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
