package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;

public class LUFS extends AudioParameter {

    public LUFS() {
        super(Type.LUFS);
    }

    @Override
    public void calculate(float[] data) {
    }
}
