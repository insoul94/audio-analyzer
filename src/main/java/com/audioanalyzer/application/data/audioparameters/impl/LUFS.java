package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class LUFS extends AudioParameter {

    public LUFS(AudioFile source) {
        super(AudioParameterType.LUFS, source);
    }

    @Override
    public void calculate(float[] data) {
        // TODO: implement
    }
}
