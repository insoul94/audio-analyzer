package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class NoiseFloor extends AudioParameter {

    public NoiseFloor(AudioFile source) {
        super(AudioParameterType.NoiseFloor, source);
    }

    @Override
    public void calculate(float[] data) {
        // TODO: implement
    }
}
